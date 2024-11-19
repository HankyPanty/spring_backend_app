package com.locoProject.backendApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {
    @Autowired
    private FilterService filterService;

    @Autowired
    private TransactionService transactionService;

    public ServiceInterface getService(ServiceTypes type){
        if(type.equals(ServiceTypes.FILTER)) return filterService;
        else if(type.equals(ServiceTypes.TRANSACTION)) return transactionService;
        else throw new IllegalArgumentException("Invalid Operation");
    }

}
