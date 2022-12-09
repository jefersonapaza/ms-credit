package com.nttdata.bootcamp.bankaccount.mscredit.dto.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerDTO {
    private String id;
    private String name;
    private String type;
}
