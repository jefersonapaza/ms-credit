package com.nttdata.bootcamp.mscredit.dto;

import lombok.Data;

@Data
public class LoanPaymentDTO {

    private Long loanId;
    private String description;
    private Double amount;

}
