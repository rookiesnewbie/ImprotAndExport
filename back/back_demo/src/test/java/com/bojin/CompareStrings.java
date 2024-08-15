package com.bojin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompareStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入字符串长度和字符串
        System.out.print("输入字符串s1的长度: ");
        int len1 = scanner.nextInt();
        System.out.print("输入字符串s1: ");
        String s1 = scanner.next();

        System.out.print("输入字符串s2的长度: ");
        int len2 = scanner.nextInt();
        System.out.print("输入字符串s2: ");
        String s2 = scanner.next();

        // 确保输入的字符串长度与提供的信息相符
        if (s1.length() != len1 || s2.length() != len2) {
            System.out.println("输入的字符串长度与提供的信息不符！");
            return;
        }

        // 计算不同字符的个数
        int diffCount = compareStrings(s1, s2, len1, len2);

        // 输出结果
        System.out.println("不同字符的个数为: " + diffCount);

        int i = find();
        System.out.println("共"+i);
    }

    public static int compareStrings(String s1, String s2, int len1, int len2) {
        int minLength = Math.min(len1, len2);
        int diffCount = 0;

        // 从后向前遍历比较
        for (int i = 0; i < minLength; i++) {
            char c1 = s1.charAt(len1 - 1 - i);
            char c2 = s2.charAt(len2 - 1 - i);
            if (c1 != c2) {
                diffCount++;
            }
        }

        // 如果s1更长，则继续遍历s1剩余部分
        if (len1 > len2) {
            for (int i = minLength; i < len1; i++) {
                if (s1.charAt(len1 - 1 - i) != s2.charAt(len2 - 1)) {
                    diffCount++; // 假设与s2的最后一个字符不同，或s2为空
                }
            }
        }

        // 如果s2更长，则继续遍历s2剩余部分
        if (len2 > len1) {
            for (int i = minLength; i < len2; i++) {
                if (s2.charAt(len2 - 1 - i) != s1.charAt(len1 - 1)) {
                    diffCount++; // 假设与s1的最后一个字符不同，或s1为空
                }
            }
        }

        return diffCount;
    }


    public static int  find(){
        List<Integer> list = new ArrayList<>();
        for (int i = 100; i < 1000; i++) {
            int a = i % 10; //个位
            int b = i / 10 % 10; //十位
            int c = i / 100;  //百位

            if (a + c == b){
                list.add(i);
            }
        }

        for (Integer number : list) {
            System.out.println(number);
        }

        return list.size();
    }
}