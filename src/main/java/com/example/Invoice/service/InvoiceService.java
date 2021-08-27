package com.example.Invoice.service;


import com.example.Invoice.model.MyCustomer;
import com.example.Invoice.model.MyInvoice;
import com.example.Invoice.model.MyProduct;
import com.stripe.Stripe;
import com.stripe.model.*;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.InvoiceItemCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    @SneakyThrows
    public String addInvoice(MyInvoice myInvoice, String description) {
        MyCustomer myCustomer = myInvoice.getMyCustomer();
        MyProduct product = myInvoice.getMyProduct();
        if(checkUserEmail(myCustomer.getEmail())){

            Map<String, Object> paramsForCustomer = new HashMap<>();
            paramsForCustomer.put("email", myCustomer.getEmail());
            paramsForCustomer.put("name", myCustomer.getCustomerName());
            paramsForCustomer.put("description", myCustomer.getDescription());
            Customer customer = Customer.create(paramsForCustomer);

            InvoiceItemCreateParams paramsForInvoiceItem =
                    InvoiceItemCreateParams.builder()
                            .setPrice(createPrice(product).getId())
                            .setCustomer(customer.getId())
                            .build();
            InvoiceItem.create(paramsForInvoiceItem);

            InvoiceCreateParams paramsForInvoice =
                    InvoiceCreateParams.builder()
                            .setCustomer(customer.getId())
                            .setCollectionMethod(InvoiceCreateParams.CollectionMethod.SEND_INVOICE)
                            .setDaysUntilDue(30L)
                            .build();

            Invoice.create(paramsForInvoice);

            return "created";
        }else{
            return "Bad email address!";
        }
    }

    @SneakyThrows
    public List<Object> findAllInvoice() {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 5);
        InvoiceCollection invoices = Invoice.list(params);

        return Collections.singletonList(invoices.getData());
    }

    @SneakyThrows
    private Price createPrice(MyProduct product){

        Map<String, Object> paramsForProduct = new HashMap<>();
        paramsForProduct.put("name", product.getName());
        Product productFromStripe = Product.create(paramsForProduct);

        Map<String, Object> paramsForPrice = new HashMap<>();
        paramsForPrice.put("unit_amount", product.getPrice());
        paramsForPrice.put("currency", product.getCurrency());
        paramsForPrice.put("product", productFromStripe.getId());

        return Price.create(paramsForPrice);
    }

    private boolean checkUserEmail(String eMail){
        if (eMail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|" +
                "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|" +
                "\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|" +
                "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
            return true;
        }else {
            return false;
//            throw new IllegalArgumentException("Wrong email format");
        }
    };

}
