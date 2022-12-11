package com.nttdata.mscredit.service.external;

import com.nttdata.mscredit.dto.external.CustomerDTO;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Mono<CustomerDTO> findById(String id);
}
