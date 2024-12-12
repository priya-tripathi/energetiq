package com.octopus.kraken.repository;

import com.octopus.kraken.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByInvoiceId(Long invoiceId);
}
