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
        int factor = 1000; //  1000次方
        int bits = 16; //  取值段的位数
        int shift = 1 << bits; //  取值段的值，移位计算
        int remain = factor % bits; // 剩余的位数
        int quant = (factor - remain) / bits; // 取值段的个数
        double result = 1.0; // 采用浮点数保存结果，并初始化为1
        for (int i = 0; i < quant; i++)
            result *= shift; // 叠乘每个取值段
        result *= 1 << remain; // 叠乘剩余的位数
        System.out.println("double result=" + result); // 打印结果
    }

    /**
     * 使用长整数数组进行计算
     */
    static void longBit() {
        int factor = 1000; // 1000次方
        int bits = 16;   // 取值段的位数
        int limit = 100000; // 每个整数结果的范围
        int limitLen = 5;  // 每个整数结果范围的位数
        int remain = factor % bits;  // 剩余的位数
        int quant = (factor - remain) / bits; // 取值段的个数
        long[] result = new long[factor >> 1]; // 创建结果整数
        result[0] = 1;  // 初始化结果整数为1
        for (int i = 0; i <= quant; i++) { // 叠乘每个取值段
            long carry = 0; // 每个结果整数的进位
            int shift = i == quant ? remain : bits; // 移位
            for (int j = 0; shift > 0 && j < result.length; j++) {
                long temp = (result[j] << shift) + carry; // 计算每个结果整数
                result[j] = temp % limit; // 取余数
                carry = (temp - result[j]) / limit; // 取进位
            }
        }
        System.out.print("long result="); // 打印结果
        boolean flag = false;
        for (int j = result.length - 1; j >= 0; j--) { // 打印每个结果整数
            if (flag || result[j] > 0) {
                String text = result[j] + "";
                for (int i = text.length(); flag && i < limitLen; i++)
                    System.out.print("0"); // 每个结果整数不足部分补零
                System.out.print(text); // 打印每个结果整数
                System.out.print(" "); // 打印分割符
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
