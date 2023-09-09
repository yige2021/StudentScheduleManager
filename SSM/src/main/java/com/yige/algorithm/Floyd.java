package com.yige.algorithm;

public class Floyd {
    public static int n = 42;
    public static int[][] mp = new int[50][50];         // 原始地图
    public static int[][] path = new int[50][50];       // 存储路径

    public static void init() {
        for(int i = 0; i <= n; i++)
            for(int j = 0; j <= n; j++) {
                mp[i][j] = 0x3f3f3f3f;
            }
    }

    public static void calcuDistance() {
        // 初始化路径数组
        for(int i = 0; i <= n; i++)
            for(int j = 0; j <= n; j++) {
                path[i][j] = j;
            }

        //Floyd算法求任意两点最短路并获取路径
        for(int k = 0; k <= n; k++)
            for(int i = 0; i <= n; i++)
                for(int j = 0; j <= n; j++) {
                    if (mp[i][k] + mp[k][j] < mp[i][j]) {
                        mp[i][j] = mp[i][k] + mp[k][j];
                        path[i][j] = k;
                    }
                }
    }

}
