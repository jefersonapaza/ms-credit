package com.nttdata.mscredit.service.impl;

import com.nttdata.mscredit.bean.CreditBean;
import com.nttdata.mscredit.dto.CreditDTO;
import com.nttdata.mscredit.repository.ICreditRepository;
import com.nttdata.mscredit.service.ICreditService;
import com.nttdata.mscredit.service.external.ICustomerService;
import com.nttdata.mscredit.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements ICreditService {

    @Autowired
    private ICreditRepository creditRepository;

    @Autowired
    private ICustomerService customerService;

    @Override
    public Flux<CreditBean> findAll() {
        return creditRepository.findAll();
    }

    @Override
    public Mono<CreditBean> save(CreditBean credit) {
        return creditRepository.save(credit);
    }

    @Override
    public Mono<CreditBean> findById(String id) {
        return creditRepository.findById(id);
    }

    @Override
    public Flux<CreditBean> findAllByIdCustomer(String idCustomer) {
        return creditRepository.findAllByIdCustomer(idCustomer);
    }

    @Override
    public Mono<String> savePersonalCredit(CreditDTO creditDTO) {

        CreditBean creditBean = new CreditBean();
        return customerService.findById(creditDTO.getIdCustomer())
                .flatMap(customer -> {
                    if(customer.getType().equalsIgnoreCase(Constants.PERSONAL)){
                        return this.findAllByIdCustomer(customer.getId())
                                .count().flatMap(c -> (c < 1 )
                                      ? Mono.just(creditBean).flatMap( bean -> {
                                            bean.setCreditType(creditDTO.getCreditType());
                                            bean.setActive(true);
                                            bean.setAmount(creditDTO.getAmount());
                                            bean.setIdCustomer(creditDTO.getIdCustomer());
                                            bean.setNumberAccount(creditDTO.getNumberAccount());
                                            return creditRepository.save(bean).flatMap(cb -> Mono.just("Personal Credit Created ! "));
                                        })
                                            : Mono.error(new IllegalArgumentException("")));

                    }
                    return Mono.error(new IllegalArgumentException("It is not Personal Customer"));
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Customer not found")));

    }

    @Override
    public Mono<String> saveBusinessCredit(CreditDTO creditDTO) {

        CreditBean creditBean = new CreditBean();
        return customerService.findById(creditDTO.getIdCustomer())
                .flatMap(customer -> {
                    if(customer.getType().equalsIgnoreCase(Constants.BUSINESS)){
                        return this.findAllByIdCustomer(customer.getId())
                                .count().flatMap(c -> (c < 1 )
                                        ? Mono.just(creditBean).flatMap( bean -> {
                                    bean.setCreditType(creditDTO.getCreditType());
                                    bean.setActive(true);
                                    bean.setAmount(creditDTO.getAmount());
                                    bean.setIdCustomer(creditDTO.getIdCustomer());
                                    bean.setNumberAccount(creditDTO.getNumberAccount());
                                    return creditRepository.save(bean).flatMap(cb -> Mono.just("Business Credit Created ! "));
                                })
                                        : Mono.error(new IllegalArgumentException("")));

                    }
                    return Mono.error(new IllegalArgumentException("It is not Business Customer"));
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Customer not found")));

    }


}
