package xyz.lhweb.furns.test;

import java.math.BigInteger;

/**
 * 主要
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BigInteger factorialsSum = new BigInteger("0"); // 初始化一个 BigInteger 存放阶乘和
        for (int i = 1; i <= 100; i++) { // 注意需要用 long 类型，否则会溢出
            BigInteger factorial = new BigInteger("1");; // 初始化一个 BigInteger 存放阶乘
            // 计算阶乘
            for (int j = 1; j <= i; j++) {
                factorial = factorial.multiply(BigInteger.valueOf(j));
            }
            // 将阶乘加到 sum 上
            factorialsSum = factorialsSum.add(factorial);
        }
        // 取 S 末尾 9 位数字
        String strS = factorialsSum.toString();
        System.out.println(strS);
        String lastNineDigits = strS.substring(strS.length() - 9);
        long end = System.currentTimeMillis();
        System.out.println(lastNineDigits); // 输出结果
        System.out.println("共花费："+(end-start)/1000+"秒");
    }
}
