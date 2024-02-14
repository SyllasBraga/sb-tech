package com.sb.tech.api;

import com.sb.tech.dtos.ClientDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/client")
public interface ClientApi {

    @GetMapping("")
    ResponseEntity<List<ClientDto>> getAll();
    @GetMapping("/{id}")
    ResponseEntity<ClientDto> getById(@PathVariable String id);
    @GetMapping("/document")
    ResponseEntity<ClientDto> getByDocument(@RequestParam String document);
    @PostMapping
    ResponseEntity<ClientDto> insert(@RequestBody @Valid ClientDto clientDto);
    @PutMapping("/{id}")
    ResponseEntity<ClientDto> update(@PathVariable String id, @Valid @RequestBody ClientDto clientDto);
    @DeleteMapping("/{id}")
    ResponseEntity<ClientDto> delete(@PathVariable String id);
}
