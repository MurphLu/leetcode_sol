package org.ml.others.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 房子排列为环，也就是 第一个和最后一个不能同时被打劫
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class RobII {
    public static void main(String[] args) {
        int[] n = new int[]{2,3,2};
        System.out.println(new RobII().rob2(n));
    }

    public static int rob(int[] nums) {
        return process(nums, 0, -1, 0, 0);
    }

    public static int process(int[] nums, int cur, int lastSelect, int robbed, int total) {
        if (cur >= nums.length) {
            return total;
        }
        if (cur - lastSelect > 2) {
            return 0;
        }
        return Math.max(process(nums, cur + 1, lastSelect, robbed, total),
        process(nums, cur + 2, cur + 2,robbed + 1, total + nums[cur]));
    }


    public int rob2(int[] nums) {
        if(nums.length == 1){ return nums[0];}
        return Math.max(process(Arrays.copyOfRange(nums, 0, nums.length - 1), 0, new ArrayList<>()),
                process(Arrays.copyOfRange(nums, 1, nums.length), 0, new ArrayList<>()));
    }

    public int process(int[] nums, int cur, List<Integer> result) {
        if (cur == nums.length) {
            return result.size() > 1 ? Math.max(result.get(cur - 1), result.get(cur - 2)) : result.get(0);
        }
        if (cur == 0 || cur == 1) {
            result.add(nums[cur]);
        } else {
            int curMaxVal = Math.max(nums[cur] + result.get(cur - 2), cur - 3 >= 0 ? nums[cur] + result.get(cur - 3) : 0);
            result.add(curMaxVal);
        }
        return process(nums, cur + 1, result);
    }
}
