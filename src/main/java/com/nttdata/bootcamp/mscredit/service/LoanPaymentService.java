package com.nttdata.bootcamp.mscredit.service;

import com.nttdata.bootcamp.mscredit.dto.LoanPaymentDTO;
import com.nttdata.bootcamp.mscredit.model.LoanPayment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoanPaymentService {

    Flux<LoanPayment> findAll();

    Mono<LoanPayment> create(LoanPayment loanPayment);

    Mono<LoanPayment> findById(Long id);

    Mono<LoanPayment> update(Long id, LoanPayment loanPayment);

    Mono<Void> delete(Long id);

    Mono<String> createLoanPayment(LoanPaymentDTO loanPaymentDTO);

    Flux<LoanPayment> findAllByLoanId(Long id);

    Mono<String> checkFields(LoanPaymentDTO loanPayment);

}
