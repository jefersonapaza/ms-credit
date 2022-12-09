package com.nttdata.bootcamp.mscredit.service.impl;

import com.nttdata.bootcamp.mscredit.dto.LoanPaymentDTO;
import com.nttdata.bootcamp.mscredit.infrastructure.LoanPaymentRepository;
import com.nttdata.bootcamp.mscredit.mapper.LoanPaymentDTOMapper;
import com.nttdata.bootcamp.mscredit.model.LoanPayment;
import com.nttdata.bootcamp.mscredit.service.DatabaseSequenceService;
import com.nttdata.bootcamp.mscredit.service.LoanPaymentService;
import com.nttdata.bootcamp.mscredit.service.LoanService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class LoanPaymentServiceImpl implements LoanPaymentService {

    @Autowired
    private LoanPaymentRepository loanPaymentRepository;

    @Autowired
    private LoanService loanService;

    @Autowired
    private DatabaseSequenceService databaseSequenceService;

    private LoanPaymentDTOMapper loanPaymentDTOMapper = new LoanPaymentDTOMapper();

    @Override
    public Flux<LoanPayment> findAll() {
        log.info("Listing all loan payments");
        return loanPaymentRepository.findAll();
    }

    @Override
    public Mono<LoanPayment> create(LoanPayment loanPayment) {
        log.info("Creating loan payment: " + loanPayment.toString());
        return loanPaymentRepository.save(loanPayment);
    }

    @Override
    public Mono<LoanPayment> findById(Long id) {
        log.info("Searching loan payment by id: " + id);
        return loanPaymentRepository.findById(id);
    }

    @Override
    public Mono<LoanPayment> update(Long id, LoanPayment loanPayment) {
        log.info("Updating loan payment with id: " + id + " with : " + loanPayment.toString());
        return loanPaymentRepository.findById(id)
                .flatMap(l -> {
                    loanPayment.setId(id);
                    return loanPaymentRepository.save(loanPayment);
                });
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.info("Deleting loan payment with id: " + id);
        return loanPaymentRepository.deleteById(id);
    }

    @Override
    public Mono<String> createLoanPayment(LoanPaymentDTO loanPaymentDTO) {
        log.info("Creating loan payment: " + loanPaymentDTO.toString());
        LoanPayment loanPayment = loanPaymentDTOMapper.convertToEntity(loanPaymentDTO);
        return checkFields(loanPaymentDTO)
                .switchIfEmpty(loanService.findById(loanPayment.getLoanId()).flatMap(cc -> {
                    cc.setPendingDebt(cc.getPendingDebt() - loanPayment.getAmount());
                    if (cc.getPendingDebt() < 0) {
                        return Mono.error(new IllegalArgumentException("Loan payment exceeds pending debt"));
                    }
                    loanPayment.setNewPendingDebt(cc.getPendingDebt());
                    return loanService.update(cc.getId(), cc)
                            .flatMap(l ->
                                    databaseSequenceService.generateSequence(LoanPayment.SEQUENCE_NAME).flatMap(sequence -> {
                                        loanPayment.setId(sequence);
                                        return loanPaymentRepository.save(loanPayment)
                                                .flatMap(lp -> Mono.just("Loan payment done, new pending debt: " + cc.getPendingDebt()));
                                    }));
                }).switchIfEmpty(Mono.error(new IllegalArgumentException("Loan not found"))));
    }

    @Override
    public Flux<LoanPayment> findAllByLoanId(Long id) {
        log.info("Listing all loan payments by loan id");
        return loanPaymentRepository.findAllByLoanId(id);
    }

    @Override
    public Mono<String> checkFields(LoanPaymentDTO loanPayment) {
        if (loanPayment.getDescription() == null || loanPayment.getDescription().trim().equals("")) {
            return Mono.error(new IllegalArgumentException("Loan payment description cannot be empty"));
        }
        if (loanPayment.getAmount() == null || loanPayment.getAmount() <= 0) {
            return Mono.error(new IllegalArgumentException("Loan payment amount must be greater than 0"));
        }
        return Mono.empty();
    }
}
