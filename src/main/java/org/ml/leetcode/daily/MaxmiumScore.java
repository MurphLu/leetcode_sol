package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxmiumScore {
    public static void main(String[] args) {
        new MaxmiumScore().maxmiumScore(new int[]{2,2,2,2}, 3);
    }
    public int maxmiumScore(int[] cards, int cnt) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        Arrays.sort(cards);
        for(int card: cards) {
            if (card % 2 == 0) {
                even.add(card);
            } else {
                odd.add(card);
            }
        }
        int[] oddSuffix = new int[odd.size()+1];
        int[] evenSuffix = new int[even.size()+1];
        oddSuffix[0] = 0;
        for (int i = 0; i < odd.size(); i++) {
            oddSuffix[i+1] = oddSuffix[i] + odd.get(odd.size()-i-1);
        }
        evenSuffix[0] = 0;
        for (int i = 0; i < even.size(); i++) {
            evenSuffix[i+1] = evenSuffix[i] + even.get(even.size()-1-i);
        }
        int oddCnt = 0;
        int max = 0;
        while (oddCnt<=cnt && oddCnt <= odd.size()) {
            int evenCnt = cnt-oddCnt;
            if (evenCnt<=even.size() && evenCnt>=0){
                int res = evenSuffix[evenCnt] + oddSuffix[oddCnt];
                max = Math.max(res, max);
            }
            oddCnt+=2;
        }
        return max;
    }
}
