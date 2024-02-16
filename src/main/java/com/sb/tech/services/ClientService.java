package com.sb.tech.services;

import com.sb.tech.models.ClientModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ClientService {

    ClientModel getByUuid(String uuid);
    @Cacheable("clients")
    List<ClientModel> getAll();
    ClientModel getClientByDocument(String document);
    @CachePut("clients")
    ClientModel insertClient(ClientModel clientModel);
    @CachePut("clients")
    ClientModel updateClient(String id, ClientModel clientModel);
    @CacheEvict("clients")
    void deleteClient(String uuid);
}
