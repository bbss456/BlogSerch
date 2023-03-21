package com.pwang.blog;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class test {

    @Test
    void test() {
        String a = "20230315";
        LocalDate localDate2 = LocalDate.parse("20230315", DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(localDate2);
    }

}
