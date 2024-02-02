package com.sb.tech.api;

import com.sb.tech.dtos.ClientDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClientApi {

    @GetMapping("")
    public ResponseEntity<List<ClientDto>> getAll();
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable String id);
    @GetMapping("/document")
    public ResponseEntity<ClientDto> getByDocument(@RequestParam String document);
    @PostMapping
    public ResponseEntity<ClientDto> insert(@RequestBody @Valid ClientDto clientDto);
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable String id, @Valid @RequestBody ClientDto clientDto);
    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDto> delete(@PathVariable String id);
}
