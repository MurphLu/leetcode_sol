package org.ml.interviewParp;

import java.util.Arrays;
import java.util.Comparator;
/**aaa**/
public class Solve {
    public static void main(String[] args) {
        System.out.println(isPalindrome("-=**aAa_+B;'cBaa;A="));
        System.out.println(findOnce(new int[]{1,1,2,3,3,4,4,5,5,6,6}));
        int[] arr = new int[]{-9,1,-2,3,-4,5,-6,7,-8};
        orderByAbs(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Math.random());
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        System.out.println(sb);
        Integer num1 = 200;
        Integer num2 = 200;
        System.out.println(num1 == num2);
        System.out.println(num1.equals(num2));
    }
    /**
     * 给定一个数组，按值的绝对值升序排列
     */
    public void orderByAbs(Integer[] arr) {
        Arrays.sort(arr, Comparator.comparingInt(Math::abs));
    }

    public static void orderByAbs(int[] arr) {
        merge(arr, 0, arr.length-1);
    }

    private static void merge(int[] arr, int left, int right) {
        if (left>=right) {
            return;
        }
        int mid = (right-left)/2+left;
        merge(arr, left, mid);
        merge(arr, mid+1, right);
        int[] temp = new int[right-left+1];
        int idx = 0;
        int ll = left;
        int lr = mid;
        int rl = mid+1;
        int rr = right;

        while (ll<=lr && rl<=rr){
            if (Math.abs(arr[ll]) <= Math.abs(arr[rl])) {
                temp[idx++] = arr[ll++];
            } else {
                temp[idx++] = arr[rl++];
            }
        }
        while (ll<=lr) {
            temp[idx++] = arr[ll++];
        }
        while (rl<=rr){
            temp[idx++] = arr[rl++];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    /**
     * 给一个有序数组，只有一个字段只出现一次，其他字段都会重复出现，找出这个只出现一次的字段
     */
    public int getOnlyOnce(int[] arr) {
        int left = 0;
        int right = 1;
        while (right< arr.length) {
            if (arr[left] == arr[right]) {
                right++;
            } else {
                if (right-left == 1) {
                    return arr[left];
                }
                left = right;
            }
        }
        return arr[left];
    }

    /**
     * 找出一个整数数组中乘积最大的三个数
     */
    public int maxThreeMut(int[] nums) {
        if(nums.length == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);
        if(nums[nums.length-1] <= 0 || nums[1] >= 0) {
            return nums[nums.length-1] * nums[nums.length-2] * nums[nums.length-3];
        }
        return Math.max(
                nums[nums.length-1] * nums[nums.length-2] * nums[nums.length-3],
                nums[nums.length-1] * nums[0] * nums[1]
        );
    }


    /**
     * 输入一个数值，判断这个数值是否是2的幂数
     */
    public static boolean isPowOf2(int val) {
        while ((val & 1) == 0) {
            val>>=1;
        }
        return val == 1;
    }

    /**
     * 数字每一位加和
     */
    public static int sum(int n) {
        if (n < 10) {
            return n;
        }
        int res;
        for (;;) {
            res = 0;
            while (n > 0) {
                res += n % 10;
                n /= 10;
            }
            if (res < 10) {
                break;
            }
            n = res;
        }
        return res;
    }

    /**
     * 判断是否回文，忽略大小写，忽略除字母外的其他字符
     * @param s 原石字符串
     * @return 结果
     */
    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length-1;
        while (left < right){

            while (!isLetter(chars[left])) {
                left++;
            }
            while (!isLetter(chars[right])){
                right--;
            }
            if (left < right) {
                if(toLowerCase(chars[left]) != toLowerCase(chars[right])) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
    private static boolean isLetter(char c) {
        return c>='A' && c<='Z' || (c>='a' && c<='z');
    }
    private static char toLowerCase(char c){
        if (c>='A' && c<='Z') return (char)(c+32);
        return c;
    }

    /**
     * 一个数组中只有一个数字出现一次，其他都出现两次，找出这个数
     */
    public static int findOnce(int[] arr) {
        int res = 0;
        for(int n: arr){
            res^=n;
        }
        return res;
    }

    /**
     * 最大子数组乘积
     */
    public static int maxProduct(int[] nums) {
        int[] preRes = new int[nums.length];
        preRes[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i] == 0 || preRes[i-1] == 0) {
                preRes[i] = nums[i];
            } else {
                preRes[i] = nums[i]*preRes[i-1];
            }
        }
        int max = Integer.MIN_VALUE;
        int leftBound = 0;
        int left = 0;
        int right = 0;
        boolean hasZero = false;
        while (right<preRes.length) {
            while (left<preRes.length && preRes[right] == 0) {
                hasZero = true;
                left = right;
                leftBound = right;
                left++;
                leftBound++;
                right++;
            }
            if(right==preRes.length) {
                break;
            }
            max = Math.max(preRes[right], max);
            while (left<right) {
                max = Math.max(preRes[right]/preRes[left++], max);
            }
            left = leftBound;
            right++;
        }
        return (hasZero && max < 0) ? 0 : max;
    }


    /**
     * 预测输赢
     *
     * 给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
     *
     * 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
     *
     * 如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。你可以假设每个玩家的玩法都会使他的分数最大化。
     */
    public boolean predictTheWinner(int[] nums) {
        return playerOne(nums, 0, nums.length-1, 0) >= 0;
    }

    private int playerOne(int[] nums, int left, int right, int score) {
        if(left == right) {
            return score + nums[left];
        }
        return Math.max(
                playerTwo(nums, left+1, right, score+nums[left]),
                playerTwo(nums, left, right-1, score+nums[right])
        );
    }

    private int playerTwo(int[] nums, int left, int right, int score) {
        if(left == right) {
            return score + nums[left];
        }
        return Math.min(
                playerOne(nums, left+1, right, score - nums[left]),
                playerOne(nums, left, right-1, score - nums[right])
        );
    }
}
