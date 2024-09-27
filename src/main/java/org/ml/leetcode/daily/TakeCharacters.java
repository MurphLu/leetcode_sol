package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。
 *
 * 你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。
 *
 * 1 <= s.length <= 10^5
 * s 仅由字母 'a'、'b'、'c' 组成
 * 0 <= k <= s.length
 *
 */
public class TakeCharacters {
    public int takeCharacters(String s, int k) {
        if(k == 0) {
            return 0;
        }
        if (s.length()/3 < k) {
            return -1;
        }

        List<int[]> cnt1 = new ArrayList<>();
        List<int[]> cnt2 = new ArrayList<>();
        int cntA=0, cntB=0, cntC = 0;
        int cntAR=0, cntBR=0, cntCR=0;
        char[] cs = s.toCharArray();
        for (int i = 0; i< cs.length; i++) {
            switch (cs[i]) {
                case 'a':
                    cntA++;
                    break;
                case 'b':
                    cntB++;
                    break;
                case 'c':
                    cntC++;
                    break;
            }
            switch (cs[cs.length - 1 - i]) {
                case 'a':
                    cntAR++;
                    break;
                case 'b':
                    cntBR++;
                    break;
                case 'c':
                    cntCR++;
                    break;
            }
            cnt1.add(new int[]{cntA, cntB, cntC});
            cnt2.add(new int[]{cntAR, cntBR, cntCR});
        }
        if (cntA< k || cntB < k || cntC < k) {
            return -1;
        }

        int res = getIdx(0, cnt1.size() - 1, new int[]{0,0,0}, cnt2, k)+1;
        for (int i = 0; i < cnt1.size(); i++) {
            int[] cnt = cnt1.get(i);
            if (cnt[0] >= k && cnt[1] >= k && cnt[2] >= k) {
                res = Math.min(i+1, res);
                break;
            } else {
                int idx = getIdx(0, cnt1.size() - i - 2, cnt, cnt2, k);
                res = Math.min(idx+i+2, res);
            }

        }
        return res;
    }

    private int getIdx(int left, int right, int[] cnt, List<int[]> src, int k) {
        if (left == right) {
            return left;
        }
        int mid = (left + right) / 2;
        int[] cntR = src.get(mid);
        if (cnt[0] + cntR[0] >= k && cnt[1] + cntR[1] >= k && cnt[2] + cntR[2] >= k) {
            return getIdx(left, mid, cnt, src, k);
        } else{
            return getIdx(mid+1, right, cnt, src, k);
        }
    }

}
