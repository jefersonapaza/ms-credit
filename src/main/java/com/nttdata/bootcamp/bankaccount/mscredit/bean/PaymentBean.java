package com.nttdata.bootcamp.bankaccount.mscredit.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
public class PaymentBean {
    private String id;
    private Float amount;
    private String accountName;
    private String idCustomer;
    private String idCredit;
}
