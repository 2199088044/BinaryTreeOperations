package com.usst;

//number的意思见左神书籍:统计所有不同的二叉树种数
public class NumberOfTrees
{
    public static int getNumberOfTrees(int n)
    {
        if (n <= 1)
            return 1;
        int[] num = new int[n + 1];
        num[0] = 1;
        for (int i = 1; i < n + 1; i++)
        {
            for (int j = 1; j < i + 1; j++)
            {
                num[i] += num[j - 1] * num[i - j];
            }
        }
        return num[n];
    }

    public static void main(String[] args)
    {
        System.out.println(getNumberOfTrees(3));
    }
}
