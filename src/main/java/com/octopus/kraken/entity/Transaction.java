package com.octopus.kraken.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.octopus.kraken.LocalDateTimeDeserializer;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "transactionId")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trxn_id", nullable = false)
    private Long transactionId;

    @Column(name = "net_transaction_amount", precision = 10, scale = 2)
    private BigDecimal netTransactionAmount;

    @Column(name = "gst_amount", precision = 10, scale = 2)
    private BigDecimal gstAmount;

    @Column(name = "date_received")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateReceived;

    @Column(name = "transaction_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime transactionDate;

    @Column(name = "billing_period_start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime billingPeriodStart;

    @Column(name = "billing_period_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime billingPeriodEnd;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id", nullable = false)
    @JsonBackReference
    private Invoice invoice;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getNetTransactionAmount() {
        return netTransactionAmount;
    }

    public void setNetTransactionAmount(BigDecimal netTransactionAmount) {
        this.netTransactionAmount = netTransactionAmount;
    }

    public BigDecimal getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(BigDecimal gstAmount) {
        this.gstAmount = gstAmount;
    }

    public LocalDateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public LocalDateTime getBillingPeriodStart() {
        return billingPeriodStart;
    }

    public void setBillingPeriodStart(LocalDateTime billingPeriodStart) {
        this.billingPeriodStart = billingPeriodStart;
    }

    public LocalDateTime getBillingPeriodEnd() {
        return billingPeriodEnd;
    }

    public void setBillingPeriodEnd(LocalDateTime billingPeriodEnd) {
        this.billingPeriodEnd = billingPeriodEnd;
    }
    public Transaction() {
        // No-args constructor required by Jackson
    }
}
