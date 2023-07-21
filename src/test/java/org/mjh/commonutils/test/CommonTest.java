package org.mjh.commonutils.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Neo Lia
 * @date 2023/7/5 17:03
 */
public class CommonTest {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.255");
        BigDecimal b = new BigDecimal("1.256");
        System.out.println(a.setScale(2, RoundingMode.DOWN).toPlainString());
        System.out.println(b.setScale(2, RoundingMode.DOWN).toPlainString());
    }
}
