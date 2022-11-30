package com.example.paymentservice.controller;

import com.example.paymentservice.model.ICard;
import com.example.paymentservice.service.IPaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@RequestMapping("/payments")
public interface IPaymentController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String doPayment(@RequestBody ICard card, @RequestParam("amount") double amount);

    @PostMapping(value = "/revert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String revertPayment(@RequestBody ICard card,@RequestParam("amount") double amount);

}
