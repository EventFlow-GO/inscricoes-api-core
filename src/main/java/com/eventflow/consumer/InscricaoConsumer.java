package com.eventflow.consumer;

import com.eventflow.dto.InscricaoMessage;
import com.eventflow.model.InscricaoModel;
import com.eventflow.repository.InscricaoRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InscricaoConsumer {

    @Autowired
    private InscricaoRepository repository;

    @SqsListener("${aws.sqs.queue.inscricoes.name}")
    public void processarInscricao(InscricaoMessage mensagem) {
        try {
            System.out.println("Processando nova inscrição da fila SQS para ID: " + mensagem.id());

            InscricaoModel inscricao = repository.findById(mensagem.id()).orElseThrow(() ->
                    new RuntimeException("Inscrição não encontrada no banco: " + mensagem.id())
            );

            // Atualiza o status
            inscricao.setStatus("CONCLUIDO");

            // Salva a alteração no banco
            repository.save(inscricao);

            System.out.println("Concluido! Inscrição " + mensagem.id() + " atualizada para CONCLUÍDO!");

        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem da fila: " + e.getMessage());
        }
    }
}
