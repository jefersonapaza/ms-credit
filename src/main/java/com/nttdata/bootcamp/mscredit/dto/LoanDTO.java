package com.nttdata.bootcamp.mscredit.dto;

import lombok.Data;

@Data
public class LoanDTO {

    private String loanCode;
    private Long clientId;
    private Double totalDebt;
    private Integer installments;

}
