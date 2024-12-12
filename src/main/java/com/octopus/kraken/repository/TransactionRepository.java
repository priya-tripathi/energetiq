package com.octopus.kraken.repository;

import com.octopus.kraken.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // No changes, uses default JpaRepository methods
}

