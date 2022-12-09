package com.nttdata.bootcamp.mscredit.model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "loanpayment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanPayment {

    @Transient
    public static final String SEQUENCE_NAME = "loanpayment_sequence";

    @Id
    private Long id;
    @NonNull
    private Long loanId;
    @NonNull
    private String description;
    @NonNull
    private Double amount;
    @NonNull
    private Double newPendingDebt;
    @NonNull
    private LocalDateTime transactionDate;

}
