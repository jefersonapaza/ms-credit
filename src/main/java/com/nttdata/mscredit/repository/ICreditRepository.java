package com.nttdata.mscredit.repository;

import com.nttdata.mscredit.bean.CreditBean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ICreditRepository extends ReactiveMongoRepository<CreditBean, String> {

    Flux<CreditBean> findAllByIdCustomer(String idCustomer);
}
