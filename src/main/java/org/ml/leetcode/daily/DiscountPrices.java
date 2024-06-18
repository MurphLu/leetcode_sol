package org.ml.leetcode.daily;

public class DiscountPrices {
    public static void main(String[] args) {
        System.out.println(new DiscountPrices().discountPrices("1 2 $3 4 $5 $6 7 8$ $9 $10$", 10));
    }
    public String discountPrices(String sentence, int discount) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;

        while (idx < sentence.length()) {
            char c;
            while (idx < sentence.length() && (c=sentence.charAt(idx)) == ' ') {
                sb.append(' ');
                idx++;
            }
            int end = idx;
            boolean isPrice = true;
            c = sentence.charAt(idx);
            if (c != '$') {
                isPrice = false;
            }
            end++;
            while (end < sentence.length() && (c=sentence.charAt(end)) != ' ') {
                if (c < '0' || c > '9') {
                    isPrice = false;
                }
                end++;
            }

            String s = sentence.substring(idx, end);
            if (s.equals("$")) {
                isPrice = false;
            }
            if(isPrice) {
                double price = Long.parseLong(s.substring(1)) * (1-discount / 100.0);
                s = String.format("$%.2f", price);
            }
            sb.append(s);
            idx = end;
        }
        return sb.toString();
    }
}
