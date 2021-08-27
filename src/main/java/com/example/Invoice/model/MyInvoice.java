package com.example.Invoice.model;

import lombok.Data;

@Data
public class MyInvoice {

    private MyCustomer myCustomer;
    private MyProduct myProduct;

}
