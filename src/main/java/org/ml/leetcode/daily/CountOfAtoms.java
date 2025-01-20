package org.ml.leetcode.daily;

import java.util.Map;
import java.util.TreeMap;

public class CountOfAtoms {
    public String countOfAtoms(String formula) {
        TreeMap<String, Integer> map = new TreeMap<>();
        return "";
    }

    static class Info{
        private String atom;
        private int cnt;

        public Info(String atom, int cnt) {
            this.atom = atom;
            this.cnt = cnt;
        }
    }

    private Info getFirstAtomAndCount(String formula) {
        StringBuilder sb = new StringBuilder();
        if (formula.charAt(0) >= 'A' && formula.charAt(0) <= 'Z') {
            sb.append(formula.charAt(0));
            formula = formula.substring(1);
        }
        while (formula.charAt(0) >= 'a' && formula.charAt(0) <= 'z') {
            sb.append(formula.charAt(0));
            formula = formula.substring(1);
        }
        int atomCount = getAtomCount(formula);
        return new Info(sb.toString(), atomCount);
    }
    private int getAtomCount(String formula) {
        StringBuilder sb = new StringBuilder();
        char c = formula.charAt(0);
        while (c>='0' && c<='9'){
            sb.append(c);
            formula = formula.substring(1);
        }
        if (sb.length() > 0) {
            return Integer.parseInt(sb.toString());
        }
        return 1;
    }
}
