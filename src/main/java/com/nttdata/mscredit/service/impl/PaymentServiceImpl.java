package com.nttdata.mscredit.service.impl;

import com.nttdata.mscredit.bean.PaymentBean;
import com.nttdata.mscredit.repository.IPaymentRepository;
import com.nttdata.mscredit.service.IPaymentService;
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
