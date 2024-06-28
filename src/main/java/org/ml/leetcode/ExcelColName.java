package org.ml.leetcode;

/**
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * 这是一道从 1 开始的的 26 进制转换题。
 */
public class ExcelColName {
    public static void main(String[] args) {
        System.out.println(new ExcelColName().convertToTitle(27));
    }
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        sb.reverse();
        return sb.toString();
    }
}
