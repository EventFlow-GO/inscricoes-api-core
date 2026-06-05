package com.eventflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inscricao")
public class InscricaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String status;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime data;

    @PrePersist
    private void prePersist(){
        this.data = LocalDateTime.now();
        if(this.status == null){
            this.status = "PENDENTE";
        }
    }
}
