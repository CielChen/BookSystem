package com.ciel.appoint.test;

import java.util.Scanner;

/**
 * Author: CIEL
 * Date: 2018/06
 */
public class number1 {
    public static int lastNumber(String string){
        String aa[] = string.split(" ");
        int len = aa.length;
        System.out.println(aa[len-1]);
        String b = aa[len-1];
        return b.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String str = scanner.nextLine();
            int len = lastNumber(str);
            System.out.println(len);
        }
    }
}
