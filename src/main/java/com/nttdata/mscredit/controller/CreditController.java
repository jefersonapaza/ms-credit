package com.nttdata.mscredit.controller;

import com.nttdata.mscredit.bean.CreditBean;
import com.nttdata.mscredit.dto.CreditDTO;
import com.nttdata.mscredit.service.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/credits")
public class CreditController {

    @Autowired
    private ICreditService creditService;


    @PostMapping("/save")
    public Mono<CreditBean> register(@RequestBody CreditBean account) {
        return creditService.save(account);
    }

    @GetMapping("/list")
    public Flux<CreditBean> findAll() {
        return creditService.findAll();
    }

    @GetMapping("/getByCustomer/{id}")
    public Flux<CreditBean> findByIdCustomer(@PathVariable("id")String id) {
        return creditService.findAllByIdCustomer(id);
    }

    @PutMapping("/customerSetCredit/{id}")
    public Mono<CreditBean> setCredit(@RequestBody CreditBean account, @PathVariable("id")String id) {
        account.setId(id);
        return creditService.save(account);
    }

    @PostMapping(value = "/savePersonalCredit")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> savePersonalCredit(@RequestBody CreditDTO creditDTO){
        return creditDTO.validator()
                .switchIfEmpty(creditService.savePersonalCredit(creditDTO));
    }

    @PostMapping(value = "/saveBusinessCredit")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> saveBusinessCredit(@RequestBody CreditDTO creditDTO){
        return creditDTO.validator()
                .switchIfEmpty(creditService.saveBusinessCredit(creditDTO));
    }





}
