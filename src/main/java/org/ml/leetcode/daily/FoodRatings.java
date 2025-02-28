package org.ml.leetcode.daily;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 设计一个支持下述操作的食物评分系统：
 *
 * 修改 系统中列出的某种食物的评分。
 * 返回系统中某一类烹饪方式下评分最高的食物。
 * 实现 FoodRatings 类：
 *
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) 初始化系统。食物由 foods、cuisines 和 ratings 描述，长度均为 n 。
 * 
 * 	foods[i] 是第 i 种食物的名字。
 * 	cuisines[i] 是第 i 种食物的烹饪方式。
 * 	ratings[i] 是第 i 种食物的最初评分。
 * 
 * 
 * void changeRating(String food, int newRating) 修改名字为 food 的食物的评分。
 * String highestRated(String cuisine) 返回指定烹饪方式 cuisine 下评分最高的食物的名字。如果存在并列，返回 字典序较小 的名字。
 * 注意，字符串 x 的字典序比字符串 y 更小的前提是：x 在字典中出现的位置在 y 之前，也就是说，要么 x 是 y 的前缀，
 * 或者在满足 x[i] != y[i] 的第一个位置 i 处，
 * x[i] 在字母表中出现的位置在 y[i] 之前。
 */
public class FoodRatings {
   
    static class FoodRate implements Comparable<FoodRate>{
        private String foodName;
        private int rating;

        public FoodRate(String foodName, int rating) {
            this.foodName = foodName;
            this.rating = rating;
        }

        @Override
        public int compareTo(FoodRate o) {
            if (this.rating == o.rating) {
                return o.foodName.compareTo(this.foodName);
            }
            return Integer.compare(this.rating, o.rating);
        }
    }

    Map<String, TreeSet<FoodRate>> cuisineMap;
    Map<String, FoodRate> foodRateMap;
    Map<String, TreeSet<FoodRate>> foodSetMap;


    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        cuisineMap = new HashMap<>();
        foodRateMap = new HashMap<>();
        foodSetMap = new HashMap<>();
        int n = foods.length;
        for (int i = 0; i < n; i++) {
            String cuisine = cuisines[i];
            String food = foods[i];
            int rate = ratings[i];

            TreeSet<FoodRate> treeSet = cuisineMap.getOrDefault(cuisine, new TreeSet<>());
            FoodRate foodRate = new FoodRate(food, rate);
            treeSet.add(foodRate);
            foodRateMap.put(food, foodRate);
            foodSetMap.put(food, treeSet);
            cuisineMap.put(cuisine, treeSet);
        }
    }

    public void changeRating(String food, int newRating) {
        TreeSet<FoodRate> foodRates = foodSetMap.get(food);
        FoodRate foodRate = foodRateMap.get(food);
        foodRates.remove(foodRate);
        foodRate.rating = newRating;
        foodRates.add(foodRate);
    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine).last().foodName;
    }
}
