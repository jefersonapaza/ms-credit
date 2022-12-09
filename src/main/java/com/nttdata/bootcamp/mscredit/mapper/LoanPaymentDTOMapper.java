package com.nttdata.bootcamp.mscredit.mapper;

import com.nttdata.bootcamp.mscredit.dto.LoanPaymentDTO;
import com.nttdata.bootcamp.mscredit.model.LoanPayment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class LoanPaymentDTOMapper {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public LoanPaymentDTO convertToDto(LoanPayment loanPayment){
        return modelMapper.map(loanPayment, LoanPaymentDTO.class);
    }
    public LoanPayment convertToEntity(LoanPaymentDTO loanPaymentDTO) {
        LoanPayment loanPayment = modelMapper.map(loanPaymentDTO, LoanPayment.class);
        loanPayment.setTransactionDate(LocalDateTime.now());
        return loanPayment;
    }
}
