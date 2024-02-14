package com.sb.tech.services;

import com.sb.tech.models.ClientModel;

import java.util.List;

public interface ClientService {

    ClientModel getByUuid(String uuid);
    List<ClientModel> getAll();
    ClientModel getClientByDocument(String document);
    ClientModel insertClient(ClientModel clientModel);
    ClientModel updateClient(String id, ClientModel clientModel);
    void deleteClient(String uuid);
}
