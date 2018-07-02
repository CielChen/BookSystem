package com.ciel.appoint.test;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Author: CIEL
 * Date: 2018/06
 */
public class Case3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            TreeSet<Integer> treeSet = new TreeSet<Integer>();
            if (n > 0){
                for (int i=0; i<n; i++){
                    treeSet.add(scanner.nextInt());
                }
            }
            for (Integer i:treeSet){
                System.out.println(i);
            }
        }
    }
}
