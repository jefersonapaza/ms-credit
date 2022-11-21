package com.nttdata.bootcamp.bankaccount.mscredit.controller;

import com.nttdata.bootcamp.bankaccount.mscredit.bean.CreditBean;
import com.nttdata.bootcamp.bankaccount.mscredit.service.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/credits")
public class CreditController {

    @Autowired
    private ICreditService service;

    @PostMapping("save")
    public Mono<CreditBean> register(@RequestBody CreditBean account) {
        return service.save(account);
    }

    @GetMapping("list")
    public Flux<CreditBean> findAll() {
        return service.findAll();
    }

    @GetMapping("getByCustomer/{id}")
    public Flux<CreditBean> findByIdCustomer(@PathVariable("id")String id) {
        return service.findAllByIdCustomer(id);
    }

    @PutMapping("customerSetCredit/{id}")
    public Mono<CreditBean> setCredit(@RequestBody CreditBean account, @PathVariable("id")String id) {
        account.setId(id);
        return service.save(account);
    }
}
