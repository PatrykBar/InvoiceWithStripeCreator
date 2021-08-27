package com.example.Invoice.service;

import com.example.Invoice.model.MyCustomer;
import com.example.Invoice.model.MyInvoice;
import com.example.Invoice.model.MyProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceTest {
//
////    @Mock - it doesn't work
//    private InvoiceService invoiceService;
//
//    private MyInvoice myInvoice;
//    private MyCustomer myCustomer;
//    private MyProduct myProduct;
//
//    @BeforeEach
//    void setUp() {
//        myInvoice = new MyInvoice();
//        myCustomer = new MyCustomer();
//        myProduct = new MyProduct();
//    }
//
//    @Test
//    void shouldAddInvoice() {
//        //Given
//        myCustomer.setCustomerName("Mane");
//        myCustomer.setDescription("Testing Customer");
//        myCustomer.setEmail("jakistammail@wp.pl");
//        myInvoice.setMyCustomer(myCustomer);
//        myInvoice.setMyProduct(myProduct);
//
//        //When
//        boolean result = invoiceService.addInvoice(myInvoice, "Test Adding Invoice");
//
//        //Then
//        assertThat(result, equalTo(true));
//    }
//
//    @Test
//    void shouldFindAllInvoice() {
//        //Given
//        //When
//        List<Object> invoiceList = invoiceService.findAllInvoice();
//
//        //Then
//        assertThat(invoiceList.size(), equalTo());
//    }
//
//    @Test
//    void shouldCheckUserEmail(){
//        //Given
//        myCustomer.setCustomerName("Mane");
//        myCustomer.setDescription("Testing Customer");
//        myCustomer.setEmail("WrongEmail.pl");
//
//        myInvoice.setMyCustomer(myCustomer);
//        myInvoice.setMyProduct(myProduct);
//
//        //When
//        //Then
//        assertThrows(IllegalArgumentException.class, () -> invoiceService.addInvoice(myInvoice, "Testing customer email checker"));
//    }
}