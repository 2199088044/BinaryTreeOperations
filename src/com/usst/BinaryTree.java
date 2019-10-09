package com.usst;

import java.util.*;

public class BinaryTree
{
    //深度优先遍历
    public static void DFS(TreeNode root)
    {
        if (root == null)
        {
            throw new RuntimeException("空树!");
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty())
        {
            TreeNode node = stack.pop();
            System.out.print(node.value + "   ");
            if (node.right != null)
            {
                stack.push(node.right);
            }
            if (node.left != null)
            {
                stack.push(node.left);
            }
        }
    }

    //广度优先遍历
    public static void BFS(TreeNode root)
    {
        if (root == null)
        {
            throw new RuntimeException("空树!");
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            TreeNode node = queue.remove();
            System.out.print(node.value + "   ");
            if (node.left != null)
            {
                queue.add(node.left);
            }
            if (node.right != null)
            {
                queue.add(node.right);
            }
        }
    }

    public static void traverse(TreeNode root)
    {
        System.out.println("\n先序遍历:");
        preOrder(root);
        System.out.println("\n中序遍历:");
        inOrder(root);
        System.out.println("\n后序遍历:");
        postOrder(root);
        System.out.println("\n深度优先遍历:");
        DFS(root);
        System.out.println("\n广度优先遍历:");
        BFS(root);
    }

    //先序遍历(递归)
    public static void preOrder(TreeNode root)
    {
        if (root == null) return;
        System.out.print(root.value + "   ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //先序遍历(栈实现)
    public static void preOrderStack(TreeNode root)
    {
        if (root == null)
        {
            throw new RuntimeException("空树!");
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty())
        {
            while (node != null)
            {
                System.out.print(node.value + "   ");
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }

    //中序遍历(递归)
    public static void inOrder(TreeNode root)
    {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.value + "   ");
        inOrder(root.right);
    }

    //中序遍历(递归)
    public static void inOrder(List<Integer> list, TreeNode root)
    {
        if (root == null) return;
        inOrder(list, root.left);
//        System.out.print(root.value + "   ");
        list.add(root.value);
        inOrder(list, root.right);
    }

    //中序遍历(栈实现)
    public static void inOrderStack(TreeNode root)
    {
        if (root == null)
        {
            throw new RuntimeException("空树!");
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty())
        {
            while (node != null)
            {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.value + "   ");
            node = node.right;
        }
    }

    //后序遍历(递归)
    public static void postOrder(TreeNode root)
    {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + "   ");
    }

    //后续遍历(双栈实现)
    public static void postOrderStack1(TreeNode root)
    {
        if (root == null)
        {
            throw new RuntimeException("空树!");
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack_help = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty())
        {
            while (node != null)
            {
                stack.push(node);
                stack_help.push(node);
                node = node.right;
            }
            node = stack.pop();
            node = node.left;
        }
        while (!stack_help.empty())
        {
            System.out.print(stack_help.pop().value + "   ");
        }
    }

    //后续遍历(单栈实现),暂未理解
    public static void postOrderStack2(TreeNode root)
    {
        if (root == null) throw new RuntimeException("空树!");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode t = null;//标识是否已经出栈
        while (node != null || !stack.isEmpty())
        {
            while (node != null)
            {
                stack.push(node);
                node = node.left;
            }//node.left==null
            node = stack.peek();//取栈顶元素
            if (node.right == null || t == node.right)//右子树为空或者右子树已经处理过
            {
                t = stack.pop();//出栈，标识是否已经处理过
                System.out.print(t.value + "   ");
                node = null;
            } else node = node.right;
        }
    }

    //二叉树初始化
    public static TreeNode createBinaryTree1(int[] arr, int index)
    {
        TreeNode node = null;
        if (index < arr.length)
        {
            int value = arr[index];
            node = new TreeNode(value);
            node.left = createBinaryTree1(arr, index * 2 + 1);
            node.right = createBinaryTree1(arr, index * 2 + 2);
            return node;
        }
        return node;
    }

    //自己输入值的二叉树初始化,输入的值的格式很重要
    public static TreeNode createBinaryTree2(TreeNode node)
    {
        System.out.println("请输入结点的值,输入0表示为null!");
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        if (value != 0)
        {
            node = new TreeNode(value);
            node.left = createBinaryTree2(node.left);
            node.right = createBinaryTree2(node.right);
        } else
        {
            node = null;
        }
        return node;
    }

    //二叉树循环初始化 https://www.cnblogs.com/crazylqy/p/7688665.html
    public static TreeNode createBinaryTree3(int[] array)
    {
        LinkedList<TreeNode> nodeList = new LinkedList<>();
        // 将一个数组的值依次转换为Node节点
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++)
        {
            nodeList.add(new TreeNode(array[nodeIndex]));
        }
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++)
        {
            // 左孩子
            nodeList.get(parentIndex).left = nodeList.get(parentIndex * 2 + 1);
            // 右孩子
            nodeList.get(parentIndex).right = nodeList.get(parentIndex * 2 + 2);
        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (array.length % 2 == 1)
        {
            nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex * 2 + 2);
        }
        return nodeList.get(0);// root结点
    }

    //判断二叉树是否为平衡二叉树,来源:左神指南
    public static boolean judgeIsBalancedBinaryTree1(TreeNode root)
    {
        boolean[] result = new boolean[1];
        result[0] = true;
        getHeight(root, 1, result);
        return result[0];
    }

    public static int getHeight(TreeNode root, int level, boolean[] res)
    {
        if (root == null)
            return level;
        int leftHeight = getHeight(root.left, level + 1, res);
        if (!res[0])
            return level;
        int rightHeight = getHeight(root.right, level + 1, res);
        if (!res[0])
            return level;
        if (Math.abs(leftHeight - rightHeight) > 1)
            res[0] = false;
        return Math.max(leftHeight, rightHeight);
    }

    //判断二叉树是否为平衡二叉树,缺点是从上往下遍历的过程中,下层结点重复遍历了很多次
    public static boolean judgeIsBalancedBinaryTree2(TreeNode root)
    {
        if (root == null)
            return true;
        int left = depth(root.left);
        int right = depth(root.right);
        return (Math.abs(left - right) <= 1) && judgeIsBalancedBinaryTree2(root.left) && judgeIsBalancedBinaryTree2(root.right);
    }

    //获取二叉树的深度
    public static int depth(TreeNode root)
    {
        if (root == null)
            return 0;
        int left = depth(root.left) + 1;
        int right = depth(root.right) + 1;
        return left > right ? left : right;
    }

    //判断二叉树是否为平衡二叉树,进行优化:改为从下往上遍历:
    // 如果子树是平衡二叉树，则返回子树的高度；
    // 如果发现子树不是平衡二叉树，则返回-1:直接停止遍历，这样至多只对每个结点访问一次。
    //思路来源:https://www.nowcoder.com/questionTerminal/8b3b95850edb4115918ecebdf1b4d222?f=discussion, author:丁满历险记
    public static boolean judgeIsBalancedBinaryTree3(TreeNode root)
    {
        return getDepth(root) != -1;
    }

    //剪枝法? 目前写不出来这种递归
    public static int getDepth(TreeNode root)
    {
        if (root == null)
            return 0;
        int left = getDepth(root.left);
        if (left == -1)
            return -1;
        int right = getDepth(root.right);
        if (right == -1)
            return -1;
        //左右子树都是平衡二叉树,下面这条语句是判断当前结点作为根节点的树是不是平衡二叉树
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    //判断是否是二叉排序树(BST)
    public static boolean isBinarySearchTree(TreeNode root)
    {
        List<Integer> list = new LinkedList<>();
        inOrder(list, root);
        for (int i = 0; i < list.size() - 1; i++)
        {
            if (list.get(i + 1) < list.get(i))
                return false;
        }
        return true;
    }

    //判断是否是完全二叉树(CBT)
    public static boolean isCompleteBinaryTree(TreeNode root)
    {
         /*
         *  1. 按层次遍历该二叉树,从左到右
            2. 如果当前结点有右孩子但没有左孩子,返回false
            3. 如果当前结点有左孩子但没有右孩子,那么之后的结点都必须为叶子结点,否则返回false
            4. 遍历过程中没有返回false,则遍历完后,返回true
         * */
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        TreeNode node;
        TreeNode left;
        TreeNode right;
        queue.add(root);
        boolean leaf = false;
        while (!queue.isEmpty())
        {
            node = queue.remove();
            left = node.left;
            right = node.right;

            if (left != null)
                queue.add(left);
            if (right != null)
                queue.add(right);

            if (left == null && right != null)
                return false;
            if (leaf && (left != null || right != null))
                return false;
            if (left != null && right == null)
                leaf = true;
        }
        return true;
    }

    //通过有序数组生成平衡搜索二叉树
    public static TreeNode generateBSTBySortedArray(int[] sortArray)
    {
        if (sortArray.length == 0)
            return null;
        return generate(sortArray, 0, sortArray.length - 1);
    }

    public static TreeNode generate(int[] sortArray, int start, int end)
    {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(sortArray[mid]);
        root.left = generate(sortArray, start, mid - 1);
        root.right = generate(sortArray, mid + 1, end);
        return root;
    }

    public static TreeNode findNearestCommonAncestor1(TreeNode head, TreeNode node1, TreeNode node2)
    {
        if (head == null || head == node1 || head == node2)
            return head;
        TreeNode left = findNearestCommonAncestor1(head.left, node1, node2);
        TreeNode right = findNearestCommonAncestor1(head.right, node1, node2);
        if (left != null && right != null)
            return head;
        return left != null ? left : right;
    }

    public static TreeNode findNearestCommonAncestor2(TreeNode head, TreeNode node1, TreeNode node2)
    {
        NearestCommonAncestor nearestCommonAncestor = new NearestCommonAncestor(head);
        return nearestCommonAncestor.query(node1, node2);
    }

    //假定树中没有重复值
    public static TreeNode getTreeNodeByValue(TreeNode root, int value)
    {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        TreeNode node;
        queue.add(root);
        while (!queue.isEmpty())
        {
            node = queue.remove();
            if (node.value == value)
                return node;
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        return null;
    }

    //将给定的二叉树变换为其的镜像
    public static void mirror(TreeNode root)
    {
        if (root == null)
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    //复制一颗二叉树
    public static TreeNode copyBinaryTree(TreeNode root)
    {
        if (root == null)
            return null;
        TreeNode node = null;
        node = new TreeNode(root.value);
        node.left = copyBinaryTree(root.left);
        node.right = copyBinaryTree(root.right);
        return node;
    }

    //判断一颗二叉树是不是对称的
    public static boolean isSymmetricBinaryTree(TreeNode root)
    {
        if (root == null)
            return true;
        return judgeIsSymmetricBinaryTree(root.left, root.right);
    }

    public static boolean judgeIsSymmetricBinaryTree(TreeNode node1, TreeNode node2)
    {
        if (node1 == null && node2 == null)
            return true;
        else if (node1 == null || node2 == null)
            return false;
        return node1.value == node2.value && judgeIsSymmetricBinaryTree(node1.left, node2.right) && judgeIsSymmetricBinaryTree(node1.right, node2.left);
    }

    //判断两颗二叉树是否相等
    public static boolean isTwoBinaryTreeEquals(TreeNode node1, TreeNode node2)
    {
        if (node1 == null && node2 == null)
            return true;
        else if (node1 == null || node2 == null)
            return false;
        return node1.value == node2.value && isTwoBinaryTreeEquals(node1.left, node2.left) && isTwoBinaryTreeEquals(node1.right, node2.right);
    }

    public static void main(String[] args)
    {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //对称的二叉树
//        int[] arr = {8, 6, 6, 5, 7, 7, 5};
//        TreeNode root = createBinaryTree1(arr, 0);
        //要构建和上面的方式一样的树应该输入:  1 2 4 0 0 5 0 0 3 6 0 0 7 0 0
        TreeNode root = createBinaryTree2(new TreeNode());

//            TreeNode root = createBinaryTree3(arr);
        System.out.println("先序遍历(递归版): ");
        preOrder(root);
        System.out.println("\n先序遍历(栈版): ");
        preOrderStack(root);
        System.out.println("\n中序遍历(递归版): ");
        inOrder(root);
        System.out.println("\n中序遍历(栈版): ");
        inOrderStack(root);
        System.out.println("\n后序遍历(递归版): ");
        postOrder(root);
        System.out.println("\n后序遍历(双栈版): ");
        postOrderStack1(root);
        System.out.println("\n后序遍历(单栈版): ");
        postOrderStack2(root);
        System.out.println("\nDFS: ");
        DFS(root);
        System.out.println("\nBFS: ");
        BFS(root);

        boolean isAVL = judgeIsBalancedBinaryTree3(root);
        System.out.println("\n该树是平衡二叉树? " + isAVL);

        boolean isBST = isBinarySearchTree(root);
        System.out.println("\n该树是二叉排序树? " + isBST);

        boolean isCBT = isCompleteBinaryTree(root);
        //二叉树初始化输入:  1 2 4 0 0 5 0 0 3 0 7 0 0(删去了结点6)
        System.out.println("\n该树是完全二叉树? " + isCBT);

        //前提:arr必须是有序的且递增
        boolean MUSTBETRUE = isBinarySearchTree(generateBSTBySortedArray(arr));
        System.out.println("生成BST再判断是否是BST? result: " + MUSTBETRUE);

        //在二叉树中找到两个节点的最近公共祖先
        //利用递归,还不是很理解
        /*TreeNode node1 = findNearestCommonAncestor1(root, getTreeNodeByValue(root, 4), getTreeNodeByValue(root, 5));
        System.out.println("两个节点的最近公共祖先是: " + node1.value);*/
        //利用HashMap
        /*TreeNode node2 = findNearestCommonAncestor2(root, getTreeNodeByValue(root, 4), getTreeNodeByValue(root, 6));
        System.out.println("两个节点的最近公共祖先是: " + node2.value);*/

        TreeNode copy = copyBinaryTree(root);
        System.out.println("\nDFS: ");
        DFS(copy);

        boolean isSymmetric = isSymmetricBinaryTree(root);
        System.out.println("\n该树是对称的? " + isSymmetric);
        mirror(copy);
        System.out.println("\nDFS: ");
        DFS(copy);
        System.out.println("\n对称的树翻转后与原来的树相等! " + isTwoBinaryTreeEquals(root, copy));

        //Done
        /*
         * 1.先中后序遍历的递归和非递归实现(6+1个方法,后序遍历非递归有两种)
         * 2.二叉树建立的3个方式(3个方法)
         * 3.DFS&BFS的非递归实现(2个方法)
         * 4.DFS递归实现:二叉树的深度优先遍历即先序遍历
         * */

        //To-Do
        /*
         * 实现更多的功能:https://www.cnblogs.com/dawnyxl/p/9047437.html
         * */
    }
}
