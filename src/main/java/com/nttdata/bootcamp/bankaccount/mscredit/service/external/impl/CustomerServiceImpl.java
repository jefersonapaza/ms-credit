package com.nttdata.bootcamp.bankaccount.mscredit.service.external.impl;


import com.nttdata.bootcamp.bankaccount.mscredit.dto.external.CustomerDTO;
import com.nttdata.bootcamp.bankaccount.mscredit.service.external.ICustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class CustomerServiceImpl implements ICustomerService {


    @Override
    public Mono<CustomerDTO> findById(String id) {
        return null;
    }
}
