package com.eventflow.service;

import com.eventflow.dto.InscricaoRequestDTO;
import com.eventflow.model.InscricaoModel;
import com.eventflow.producer.InscricaoProducer;
import com.eventflow.repository.InscricaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscricaoService {

    private final InscricaoRepository repository;
    private final InscricaoProducer producer;

    public InscricaoService(InscricaoRepository repository, InscricaoProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Transactional
    public InscricaoModel processarNovaInscricao(InscricaoRequestDTO request) {

        InscricaoModel novaInscricao = new InscricaoModel();
        novaInscricao.setNome(request.nome());
        novaInscricao.setEmail(request.email());
        novaInscricao.setSenha(request.senha());

        InscricaoModel inscricaoSalva = repository.save(novaInscricao);

        // Publica a mensagem na Fila com o ID gerado
        producer.enviarIncricao(inscricaoSalva);

        return inscricaoSalva;
    }
}