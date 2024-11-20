package com.locoProject.backendApp.clients;

import com.locoProject.backendApp.models.TransactionDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope("singleton")
public class DBClient implements dbInterface{
    Map<Long, Record> records;
    Map<String,List<Long>> typeFieldIndex;

    public DBClient(){
        this.records = new HashMap<>();
        this.typeFieldIndex = new HashMap<>();
    }

    @Override
    public String put(Long id, TransactionDto dto) {
        if(records.containsKey(id)) throw new IllegalArgumentException("Cannot add the same ID again.");
        if(!(dto.parentId==null || records.containsKey(dto.parentId)))
            throw new IllegalArgumentException("Cannot add data as Parent not present.");

        // Add in main db;
        records.put(id, new Record(dto.type, dto.amount, dto.parentId, new ArrayList<>()));
        // Update parent Entry
        if(dto.parentId!=null) records.get(dto.parentId).children.add(id);
        // Update typeField Index
        List<Long> typeIds = typeFieldIndex.getOrDefault(dto.type, new ArrayList<>());
        typeIds.add(id);
        typeFieldIndex.put(dto.type, typeIds);
        return "Success";
    }

    @Override
    public TransactionDto get(Long id) {
        if(!records.containsKey(id)) throw new IllegalArgumentException("This Id is not Present.");
        Record r = records.get(id);
        TransactionDto t = new TransactionDto();
        t.type = r.type;
        t.amount = r.amount;
        t.parentId = r.parentId;
        return t;
    }

    @Override
    public List<Long> getType(String type) {
        return typeFieldIndex.getOrDefault(type, new ArrayList<>());
    }

    @Override
    public List<Long> getChildren(Long id) {
        return records.get(id).children;
    }

    private class Record{

        public Record(String type, Long amount, Long parentId, List<Long> children){
            this.type = type;
            this.amount = amount;
            this.parentId = parentId;
            this.children = children;
        }

        String type;

        Long amount;

        Long parentId;

        List<Long> children;
    }
}
