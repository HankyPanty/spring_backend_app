package com.locoProject.backendApp.clients;

import com.locoProject.backendApp.models.TransactionDto;

import java.util.List;

public interface dbInterface {


    public String put(Long id, TransactionDto dto);

    public TransactionDto get(Long id);

    public List<Long> getType(String type);

    public List<Long> getChildren(Long id);
}
