package com.bojin;


public class test4 {
    public static void main(String[] args) {

        String s1 = "1,3,5,0";
        String s2 = "2,4,1,7,5";
        int len1 = 4;
        int len2 = 5;

        //System.out.println(getDiffNum(len1, s1, len2, s2));
        //System.out.println(getDiffNum1(len1, s1, len2, s2));

        //System.out.println(sum(15));
        //System.out.println(sum2(10020030006015L));
        System.out.println(solve("1","99"));
    }

    public static int getDiffNum(int len1, String s1, int len2, String s2) {
        int count = 0;
        int len = 0;
        String[] arr1 = s1.split(",");
        String[] arr2 = s2.split(",");
        if (len1 > len2) {
            len = len2;
        } else {
            len = len1;
        }
        for (int i = 0; i < len; i++) {
            if (!arr1[len1 - i - 1].equals(arr2[len2 - i - 1])) {
                count++;
            }
        }
        return count;
    }



    public static int getDiffNum1(int len1, String s1, int len2, String s2) {
       int count = 0;

       if (len1 < len2){
           for (int i = 1; i <= s1.length(); i++) {
               if (s1.charAt(s1.length() - i) != s2.charAt(s2.length() - i)){
                   count++;
               }
           }
           return count;
       }
       return 0;
    }


    public static int sum(Integer number){
        int count = 0;
        String s = number + "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0'){
                count++;
            }
        }

        return count;
    }

    public static int sum2(Long number){
        int count = 0;
        String s = String.valueOf(number);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0'){
                count++;
            }
        }

        return count;
    }

    public static String solve (String s, String t) {
        Integer a = null;
        Integer b = null;
        if (s != "" && s != null){
             a =  Integer.valueOf(s);
        }else {
            a = 0;
        }

        if (t != "" && t != null){
             b =  Integer.valueOf(t);
        }else {
            b = 0;        }



        Integer c =  a + b;

        return String.valueOf(c);
    }
}
