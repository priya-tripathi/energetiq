package com.octopus.kraken.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.octopus.kraken.LocalDateTimeDeserializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "invoiceId")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_id", nullable = false, unique = true)
    private Long invoiceId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "gross_amount", precision = 10, scale = 2)
    private BigDecimal grossAmount;

    @Column(name = "gst_amount", precision = 10, scale = 2)
    private BigDecimal gstAmount;

    @Column(name = "net_amount", precision = 10, scale = 2)
    private BigDecimal netAmount;

    @Column(name = "receipt_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime receiptDate;

    @Column(name = "payment_due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime paymentDueDate;

    @Column(name = "total_transaction_count")
    private Integer totalTransactionCount;

    @Column(name = "validation_status")
    private String validationStatus = "VALID";

    @Column(name = "validation_reason")
    private String validationReason;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Transaction> transactions;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public BigDecimal getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(BigDecimal gstAmount) {
        this.gstAmount = gstAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public LocalDateTime getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDateTime receiptDate) {
        this.receiptDate = receiptDate;
    }

    public LocalDateTime getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDateTime paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Integer getTotalTransactionCount() {
        return totalTransactionCount;
    }

    public void setTotalTransactionCount(Integer totalTransactionCount) {
        this.totalTransactionCount = totalTransactionCount;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getValidationReason() {
        return validationReason;
    }

    public void setValidationReason(String validationReason) {
        this.validationReason = validationReason;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public Invoice() {
        // No-args constructor required by Jackson
    }
    @JsonCreator
    public Invoice(
            @JsonProperty("invoiceId") Long invoiceId, // Change to Long to match JSON input
            @JsonProperty("receiptDate") LocalDateTime receiptDate, // Match the JSON key name
            @JsonProperty("netAmount") BigDecimal netAmount, // Change to BigDecimal to match the expected type
            @JsonProperty("transactions") List<Transaction> transactions) {
        this.invoiceId = invoiceId;
        this.receiptDate = receiptDate;
        this.netAmount = netAmount;
        this.transactions = transactions;
    }
}
