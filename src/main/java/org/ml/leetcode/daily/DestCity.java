package org.ml.leetcode.daily;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi]
 * 表示该线路将会从 cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
 *
 * 题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。
 */
public class DestCity {
    public String destCity(List<List<String>> paths) {
        Map<String, String> dest = new HashMap<>();
        for(List<String> path: paths) {
            String from = path.get(0);
            String to = path.get(1);
            dest.put(from, to);
            dest.put(to, dest.get(to));
            String mid = to;
            mid = dest.get(mid);
            while (mid != null) {
                dest.put(from, mid);
                dest.put(to, mid);
                mid = dest.get(mid);
            }
        }
        for (Map.Entry<String, String> entry: dest.entrySet()) {
            if (entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return "";
    }
}
