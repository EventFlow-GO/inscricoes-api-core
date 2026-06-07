package com.eventflow.controller;

import com.eventflow.dto.InscricaoRequestDTO;
import com.eventflow.model.InscricaoModel;
import com.eventflow.service.InscricaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscricao")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @PostMapping
    public ResponseEntity<InscricaoModel> registrarInscricao(@RequestBody InscricaoRequestDTO request) {

        InscricaoModel inscricaoSalva = inscricaoService.processarNovaInscricao(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inscricaoSalva);
    }
}