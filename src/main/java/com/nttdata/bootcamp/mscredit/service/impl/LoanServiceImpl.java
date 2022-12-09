package com.nttdata.bootcamp.mscredit.service.impl;

import com.nttdata.bootcamp.mscredit.dto.LoanDTO;
import com.nttdata.bootcamp.mscredit.infrastructure.LoanRepository;
import com.nttdata.bootcamp.mscredit.mapper.LoanDTOMapper;
import com.nttdata.bootcamp.mscredit.model.Loan;
import com.nttdata.bootcamp.mscredit.model.enums.ClientTypeEnum;
import com.nttdata.bootcamp.mscredit.model.enums.LoanTypeEnum;
import com.nttdata.bootcamp.mscredit.service.ClientService;
import com.nttdata.bootcamp.mscredit.service.DatabaseSequenceService;
import com.nttdata.bootcamp.mscredit.service.LoanService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DatabaseSequenceService databaseSequenceService;

    private LoanDTOMapper loanDTOMapper = new LoanDTOMapper();

    @Override
    public Flux<Loan> findAll() {
        log.info("Listing all loans");
        return loanRepository.findAll();
    }

    @Override
    public Mono<Loan> create(Loan loan) {
        log.info("Creating loan: " + loan.toString());
        return loanRepository.save(loan);
    }

    @Override
    public Mono<Loan> findById(Long id) {
        log.info("Searching loan by id: " + id);
        return loanRepository.findById(id);
    }

    @Override
    public Mono<Loan> update(Long id, Loan loan) {
        log.info("Updating loan with id: " + id + " with : " + loan.toString());
        return loanRepository.findById(id)
                .flatMap(l -> {
                    loan.setId(id);
                    return loanRepository.save(loan);
                });
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.info("Deleting loan with id: " + id);
        return loanRepository.deleteById(id);
    }

    @Override
    public Mono<String> createPersonalLoan(LoanDTO loanDTO) {
        log.info("Creating personal loan: " + loanDTO.toString());
        Loan loan = loanDTOMapper.convertToEntity(loanDTO, LoanTypeEnum.PERSONAL);
        return checkFields(loanDTO)
                .switchIfEmpty(clientService.findById(loanDTO.getClientId())
                        .flatMap(c -> {
                            switch (ClientTypeEnum.valueOf(c.getClientType())) {
                                case PERSONAL:
                                    return findAllByClientId(c.getId()).count().flatMap(l -> (l < 1) ?
                                            databaseSequenceService.generateSequence(Loan.SEQUENCE_NAME).flatMap(sequence -> {
                                                loan.setId(sequence);
                                                return loanRepository.save(loan)
                                                        .flatMap(lo -> Mono.just("Personal Loan created! " + loanDTOMapper.convertToDto(lo)));
                                            })
                                            : Mono.error(new IllegalArgumentException("Personal clients can have only one personal loan")));
                                case BUSINESS:
                                    return Mono.error(new IllegalArgumentException("Only personal clients can have a personal loan"));
                                default:
                                    return Mono.error(new IllegalArgumentException("Invalid client type"));
                            }
                        })
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Client not found"))));
    }

    @Override
    public Mono<String> createBusinessLoan(LoanDTO loanDTO) {
        log.info("Creating business loan: " + loanDTO.toString());
        Loan loan = loanDTOMapper.convertToEntity(loanDTO, LoanTypeEnum.BUSINESS);
        return checkFields(loanDTO)
                .switchIfEmpty(clientService.findById(loanDTO.getClientId())
                        .flatMap(c -> {
                            switch (ClientTypeEnum.valueOf(c.getClientType())) {
                                case PERSONAL:
                                    return Mono.error(new IllegalArgumentException("Only business clients can have business loans"));
                                case BUSINESS:
                                    return databaseSequenceService.generateSequence(Loan.SEQUENCE_NAME).flatMap(sequence -> {
                                        loan.setId(sequence);
                                        return loanRepository.save(loan)
                                                .flatMap(lo -> Mono.just("Business Loan created! " + loanDTOMapper.convertToDto(lo)));
                                    });
                                default:
                                    return Mono.error(new IllegalArgumentException("Invalid client type"));
                            }
                        })
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Client not found"))));
    }

    @Override
    public Flux<Loan> findAllByClientId(Long id) {
        log.info("Listing all loans by client id");
        return loanRepository.findAllByClientId(id);
    }

    @Override
    public Mono<String> checkFields(LoanDTO loan) {
        if (loan.getLoanCode() == null || loan.getLoanCode().trim().equals("")) {
            return Mono.error(new IllegalArgumentException("Loan code cannot be empty"));
        }
        if (loan.getTotalDebt() == null || loan.getTotalDebt() <= 0) {
            return Mono.error(new IllegalArgumentException("Loan total debt must be greater than 0"));
        }
        if (loan.getInstallments() == null || loan.getInstallments() <= 0) {
            return Mono.error(new IllegalArgumentException("Loan installments must be greater than 0"));
        }
        return Mono.empty();
    }

}
