package com.bojin;

import java.util.Scanner;

public class Test2 {
    public static int countDifferentChars(String s1, String s2) {
        // 获取两个字符串的长度
        int len1 = s1.length();
        int len2 = s2.length();

        // 以最短字符串为标准
        int minLength = Math.min(len1, len2);
        int count = 0;

        // 从后向前逐个字符比较
        for (int i = 1; i <= minLength; i++) {
            if (s1.charAt(len1 - i) != s2.charAt(len2 - i)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入第一个字符串
        System.out.println("请输入第一个字符串:");
        String s1 = scanner.nextLine();

        // 输入第二个字符串
        System.out.println("请输入第二个字符串:");
        String s2 = scanner.nextLine();

        // 计算不同字符的个数
        int result = countDifferentChars(s1, s2);

        // 输出结果
        System.out.println("不同字符的个数为: " + result);

        scanner.close();
    }
}
