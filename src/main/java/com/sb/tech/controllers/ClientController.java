package com.sb.tech.controllers;

import com.sb.tech.dtos.ClientDto;
import com.sb.tech.models.ClientModel;
import com.sb.tech.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable String id){
        return ResponseEntity.ok(ClientDto.toClientDto(clientService.getByUuid(id)));
    }

    @GetMapping("")
    public ResponseEntity<ClientDto> getByDocument(@RequestParam String document){
        return ResponseEntity.ok(ClientDto.toClientDto(clientService.getClientByDocument(document)));
    }

    @PostMapping
    public ResponseEntity<ClientDto> insert(@RequestBody @Valid ClientDto clientDto){
        ClientModel clientModel = ClientModel.toClientModel(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientDto.toClientDto(clientService.insertClient(clientModel)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable String id, @Valid @RequestBody ClientDto clientDto){
        ClientModel clientSaved = ClientModel.toClientModel(clientDto);
        return ResponseEntity.ok(ClientDto.toClientDto(clientService.updateClient(id, clientSaved)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDto> delete(@PathVariable String id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
