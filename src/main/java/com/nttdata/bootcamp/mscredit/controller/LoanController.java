package com.nttdata.bootcamp.mscredit.controller;

import com.nttdata.bootcamp.mscredit.dto.LoanDTO;
import com.nttdata.bootcamp.mscredit.model.Loan;
import com.nttdata.bootcamp.mscredit.service.LoanService;
import com.nttdata.bootcamp.mscredit.service.impl.LoanServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/bootcamp/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping(value = "/findAllLoans")
    @ResponseBody
    public Flux<Loan> findAllLoans() {
        return loanService.findAll();
    }

    @PostMapping(value = "/createPersonalLoan")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> createPersonalLoan(@RequestBody LoanDTO loanDTO) {
        return loanService.createPersonalLoan(loanDTO);
    }

    @PostMapping(value = "/createBusinessLoan")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> createBusinessLoan(@RequestBody LoanDTO loanDTO) {
        return loanService.createBusinessLoan(loanDTO);
    }

    @GetMapping(value = "/find/{id}")
    @ResponseBody
    public Mono<ResponseEntity<Loan>> findLoanById(@PathVariable Long id) {
        return loanService.findById(id)
                .map(loan -> ResponseEntity.ok().body(loan))
                .onErrorResume(e -> {
                    log.info("Loan not found " + id, e);
                    return Mono.just(ResponseEntity.badRequest().build());
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public Mono<ResponseEntity<Loan>> updateLoan(@PathVariable Long id, @RequestBody Loan loan) {
        return loanService.update(id, loan)
                .map(a -> new ResponseEntity<>(a, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public Mono<Void> deleteByIdLoan(@PathVariable Long id) {
        return loanService.delete(id)
                .defaultIfEmpty(null);
    }

    @GetMapping(value = "/findAllByClientId/{id}")
    @ResponseBody
    public Flux<Loan> findAllByClientId(@PathVariable Long id) {
        return loanService.findAllByClientId(id);
    }

}
