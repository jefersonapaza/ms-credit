package com.nttdata.bootcamp.bankaccount.mscredit.service;

import com.nttdata.bootcamp.bankaccount.mscredit.bean.PaymentBean;
import reactor.core.publisher.Mono;

public interface IPaymentService {
    Mono<PaymentBean> save(PaymentBean credit);
}
