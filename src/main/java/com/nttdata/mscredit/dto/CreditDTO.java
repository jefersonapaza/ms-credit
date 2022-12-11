package com.nttdata.mscredit.dto;


import lombok.*;
import reactor.core.publisher.Mono;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditDTO {

    private String numberAccount;
    private Float amount;
    private String idCustomer;
    private int creditType;

    public Mono<String> validator(){
        if(this.numberAccount == null || this.numberAccount.trim().equals("")){
            return Mono.error(new IllegalArgumentException("Number Account cannot be empty"));
        }
        if(this.amount == null || this.amount == 0F){
            return Mono.error(new IllegalArgumentException("Amount cannot be empty or zero "));
        }
        if(this.idCustomer == null || this.idCustomer.trim().equals("")){
            return Mono.error(new IllegalArgumentException("Customer cannot be empty"));
        }
        if(this.numberAccount == null || this.numberAccount.trim().equals("")){
            return Mono.error(new IllegalArgumentException("Number Account cannot be empty"));
        }
        return Mono.empty();
    }
}
