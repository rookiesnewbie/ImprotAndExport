package com.bojin;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner scannerStr1 = new Scanner(System.in);
        System.out.println("请输入第一个字符串");
        scannerStr1.hasNext();

        Scanner scannerStrLen1 = new Scanner(System.in);
        System.out.println("请输入第一个字符串的长度");
        scannerStrLen1.hasNext();

        Scanner scannerStr2 = new Scanner(System.in);
        System.out.println("请输入第而个字符串");
        scannerStr2.hasNext();

        Scanner scannerStrLen2 = new Scanner(System.in);
        System.out.println("请输入第一个字符串");
        scannerStrLen2.hasNext();



        //compareString(scannerStr1.next(),scannerStrLen1.nextInt(),scannerStr2.next(),scannerStrLen2.nextInt());

        compareString(scannerStr1.next(),scannerStr2.next());
    }

    public static void compareString(String Str1, int len1, String Str2, int len2){
        int count = 0;
        int minLen = Math.min(len1, len2);
        for (int i = 1; i <= minLen; i++) {
            if (Str1.charAt(len1 - i) != Str2.charAt(len2 - i)){
                count++;
            }
        }

        System.out.println(count);
    }

    public static void compareString(String s1,String s2){
        int count = 0;
        int minLen = Math.min(s1.length(), s2.length());
        for (int i = 1; i <= minLen; i++) {
            if (s1.charAt(s1.length() - i) != s2.charAt(s2.length() - i)){
                count++;
            }
        }
        System.out.println(count);
    }
}
