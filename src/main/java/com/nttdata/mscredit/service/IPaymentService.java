package com.nttdata.mscredit.service;

import com.nttdata.mscredit.bean.PaymentBean;
import reactor.core.publisher.Mono;

public interface IPaymentService {
    Mono<PaymentBean> save(PaymentBean credit);
}
