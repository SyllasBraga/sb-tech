package com.sb.tech.api;

import com.sb.tech.dtos.TechnicianDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/technician")
public interface TechnicianApi {

    @GetMapping("/{id}")
    ResponseEntity<TechnicianDto> getById(@PathVariable String id);
    @GetMapping("")
    ResponseEntity<TechnicianDto> getByDocument(@RequestParam String document);
    @PostMapping
    ResponseEntity<TechnicianDto> insert(@RequestBody @Valid TechnicianDto technicianDto);
    @PutMapping("/{id}")
    ResponseEntity<TechnicianDto> update(@PathVariable String id, @Valid @RequestBody TechnicianDto technicianDto);
    @DeleteMapping("/{id}")
    ResponseEntity<TechnicianDto> delete(@PathVariable String id);
}
