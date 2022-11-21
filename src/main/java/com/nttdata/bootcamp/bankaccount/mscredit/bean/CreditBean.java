package com.nttdata.bootcamp.bankaccount.mscredit.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credits")
public class CreditBean {

    @Id
    private String id;
    private String numberAccount;
    private Float amount;
    private String idCustomer;
    private boolean isActive;
    private int creditType;
}
