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

