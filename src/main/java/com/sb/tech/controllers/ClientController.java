package com.sb.tech.controllers;

import com.sb.tech.api.ClientApi;
import com.sb.tech.dtos.ClientDto;
import com.sb.tech.models.ClientModel;
import com.sb.tech.services.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private final ClientServiceImpl clientServiceImpl;

    public ClientController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @Override
    public ResponseEntity<List<ClientDto>> getAll(){
        List<ClientDto> listClients = new ArrayList<>();
        clientServiceImpl.getAll().forEach(clientModel ->
            listClients.add(ClientDto.toClientDto(clientModel))
        );
        return ResponseEntity.ok(listClients);
    }

    @Override
    public ResponseEntity<ClientDto> getById(@PathVariable String id){
        return ResponseEntity.ok(ClientDto.toClientDto(clientServiceImpl.getByUuid(id)));
    }

    @Override
    public ResponseEntity<ClientDto> getByDocument(@RequestParam String document){
        return ResponseEntity.ok(ClientDto.toClientDto(clientServiceImpl.getClientByDocument(document)));
    }

    @Override
    public ResponseEntity<ClientDto> insert(@RequestBody @Valid ClientDto clientDto){
        ClientModel clientModel = ClientModel.toClientModel(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientDto.toClientDto(clientServiceImpl.insertClient(clientModel)));
    }

    @Override
    public ResponseEntity<ClientDto> update(@PathVariable String id, @Valid @RequestBody ClientDto clientDto){
        ClientModel clientSaved = ClientModel.toClientModel(clientDto);
        return ResponseEntity.ok(ClientDto.toClientDto(clientServiceImpl.updateClient(id, clientSaved)));
    }

    @Override
    public ResponseEntity<ClientDto> delete(@PathVariable String id){
        clientServiceImpl.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
