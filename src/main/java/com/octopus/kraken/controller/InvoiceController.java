package com.octopus.kraken.controller;

import com.octopus.kraken.custom.InvalidInputException;
import com.octopus.kraken.custom.MalformedDataException;
import com.octopus.kraken.entity.Invoice;
import com.octopus.kraken.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok().body("The Energetiq API health is good");
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        try {
            Invoice savedInvoice = invoiceService.createInvoice(invoice);
            return ResponseEntity.ok(savedInvoice);
        } catch (MalformedDataException | InvalidInputException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error occurred while creating the invoice");
        }
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }
    // This endpoint is secured; only authorized users can access
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(invoiceService.getInvoiceByInvoiceId(invoiceId));
    }

    @GetMapping("/{invoiceId}/status")
    public ResponseEntity<String> getInvoiceStatus(@PathVariable Long invoiceId) {
        String status = invoiceService.validateInvoice(invoiceId);
        return ResponseEntity.ok(status);
    }
}

