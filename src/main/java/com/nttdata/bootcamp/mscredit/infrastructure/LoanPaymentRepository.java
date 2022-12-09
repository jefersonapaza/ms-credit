package com.nttdata.bootcamp.mscredit.infrastructure;

import com.nttdata.bootcamp.mscredit.model.LoanPayment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface LoanPaymentRepository extends ReactiveMongoRepository<LoanPayment, Long> {

    Flux<LoanPayment> findAllByLoanId(Long id);

}
