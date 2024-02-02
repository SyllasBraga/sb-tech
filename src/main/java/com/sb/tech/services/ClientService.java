package com.sb.tech.services;

import com.sb.tech.models.ClientModel;

import java.util.List;

public interface ClientService {

    public ClientModel getByUuid(String uuid);
    public List<ClientModel> getAll();
    public ClientModel getClientByDocument(String document);
    public ClientModel insertClient(ClientModel clientModel);
    public ClientModel updateClient(String id, ClientModel clientModel);
    public void deleteClient(String uuid);
}
