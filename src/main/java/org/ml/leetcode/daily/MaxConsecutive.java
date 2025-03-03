package org.ml.leetcode.daily;

import java.util.Arrays;

/**
 * Alice 管理着一家公司，并租用大楼的部分楼层作为办公空间。Alice 决定将一些楼层作为 特殊楼层 ，仅用于放松。
 *
 * 给你两个整数 bottom 和 top ，表示 Alice 租用了从 bottom 到 top（含 bottom 和 top 在内）的所有楼层。另给你一个整数数组 special ，其中 special[i] 表示 Alice 指定用于放松的特殊楼层。
 *
 * 返回不含特殊楼层的 最大 连续楼层数。
 */
public class MaxConsecutive {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int cnt = 0;
        int idx = 0;
        while (bottom <= top && idx < special.length) {
            if (bottom < special[idx]) {
                cnt = Math.max(cnt, special[idx]-bottom);
                bottom = special[idx]+1;
                idx++;
            } else if (bottom == special[idx]) {
                bottom++;
                idx++;
            }else {
                idx++;
            }
        }
        if (bottom < top){
            cnt = Math.max(cnt, top-bottom+1);
        }
        return cnt;
    }
}
