package com.nttdata.bootcamp.mscredit.service;

import com.nttdata.bootcamp.mscredit.dto.ClientDTO;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<ClientDTO> findById(Long id);

}
