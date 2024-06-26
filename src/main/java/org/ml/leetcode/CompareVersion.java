package org.ml.leetcode;

/**
 * 给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
 *
 * 比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
 *
 * 返回规则如下：
 *
 * 如果 version1 < version2 返回 -1，
 * 如果 version1 > version2 返回 1，
 * 除此之外返回 0。
 */
public class CompareVersion {
    public static void main(String[] args) {
        new CompareVersion().compareVersion("10.10.00011.111", "110.10.000100");
    }
    public int compareVersion(String version1, String version2) {
        if (version1.isEmpty() && version2.isEmpty()) {
            return 0;
        }
        String v1 = getNextPoint(version1);
        String v2 = getNextPoint(version2);
        int cp;
        if ((cp = compare(v1, v2)) != 0) {
            return cp;
        }
        version1 = v1.length() >= version1.length() ? "" : version1.substring(v1.length() + 1);
        version2 = v2.length() >= version2.length() ? "" : version2.substring(v2.length() + 1);
        System.out.println(v1);
        System.out.println(v2);
        return compareVersion(version1, version2);
    }

    private String getNextPoint(String version) {
        if (version.isEmpty()) {
            return "0";
        }
        int idx = 0;
        while (idx < version.length() && version.charAt(idx) != '.'){
            idx++;
        }
        return idx == version.length() ? version : version.substring(0, idx);
    }

    private int compare(String v1, String v2) {
        return Integer.compare(Integer.parseInt(v1), Integer.parseInt(v2));
    }

}
