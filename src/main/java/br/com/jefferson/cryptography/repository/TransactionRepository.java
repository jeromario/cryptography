package br.com.jefferson.cryptography.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jefferson.cryptography.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>{

}
