package com.eventflow.producer;

import com.eventflow.dto.InscricaoMessage;
import com.eventflow.model.InscricaoModel;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class InscricaoProducer {

    private final SqsTemplate sqsTemplate;

    @Value("${aws.sqs.queue.inscricoes.name}")
    private String filaInscricoes;

    public InscricaoProducer(SqsTemplate sqsTemplate){
        this.sqsTemplate = sqsTemplate;
    }

    @Retryable(
            retryFor = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
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

    @Recover
    public void recover(Exception e, InscricaoModel inscricao) {
        System.err.println("Falha definitiva ao enviar mensagem para SQS após retentativas. ID: " + inscricao.getId() + ". Erro: " + e.getMessage());
        // Aqui poderíamos salvar em uma tabela de 'mensagens_falhas' para reprocessamento manual
    }
}