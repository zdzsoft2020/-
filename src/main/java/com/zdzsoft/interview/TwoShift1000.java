package com.zdzsoft.interview;

/**
 * 计算2的1000次方
 *
 * @author zdzsoft 北京掌舵者科技有限公司
 * @link www.zdzsoft.com
 * @Copyright BeiJing ZDZ Tech Co.LTD
 */
public class TwoShift1000 {

    /**
     * 使用double浮点数进行计算
     */
    static void doubleBit() {
        int bits = 16;
        int factor = 1000;
        int shift = 1 << bits;
        int remain = factor % bits;
        int quant = (factor - remain) / bits;
        double result = 1.0;
        for (int i = 0; i < quant; i++)
            result *= shift;
        result *= 1 << remain;
        System.out.println("double result=" + result);
    }

    /**
     * 使用长整数数组进行计算
     */
    static void longBit() {
        int bits = 16;
        int factor = 1000;
        int limit = 100000;
        int limitLen = 5;
        int remain = factor % bits;
        int quant = (factor - remain) / bits;
        long[] result = new long[factor >> 1];
        result[0] = 1;
        for (int i = 0; i < quant; i++) {
            long carry = 0;
            for (int j = 0; j < result.length; j++) {
                long temp = (result[j] << bits) + carry;
                result[j] = temp % limit;
                carry = (temp - result[j]) / limit;
            }
        }
        long carry = 0;
        for (int j = 0; j < result.length; j++) {
            long temp = (result[j] << remain) + carry;
            result[j] = temp % limit;
            carry = (temp - result[j]) / limit;
        }
        System.out.print("long result=");
        boolean flag = false;
        for (int j = result.length - 1; j >= 0; j--) {
            if (flag || result[j] > 0) {
                String text = result[j] + "";
                for (int i = text.length(); flag && i < limitLen; i++)
                    System.out.print("0");
                System.out.print(text);
                System.out.print(" ");
                flag = true;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        doubleBit();
        longBit();
    }
}
