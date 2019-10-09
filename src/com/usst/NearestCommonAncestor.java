package com.usst;

import java.util.HashMap;
import java.util.HashSet;

public class NearestCommonAncestor
{
    private HashMap<TreeNode, TreeNode> map;

    public NearestCommonAncestor(TreeNode root)
    {
        map = new HashMap<>();
        if (root != null)
            map.put(root, null);
        setMap(root);
    }

    public void setMap(TreeNode head)
    {
        if (head == null)
            return;
        if (head.left != null)
            map.put(head.left, head);
        if (head.right != null)
            map.put(head.right, head);
        setMap(head.left);
        setMap(head.right);
    }

    public TreeNode query(TreeNode node1, TreeNode node2)
    {
        HashSet<TreeNode> path = new HashSet<>();
        while (map.containsKey(node1))
        {
            path.add(node1);
            node1 = map.get(node1);
        }
        while (!path.contains(node2))
            node2 = map.get(node2);
        return node2;
    }
}
