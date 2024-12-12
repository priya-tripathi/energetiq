package com.octopus.kraken.service;

import com.octopus.kraken.entity.Invoice;
import com.octopus.kraken.entity.Transaction;
import com.octopus.kraken.repository.InvoiceRepository;
import com.octopus.kraken.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateInvoice_Valid() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1L);
        invoice.setNetAmount(new BigDecimal("100.00"));
        invoice.setTotalTransactionCount(2);

        Transaction transaction1 = new Transaction();
        transaction1.setNetTransactionAmount(new BigDecimal("50.00"));

        Transaction transaction2 = new Transaction();
        transaction2.setNetTransactionAmount(new BigDecimal("50.00"));

        invoice.setTransactions(Arrays.asList(transaction1, transaction2));

        when(invoiceRepository.findByInvoiceId(1L)).thenReturn(invoice);
        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        String status = invoiceService.validateInvoice(1L);

        assertEquals("VALID", status);
        assertNull(invoice.getValidationReason());
    }

    @Test
    void testValidateInvoice_InvalidTotal() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1L);
        invoice.setNetAmount(new BigDecimal("150.00"));
        invoice.setTotalTransactionCount(2);

        Transaction transaction1 = new Transaction();
        transaction1.setNetTransactionAmount(new BigDecimal("50.00"));

        Transaction transaction2 = new Transaction();
        transaction2.setNetTransactionAmount(new BigDecimal("50.00"));

        invoice.setTransactions(Arrays.asList(transaction1, transaction2));

        when(invoiceRepository.findByInvoiceId(1L)).thenReturn(invoice);
        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        String status = invoiceService.validateInvoice(1L);

        assertEquals("INVALID", status);
        assertEquals("Transaction total does not match invoice total", invoice.getValidationReason());
    }

    @Test
    void testValidateInvoice_InvalidTransactionCount() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1L);
        invoice.setNetAmount(new BigDecimal("100.00"));
        invoice.setTotalTransactionCount(3);

        Transaction transaction1 = new Transaction();
        transaction1.setNetTransactionAmount(new BigDecimal("50.00"));

        Transaction transaction2 = new Transaction();
        transaction2.setNetTransactionAmount(new BigDecimal("50.00"));

        invoice.setTransactions(Arrays.asList(transaction1, transaction2));

        when(invoiceRepository.findByInvoiceId(1L)).thenReturn(invoice);
        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        String status = invoiceService.validateInvoice(1L);

        assertEquals("INVALID", status);
        assertEquals("Transaction count does not match invoice transaction count", invoice.getValidationReason());
    }
}
