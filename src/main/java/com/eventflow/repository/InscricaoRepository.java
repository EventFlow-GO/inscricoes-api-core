package com.eventflow.repository;

import com.eventflow.model.InscricaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InscricaoRepository extends JpaRepository<InscricaoModel, UUID>{
}
