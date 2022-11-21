package com.nttdata.bootcamp.bankaccount.mscredit.service.impl;

import com.nttdata.bootcamp.bankaccount.mscredit.bean.CreditBean;
import com.nttdata.bootcamp.bankaccount.mscredit.repository.ICreditRepository;
import com.nttdata.bootcamp.bankaccount.mscredit.service.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements ICreditService {

    @Autowired
    private ICreditRepository repo;

    @Override
    public Flux<CreditBean> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<CreditBean> save(CreditBean credit) {
        return repo.save(credit);
    }

    @Override
    public Mono<CreditBean> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public Flux<CreditBean> findAllByIdCustomer(String idCustomer) {
        return repo.findAllByIdCustomer(idCustomer);
    }
}
