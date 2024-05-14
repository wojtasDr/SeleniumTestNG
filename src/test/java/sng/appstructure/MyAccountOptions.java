package sng.appstructure;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum MyAccountOptions {
    ADD_MY_FIRST_ADDRESS("ADD MY FIRST ADDRESS"),
    ORDER_HISTORY_AND_DETAILS("ORDER HISTORY AND DETAILS"),
    MY_CREDIT_SLIPS("MY CREDIT SLIPS"),
    MY_ADDRESSES("MY ADDRESSES"),
    MY_PERSONAL_INFORMATION("MY PERSONAL INFORMATION");

    @Getter
    private final String optionName;

    MyAccountOptions(String optionName) {
        this.optionName = optionName;
    }

    public static List<String> getAllOptionsTextList() {
        return Arrays.stream(MyAccountOptions.values()).map(MyAccountOptions::getOptionName).toList();
    }
}
