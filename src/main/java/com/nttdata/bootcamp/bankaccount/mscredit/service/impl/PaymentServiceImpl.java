package com.nttdata.bootcamp.bankaccount.mscredit.service.impl;

import com.nttdata.bootcamp.bankaccount.mscredit.bean.PaymentBean;
import com.nttdata.bootcamp.bankaccount.mscredit.repository.IPaymentRepository;
import com.nttdata.bootcamp.bankaccount.mscredit.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private IPaymentRepository repo;

    @Override
    public Mono<PaymentBean> save(PaymentBean payment) {
        return repo.save(payment);
    }
}
