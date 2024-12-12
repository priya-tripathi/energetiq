package com.octopus.kraken.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octopus.kraken.entity.Invoice;
import com.octopus.kraken.service.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active=test")
//@Transactional
//@AutoConfigureMockMvc
class InvoiceControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
//    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    void testCreateInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1L);
        invoice.setNetAmount(new BigDecimal("100.00"));

        when(invoiceService.createInvoice(any(Invoice.class))).thenReturn(invoice);

        mockMvc.perform(post("/api/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invoice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.invoiceId").value(1));
    }

    @Test
    void testGetAllInvoices() throws Exception {
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1L);

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(2L);

        when(invoiceService.getAllInvoices()).thenReturn(Arrays.asList(invoice1, invoice2));

        mockMvc.perform(get("/api/invoice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].invoiceId").value(1))
                .andExpect(jsonPath("$[1].invoiceId").value(2));
    }

    @Test
    void testGetInvoiceStatus() throws Exception {
        when(invoiceService.validateInvoice(1L)).thenReturn("VALID");

        mockMvc.perform(get("/api/invoice/1/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("VALID"));
    }
//    @Test
//    void testMalformedData() throws Exception {
//        String jsonRequest = "{ \"invoiceId\": \"123\", \"amount\": -100.0, \"date\": \"2024-12-01\", \"transactions\": [] }";
//
//        mockMvc.perform(post("/invoice")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Invoice amount must be greater than zero"));
//    }
//
//    @Test
//    void testInvalidInput() throws Exception {
//        String jsonRequest = "{ \"invoiceId\": \"123\", \"amount\": 100.0, \"date\": \"2024-12-01\", \"transactions\": [] }";
//
//        mockMvc.perform(post("/invoice")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(jsonPath("$.message").value("No transactions found for the invoice"));
//    }
}
