package com.locoProject.backendApp.service;

import com.locoProject.backendApp.clients.DBClient;
import com.locoProject.backendApp.models.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ServiceInterface{
    @Autowired
    private DBClient dbClient;

    @Override
    public String getTransactionsByType(String type) {
        throw new IllegalArgumentException("Cannot access Get using TransactionService");
    }

    @Override
    public String getTransactionsSum(Long id) {
        throw new IllegalArgumentException("Cannot access Get using TransactionService");
    }

    @Override
    public String getTransaction(Long id) {
        throw new IllegalArgumentException("Cannot access Get using TransactionService");
    }

    @Override
    public String setTransaction(Long id, TransactionDto data) {
        validateData(id, data);
        return dbClient.put(id, data);
    }

    public void validateData(Long id, TransactionDto data) {
        // Check if id is new;
        // Throw if id is used;
    }

}
