package com.bojin;

import java.util.ArrayList;
import java.util.List;


public class Test3 {
    public static void main(String[] args) {
        List<Integer> specialNumbers = findSpecialNumbers();
        for (int number : specialNumbers) {
            System.out.println(number);
        }
        System.out.println(specialNumbers.size());
    }

    public static List<Integer> findSpecialNumbers() {
        List<Integer> specialNumbers = new ArrayList<>();



        for (int i = 100; i <= 999; i++) {
            int hundred = i / 100;          // 百位数
            int ten = (i / 10) % 10;        // 十位数
            int unit = i % 10;              // 个位数

            if (hundred + unit == ten) {
                specialNumbers.add(i);
            }
        }

        return specialNumbers;
    }
}
