package com.usst;

import java.util.Arrays;
import java.util.HashMap;

//根据二叉树的先中后序遍历数组重建二叉树
public class PreInPostRebuildBinaryTree
{
    //先序和中序重建
    public static TreeNode preAndIn(int[] pre, int[] in)
    {
        if (pre.length == 0)
            return null;
        if (pre.length == 1)
            return new TreeNode(pre[0]);

        int index = 0;
        for (int i = 0; i < in.length; i++)
        {
            if (pre[0] == in[i])
            {
                index = i;
                break;
            }
        }

        TreeNode root = new TreeNode(pre[0]);
        root.left = preAndIn(Arrays.copyOfRange(pre, 1, index + 1), Arrays.copyOfRange(in, 0, index));
        root.right = preAndIn(Arrays.copyOfRange(pre, index + 1, pre.length), Arrays.copyOfRange(in, index + 1, in.length));

        return root;
    }

    //后序和中序重建
    public static TreeNode postAndIn(int[] post, int[] in)
    {
        if (post.length == 0)
            return null;
        if (post.length == 1)
            return new TreeNode(post[0]);

        int index = 0;
        for (int i = 0; i < in.length; i++)
        {
            if (post[post.length - 1] == in[i])
            {
                index = i;
                break;
            }
        }

        TreeNode root = new TreeNode(post[post.length - 1]);
        root.left = postAndIn(Arrays.copyOfRange(post, 0, index), Arrays.copyOfRange(in, 0, index));
        root.right = postAndIn(Arrays.copyOfRange(post, index, post.length - 1), Arrays.copyOfRange(in, index + 1, in.length));

        return root;
    }

    //通过先序和中序数组生成后序数组(直接生成,不重建树),未理解
    public static int[] getPosArray(int[] pre, int[] in)
    {
        if (pre == null || in == null)
            return null;
        int len = pre.length;
        int[] pos = new int[len];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++)
        {
            map.put(in[i], i);
        }
        setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
    }

    public static int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s,
                             int si, HashMap<Integer, Integer> map)
    {
        if (pi > pj)
            return si;
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
        return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
    }

    public static void main(String[] args)
    {
        int[] pre = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] in = new int[]{3, 2, 4, 1, 6, 5, 7};
        int[] post = new int[]{3, 4, 2, 6, 7, 5, 1};
        TreeNode rootByPreAndIn = preAndIn(pre, in);
        BinaryTree.traverse(rootByPreAndIn);
        TreeNode rootByPostAndIn = postAndIn(post, in);
        BinaryTree.traverse(rootByPostAndIn);

        System.out.println();
        System.out.println(Arrays.equals(post, getPosArray(pre, in)));//true
        /*
            根据先序和后序是无法确定二叉树的,因为前序和后序在本质上都是将父节点与子结点进行分离,并没有指明左子树和右子树的能力
            但左神书籍中提到,若一颗二叉树,除去叶子结点的其他结点都有左孩子和右孩子,则可以根据先序和后序确定这颗二叉树
        */
    }
}
