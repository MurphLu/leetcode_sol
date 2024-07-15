package org.ml.leetcode.daily;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 *
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
 * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 *
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 的邮箱地址。账户本身可以以 任意顺序 返回。
 */

public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> a = Arrays.asList(
                Arrays.asList("Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"),
                Arrays.asList("Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"),
                Arrays.asList("Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"),
                Arrays.asList("Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"),
                Arrays.asList("Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"));
//        list.add("Gabe0@m.co");
//        list.add("Gabe3@m.co");
//        list.add("Gabe1@m.co");
        List<List<String>> lists = new AccountsMerge().accountsMerge(a);
//        list.sort(String::compareTo);
//        System.out.println(list);
        System.out.println(lists);

    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Integer>> ep = new HashMap<>();
        int idx = 0;
        int size = accounts.size();
        while (idx < size) {
            List<String> account = accounts.get(idx);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                List<Integer> idxList = ep.getOrDefault(email, new ArrayList<>());
                idxList.add(idx);
                ep.put(email, idxList);
            }
            idx++;
        }
        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<String, List<Integer>> entry: ep.entrySet()){
            if (entry.getValue().size() > 1) {
                String name="";
                if (accounts.get(entry.getValue().get(0)) != null) {
                    name = accounts.get(entry.getValue().get(0)).get(0);
                }
                List<String> m = mergeAccount(ep, entry.getKey(), accounts, entry.getValue());
                if (!m.isEmpty()) {
                    m.sort(String::compareTo);
                    m.add(0, name);
                    res.add(m);
                }
            }
        }
        for(List<String> account: accounts) {
            if (account != null) {
                String name = account.remove(0);
                account.sort(String::compareTo);
                account.add(0, name);
                res.add(account);
            }
        }
        return res;
    }

    private List<String> mergeAccount(Map<String, List<Integer>> ep, String email, List<List<String>> accounts, List<Integer> idxes) {
        Set<String> res = new HashSet<>();
        res.add(email);
        boolean hasDig = false;
        for(int i: idxes) {
            List<String> account = accounts.get(i);
            List<String> dig = new ArrayList<>();
            if (account!=null) {
                hasDig = true;
                for (int j = 1; j < account.size(); j++) {
                    String tmpEmail = account.get(j);
                    if (tmpEmail.equals(email)) {
                        continue;
                    }
                    if (ep.containsKey(tmpEmail) && ep.get(tmpEmail).size() > 1) {
                        dig.add(tmpEmail);
                    }
                    res.add(tmpEmail);

                }
            }
            accounts.set(i, null);
            for(String digEmail: dig) {
                res.addAll(mergeAccount(ep, digEmail, accounts, ep.get(digEmail)));
            }
        }
        List<String> strings = new ArrayList<>();
        if (hasDig) {
            strings.addAll(res);

        }
        return strings;

    }

}
