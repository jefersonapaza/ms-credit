package com.nttdata.bootcamp.mscredit.mapper;

import com.nttdata.bootcamp.mscredit.dto.LoanDTO;
import com.nttdata.bootcamp.mscredit.model.Loan;
import com.nttdata.bootcamp.mscredit.model.enums.LoanTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class LoanDTOMapper {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public LoanDTO convertToDto(Loan loan) {
        return modelMapper.map(loan, LoanDTO.class);
    }

    public Loan convertToEntity(LoanDTO loanDTO, LoanTypeEnum type) {
        Loan creditCard = modelMapper.map(loanDTO, Loan.class);
        creditCard.setLoanType(type.ordinal());
        creditCard.setPendingDebt(loanDTO.getTotalDebt());
        creditCard.setLoanDate(LocalDateTime.now());
        return creditCard;
    }

}
