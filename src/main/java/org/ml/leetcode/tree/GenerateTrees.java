package org.ml.leetcode.tree;

import org.ml.dataStructer.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 */
public class GenerateTrees {
    public List<TreeNode> generateTrees(int n) {
        return buildTree(1, n);
    }

    private List<TreeNode> buildTree(int from, int to) {
        if (from == to) {
            return Arrays.asList(new TreeNode(from));
        }
        List<TreeNode> res = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            List<TreeNode> lefts = null;
            List<TreeNode> rights = null;
            if (i != from)
                lefts = buildTree(from, i - 1);
            if (i != to)
               rights = buildTree(i+1, to);

            if (lefts != null && rights != null) {
                for (TreeNode left: lefts) {
                    for (TreeNode right: rights) {
                        res.add(new TreeNode(i, left, right));
                    }
                }
                continue;
            }
            if (lefts != null) {
                for (TreeNode left: lefts) {
                    res.add(new TreeNode(i, left, null));
                }
            }
            if (rights != null) {
                for (TreeNode right: rights) {
                    res.add(new TreeNode(i, null, right));
                }
            }
        }
        return res;
    }

}
