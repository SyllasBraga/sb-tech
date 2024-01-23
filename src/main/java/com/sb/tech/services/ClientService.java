package com.sb.tech.services;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.ClientModel;
import com.sb.tech.repositories.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class ClientService {
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String EXCEPTION_UUID_INVALID = "Exception Parse UUID: Invalid UUID";
    private static final String CLIENT_NOT_FOUND = "Client not found";

    public ClientService(ClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ClientModel getByUuid(String uuid){
        try {
            return clientRepository.findById(UUID.fromString(uuid))
                    .orElseThrow(() -> new NotFoundException(CLIENT_NOT_FOUND));
        }catch (IllegalArgumentException ex){
            throw new UuidParseException(EXCEPTION_UUID_INVALID);
        }
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
