package com.locoProject.backendApp.controller;

import com.locoProject.backendApp.models.TransactionDto;
import com.locoProject.backendApp.service.ServiceFactory;
import com.locoProject.backendApp.service.ServiceInterface;
import com.locoProject.backendApp.service.ServiceTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private ServiceFactory serviceFactory;

    @GetMapping("/transactionservice/transaction/{transactionId}")
    @ResponseBody
    public String getTransaction(@PathVariable("transactionId") Long Id){
        ServiceInterface si = serviceFactory.getService(ServiceTypes.FILTER);
        try {
            return si.getTransaction(Id);
        } catch (IllegalArgumentException e){
            return e.toString();
        }
    }

    @PostMapping("/transactionservice/transaction/{transactionId}")
    @ResponseBody
    public String putTransaction(@PathVariable("transactionId") Long Id, @RequestBody TransactionDto data){
        ServiceInterface si = serviceFactory.getService(ServiceTypes.TRANSACTION);
        try{
            return si.setTransaction(Id, data);
        } catch (IllegalArgumentException e){
            return e.toString();
        }
    }

    @GetMapping("/transactionservice/transaction/types/{type}")
    @ResponseBody
    public String filterType(@PathVariable("type") String type){
        ServiceInterface si = serviceFactory.getService(ServiceTypes.FILTER);
        try{
            return si.getTransactionsByType(type);
        } catch (IllegalArgumentException e){
            return e.toString();
        }
    }

    @GetMapping("/transactionservice/transaction/sum/{transactionId}")
    @ResponseBody
    public String getTransactionSum(@PathVariable("transactionId") Long Id){
        ServiceInterface si = serviceFactory.getService(ServiceTypes.FILTER);

        try{
            return si.getTransactionsSum(Id);
        } catch (IllegalArgumentException e){
            return e.toString();
        }
    }


}
