package com.example.Invoice.controllers;

import com.example.Invoice.model.MyCustomer;
import com.example.Invoice.model.MyInvoice;
import com.example.Invoice.model.MyProduct;
import com.example.Invoice.service.InvoiceService;
import com.stripe.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/getAllInvoices")
    public ResponseEntity<List<Object>> getAllInvoice(){
        if (invoiceService.findAllInvoice().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(invoiceService.findAllInvoice(), HttpStatus.OK);
        }
    }

    @PostMapping("/createInvoice")
    public ResponseEntity<MyInvoice> createInvoice(@RequestBody MyInvoice myInvoice,
                                                    String description) {
        if (invoiceService.addInvoice(myInvoice, description).equals("created")){
            return new ResponseEntity<>(myInvoice, HttpStatus.CREATED);
        }else if (invoiceService.addInvoice(myInvoice, description).equals("Bad email address!")){
            return new ResponseEntity<>(myInvoice, HttpStatus.NOT_ACCEPTABLE);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
