package com.nttdata.bootcamp.bankaccount.mscredit.repository;

import com.nttdata.bootcamp.bankaccount.mscredit.bean.PaymentBean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository  extends ReactiveMongoRepository<PaymentBean, String> {
}
