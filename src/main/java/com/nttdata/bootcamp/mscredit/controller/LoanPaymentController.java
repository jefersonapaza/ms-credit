package com.nttdata.bootcamp.mscredit.controller;

import com.nttdata.bootcamp.mscredit.dto.LoanPaymentDTO;
import com.nttdata.bootcamp.mscredit.model.LoanPayment;
import com.nttdata.bootcamp.mscredit.service.LoanPaymentService;
import com.nttdata.bootcamp.mscredit.service.impl.LoanPaymentServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/bootcamp/loanPayment")
public class LoanPaymentController {

    @Autowired
    LoanPaymentService loanPaymentService;

    @GetMapping(value = "/findAllLoanPayments")
    @ResponseBody
    public Flux<LoanPayment> findAllLoanPayments() {
        return loanPaymentService.findAll();
    }

    @PostMapping(value = "/createLoanPayment")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> createLoanPayment(@RequestBody LoanPaymentDTO loanPaymentDTO) {
        return loanPaymentService.createLoanPayment(loanPaymentDTO);
    }

    @GetMapping(value = "/find/{id}")
    @ResponseBody
    public Mono<ResponseEntity<LoanPayment>> findLoanPaymentById(@PathVariable Long id) {
        return loanPaymentService.findById(id)
                .map(creditCard -> ResponseEntity.ok().body(creditCard))
                .onErrorResume(e -> {
                    log.info("Loan Payment not found " + id, e);
                    return Mono.just(ResponseEntity.badRequest().build());
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public Mono<ResponseEntity<LoanPayment>> updateLoanPayment(@PathVariable Long id, @RequestBody LoanPayment loanPayment) {
        return loanPaymentService.update(id, loanPayment)
                .map(a -> new ResponseEntity<>(a, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public Mono<Void> deleteByIdLoanPayment(@PathVariable Long id) {
        return loanPaymentService.delete(id)
                .defaultIfEmpty(null);
    }

    @GetMapping(value = "/findAllByLoanId/{id}")
    @ResponseBody
    public Flux<LoanPayment> findAllByLoanId(@PathVariable Long id) {
        return loanPaymentService.findAllByLoanId(id);
    }
}
