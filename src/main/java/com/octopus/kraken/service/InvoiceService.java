package com.octopus.kraken.service;

import com.octopus.kraken.custom.InvalidInputException;
import com.octopus.kraken.custom.MalformedDataException;
import com.octopus.kraken.entity.Invoice;
import com.octopus.kraken.entity.Transaction;
import com.octopus.kraken.repository.InvoiceRepository;
import com.octopus.kraken.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Invoice createInvoice(Invoice invoice) {
        //since we want to consider negative case hence no use of this
        //if (invoice.getNetAmount().compareTo(BigDecimal.valueOf(0)) <= 0) {
          //  throw new MalformedDataException("Invoice amount must be greater than zero");
        //}

        if (invoice.getTransactions() == null || invoice.getTransactions().isEmpty()) {
            throw new InvalidInputException("No transactions found for the invoice");
        }
        validateInvoice(invoice.getInvoiceId());
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice getInvoiceByInvoiceId(Long invoiceId) {
        return invoiceRepository.findByInvoiceId(invoiceId);
    }

    public String validateInvoice(Long invoiceId) {
        Invoice invoice = getInvoiceByInvoiceId(invoiceId);
        if (invoice == null) {
            return "Invoice not found";
        }

        List<Transaction> transactions = invoice.getTransactions();
        BigDecimal totalNetTransactionAmount = transactions.stream()
                .map(Transaction::getNetTransactionAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (!totalNetTransactionAmount.equals(invoice.getNetAmount())) {
            invoice.setValidationStatus("INVALID");
            invoice.setValidationReason("Transaction total does not match invoice total");
        } else if (transactions.size() != invoice.getTotalTransactionCount()) {
            invoice.setValidationStatus("INVALID");
            invoice.setValidationReason("Transaction count does not match invoice transaction count");
        } else {
            invoice.setValidationStatus("VALID");
            invoice.setValidationReason(null);
        }

        invoiceRepository.save(invoice);
        return invoice.getValidationStatus();
    }
}

