# energetiq

This is basic Springboot - Rest - Swagger(OpenAPI) - Postgresql api application

<img width="1450" alt="image" src="https://github.com/user-attachments/assets/d4b0dd6a-0ca6-447e-b507-55f9e8b51229" />

Sample Input for /POST

```json
{
  "id": 0,
  "invoiceId": 31558,
  "invoiceNumber": "893018131C",
  "grossAmount": -27.32,
  "gstAmount": -2.49,
  "netAmount": -24.83,
  "receiptDate": "2006-01-01 00:00:00.000",
  "paymentDueDate": "2006-01-01 00:00:00.000",
  "totalTransactionCount": 2,
  "validationStatus": "string",
  "validationReason": "string",
  "transactions": [
    {
      "id": 0,
      "transactionId": 725740,
      "netTransactionAmount": -2.47,
      "gstAmount": -0.25,
      "dateReceived": "2006-01-01 00:00:00.000",
      "transactionDate": "2006-01-01 00:00:00.000",
      "billingPeriodStart": "2006-01-01 00:00:00.000",
      "billingPeriodEnd": "2006-01-01 00:00:00.000",
      "invoice": 31558
      
    },
    {
      "id": 0,
      "transactionId": 728607,
      "netTransactionAmount": -22.36,
      "gstAmount": -2.24,
      "dateReceived": "2006-01-01 00:00:00.000",
      "transactionDate": "2006-01-01 00:00:00.000",
      "billingPeriodStart": "2006-01-01 00:00:00.000",
      "billingPeriodEnd": "2006-01-01 00:00:00.000",
      "invoice": 31558
      
    }
  ]
}

```
To start:
For Postgres setup:
```
1. create database invoice_management;
2. CREATE TABLE invoice (
    id SERIAL PRIMARY KEY, 
    invoice_id BIGINT NOT NULL UNIQUE, -- Ensure invoice_id is unique
    invoice_number VARCHAR(255),
    gross_amount DECIMAL(10, 2),
    gst_amount DECIMAL(10, 2),
    net_amount DECIMAL(10, 2),
    receipt_date DATE,
    payment_due_date DATE,
    total_transaction_count INT,
    validation_status VARCHAR(255) DEFAULT 'VALID',
    validation_reason TEXT
);
3. CREATE TABLE transaction (
    id SERIAL PRIMARY KEY, 
    trxn_id BIGINT NOT NULL,
    invoice_id BIGINT NOT NULL,
    net_transaction_amount DECIMAL(10, 2),
    gst_amount DECIMAL(10, 2),
    date_received DATE,
    transaction_date DATE,
    FOREIGN KEY (invoice_id) REFERENCES invoice (invoice_id) -- References the unique invoice_id
);
4. Insert sample data -
INSERT INTO invoice (
    invoice_id, invoice_number, gross_amount, gst_amount, net_amount, receipt_date, payment_due_date, total_transaction_count
) VALUES (
    123, 'INV-001', 150.00, 10.00, 140.00, '2024-12-01', '2024-12-15', 2
);
INSERT INTO invoice (
    invoice_id, invoice_number, gross_amount, gst_amount, net_amount, receipt_date, payment_due_date, total_transaction_count
) VALUES (
    123, 'INV-001', 150.00, 10.00, 140.00, '2024-12-01', '2024-12-15', 2
);
```

For running the application -
```
1. mvn clean install
2. ./mvnw spring-boot:run
```
