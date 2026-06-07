package com.eventflow.dto;

import java.util.UUID;

public record InscricaoMessage(UUID id, String nome, String email) {
}
