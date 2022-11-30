package com.reservation.proxy;

import com.reservation.proxy.model.payment.ICard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ZuulApiGateway")
public interface IPaymentServiceProxy {

    @PostMapping(value = "/PaymentService/payments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String doPayment(@RequestBody ICard card,@RequestParam("amount") double amount);

    @PostMapping(value = "/PaymentService/payments/revert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String revertPayment(@RequestBody ICard card,@RequestParam("amount") double amount);

}
