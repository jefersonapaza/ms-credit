package com.nttdata.mscredit.repository;

import com.nttdata.mscredit.bean.PaymentBean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository  extends ReactiveMongoRepository<PaymentBean, String> {
}
