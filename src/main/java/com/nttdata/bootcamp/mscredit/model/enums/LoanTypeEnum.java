package com.nttdata.bootcamp.mscredit.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum LoanTypeEnum {

    PERSONAL(0),
    BUSINESS(1);

    private int value;
    private static Map map = new HashMap();

    private LoanTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (LoanTypeEnum loanType : LoanTypeEnum.values()) {
            map.put(loanType.value, loanType);
        }
    }

    public static LoanTypeEnum valueOf(int loanType) {
        return (LoanTypeEnum) map.get(loanType);
    }

    public int getValue() {
        return value;
    }

}
