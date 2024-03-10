package com.teachmeskills.final_assignment.util.consts.regex;

public interface Regex {
    String CHECK_REGEX = "^2023_Electric_Bill_[0-9]{2,}[.]{1}txt$";
    String ORDER_REGEX = "^2023_order_[0-9]{2,}[.]{1}txt$";
    String INVOICE_REGEX = "^INVOICE_[0-9]{2}_2023\\.txt$)|(^[a-z]{7}_[0-9]{2}_2023\\.txt$)";
}
