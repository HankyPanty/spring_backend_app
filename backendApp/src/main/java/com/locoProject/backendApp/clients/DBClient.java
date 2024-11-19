package com.locoProject.backendApp.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.locoProject.backendApp.models.TransactionDto;
import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class DBClient implements dbInterface{
    Map<Long, Record> IDstore;
    Map<String,List<Long>> TYPEstore;

    public DBClient(){
        this.IDstore = new HashMap<>();
        this.TYPEstore = new HashMap<>();
    }

    @Override
    public String put(Long id, TransactionDto dto) {
        if(IDstore.containsKey(id)) throw new IllegalArgumentException("Cannot add the same ID again.");
        if(!(dto.parentId==null || IDstore.containsKey(dto.parentId)))
            throw new IllegalArgumentException("Cannot add data as Parent not present.");

        IDstore.put(id, new Record(dto.type, dto.amount, dto.parentId));
        List<Long> typeIds = TYPEstore.getOrDefault(dto.type, new ArrayList<>());
        TYPEstore.put(dto.type, typeIds);
        return "Success";
    }

    @Override
    public TransactionDto get(Long id) {
        if(!IDstore.containsKey(id)) throw new IllegalArgumentException("This Id is not Present.");
        Record r = IDstore.get(id);
        TransactionDto t = new TransactionDto();
        t.type = r.type;
        t.amount = r.amount;
        t.parentId = r.parentId;
        return t;
    }

    @Override
    public List<Long> getType(String type) {
        return TYPEstore.getOrDefault(type, new ArrayList<>());
    }

    @Override
    public Long getSum(Long id) {
        Long curId = id;
        Long sum = 0L;
        while(curId!=null){
            if(IDstore.containsKey(curId)){
                Record r = IDstore.get(curId);
                sum += r.amount;
                curId = r.parentId;
            }
            else{
                throw new IllegalArgumentException("Id: " + curId + " is not Present.");
            }
        }
        return sum;
    }

    public class Record{

        public Record(String type, Long amount, Long parentId){
            this.type = type;
            this.amount = amount;
            this.parentId = parentId;
        }

        String type;

        Long amount;

        Long parentId;
    }
}
