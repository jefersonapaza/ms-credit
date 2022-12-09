package com.nttdata.bootcamp.bankaccount.mscredit.service.external;

import com.nttdata.bootcamp.bankaccount.mscredit.dto.external.CustomerDTO;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Mono<CustomerDTO> findById(String id);
}
