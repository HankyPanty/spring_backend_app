package com.locoProject.backendApp.service;

import com.locoProject.backendApp.clients.DBClient;
import com.locoProject.backendApp.models.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;

public interface ServiceInterface {
    DBClient dbClient = null;

    String getTransaction(Long id);

    String setTransaction(Long id, TransactionDto data);

    String getTransactionsByType(String type);

    String getTransactionsSum(Long id);
}
