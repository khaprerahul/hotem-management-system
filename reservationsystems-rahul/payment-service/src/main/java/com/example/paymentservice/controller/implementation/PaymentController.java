package com.example.paymentservice.controller.implementation;

import com.example.paymentservice.controller.IPaymentController;
import com.example.paymentservice.model.ICard;
import com.example.paymentservice.service.IPaymentService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class PaymentController implements IPaymentController {

    @Inject
    private IPaymentService paymentService;

    @Override
    public String doPayment(ICard card,double amount){
        return paymentService.doPayment(card, amount);
    }

    @Override
    public String revertPayment(ICard card,double amount){
        return paymentService.revertPayment(card, amount);
    }

}
