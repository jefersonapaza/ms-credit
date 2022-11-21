package com.nttdata.bootcamp.bankaccount.mscredit.controller;

import com.nttdata.bootcamp.bankaccount.mscredit.bean.CreditBean;
import com.nttdata.bootcamp.bankaccount.mscredit.bean.PaymentBean;
import com.nttdata.bootcamp.bankaccount.mscredit.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class PaymentController {

    @Autowired
    private IPaymentService service;

    @PostMapping("customerPayCredit")
    public Mono<PaymentBean> register(@RequestBody PaymentBean payment) {
        return service.save(payment);
    }
}
