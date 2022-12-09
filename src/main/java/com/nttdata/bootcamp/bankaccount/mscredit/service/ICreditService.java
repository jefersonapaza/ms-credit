package com.nttdata.bootcamp.bankaccount.mscredit.service;

import com.nttdata.bootcamp.bankaccount.mscredit.bean.CreditBean;
import com.nttdata.bootcamp.bankaccount.mscredit.dto.CreditDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {
    Flux<CreditBean> findAll();
    Mono<CreditBean> save(CreditBean credit);
    Mono<CreditBean> findById(String id);
    Flux<CreditBean> findAllByIdCustomer(String idCustomer);

    Mono<String> savePersonalCredit(CreditDTO creditDTO);
    Mono<String> saveBusinessCredit(CreditDTO creditDTO);

}
