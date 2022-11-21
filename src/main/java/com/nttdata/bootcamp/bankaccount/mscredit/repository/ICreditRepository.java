package com.nttdata.bootcamp.bankaccount.mscredit.repository;

import com.nttdata.bootcamp.bankaccount.mscredit.bean.CreditBean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ICreditRepository extends ReactiveMongoRepository<CreditBean, String> {

    Flux<CreditBean> findAllByIdCustomer(String idCustomer);
}
