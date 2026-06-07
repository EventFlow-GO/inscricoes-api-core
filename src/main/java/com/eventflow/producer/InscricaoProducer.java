package com.eventflow.producer;

import com.eventflow.dto.InscricaoMessage;
import com.eventflow.dto.InscricaoRequestDTO;
import com.eventflow.model.InscricaoModel;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InscricaoProducer {

    private final SqsTemplate sqsTemplate;

    @Value("${aws.sqs.queue.inscricoes.name}")
    private String filaInscricoes;

    public InscricaoProducer(SqsTemplate sqsTemplate){
        this.sqsTemplate = sqsTemplate;
    }

    public void enviarIncricao(InscricaoModel inscricao){
        InscricaoMessage message = new InscricaoMessage(
                inscricao.getId(),
                inscricao.getNome(),
                inscricao.getEmail(),
                inscricao.getSenha()
        );

        sqsTemplate.send(to -> to
                .queue(filaInscricoes)
                .payload(message)
        );
        System.out.println("Mensagem enviada para a AWS SQS com sucesso! ID: " + inscricao.getId());
    }
}