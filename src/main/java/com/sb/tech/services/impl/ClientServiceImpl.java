package com.sb.tech.services.impl;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.ClientModel;
import com.sb.tech.repositories.ClientRepository;
import com.sb.tech.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private static final String EXCEPTION_UUID_INVALID = "Exception Parse UUID: Invalid UUID";
    private static final String CLIENT_NOT_FOUND = "Client not found";

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientModel getByUuid(String uuid){
        try {
            return clientRepository.findById(UUID.fromString(uuid))
                    .orElseThrow(() -> new NotFoundException(CLIENT_NOT_FOUND));
        }catch (IllegalArgumentException ex){
            throw new UuidParseException(EXCEPTION_UUID_INVALID);
        }
    }

    public List<ClientModel> getAll(){
        return clientRepository.findAll();
    }

    public ClientModel getClientByDocument(String document){
        return clientRepository.findByDocument(document);
    }

    public ClientModel insertClient(ClientModel clientModel){
        clientModel.setId(UUID.randomUUID());
        return clientRepository.save(clientModel);
    }

    public ClientModel updateClient(String id, ClientModel clientModel){
        try {
            clientModel.setId(UUID.fromString(id));
            ClientModel actualClient = getByUuid(id);
            actualClient.update(clientModel);
            return clientRepository.save(actualClient);
        }catch (IllegalArgumentException ex){
            throw new UuidParseException(EXCEPTION_UUID_INVALID);
        }
    }

    public void deleteClient(String uuid) {
        try {
            clientRepository.deleteById(UUID.fromString(uuid));
        } catch (IllegalArgumentException ex) {
            throw new UuidParseException(EXCEPTION_UUID_INVALID);
        }
    }
}
