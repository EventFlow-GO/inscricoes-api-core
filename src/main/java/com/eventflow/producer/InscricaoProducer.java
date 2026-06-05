package com.eventflow.producer;

import com.eventflow.dto.InscricaoRequestDTO;
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

    public void enviarIncricao(InscricaoRequestDTO inscricao){
        sqsTemplate.send(to -> to
                .queue(filaInscricoes)
                .payload(inscricao)
        );
        System.out.println("Mensagem enviada para a AWS SQS com sucesso!");
    }
}