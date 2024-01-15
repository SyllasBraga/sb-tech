package com.sb.tech.dtos;

import java.util.UUID;

public record ClientDto(
    UUID id,
    String document,
    String name,
    String phone,
    String email
) {
}
