package com.nttdata.bootcamp.bankaccount.mscredit.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {

    private Float amount;
    private String accountName;
    private String idCustomer;
    private String idCredit;

}
