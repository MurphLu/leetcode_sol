package org.ml.others;

public class PMT {
    public static void main(String[] args) {
        pmt(1000, 12, 0.03);
    }

    private static void pmt(int total, int trx, double interestRate) {
        int m = 1;
        System.out.println(total * (interestRate * Math.pow((1+interestRate), trx))/ (Math.pow(1+interestRate, trx) -1));
//        while (m <= trx) {
//
//            m++;
//        }
    }
}

