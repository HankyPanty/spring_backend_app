package com.locoProject.backendApp.service;

import com.locoProject.backendApp.clients.DBClient;
import com.locoProject.backendApp.models.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class FilterService implements ServiceInterface{
    @Autowired
    private DBClient dbClient;

    @Override
    public String getTransactionsByType(String type) {
        return dbClient.getType(type).toString();
    }

    @Override
    public String getTransactionsSum(Long id) {
        Stack<Long> childs = new Stack<>();
        childs.add(id);
        Long sum = 0L;
        while(!childs.isEmpty()){
            Long curId = childs.pop();
            sum += dbClient.get(curId).amount;
            childs.addAll(dbClient.getChildren(curId));
        }
        return sum.toString();
    }

    @Override
    public String getTransaction(Long id) {
        return dbClient.get(id).toString();
    }

    @Override
    public String setTransaction(Long id, TransactionDto data) {
        throw new IllegalArgumentException("Cannot access Set using FilterService");
    }

}
