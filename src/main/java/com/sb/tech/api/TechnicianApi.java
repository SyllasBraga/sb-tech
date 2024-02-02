package com.sb.tech.api;

import com.sb.tech.dtos.TechnicianDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/technician")
public interface TechnicianApi {

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDto> getById(@PathVariable String id);
    @GetMapping("")
    public ResponseEntity<TechnicianDto> getByDocument(@RequestParam String document);
    @PostMapping
    public ResponseEntity<TechnicianDto> insert(@RequestBody @Valid TechnicianDto technicianDto);
    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDto> update(@PathVariable String id, @Valid @RequestBody TechnicianDto technicianDto);
    @DeleteMapping("/{id}")
    public ResponseEntity<TechnicianDto> delete(@PathVariable String id);
}
