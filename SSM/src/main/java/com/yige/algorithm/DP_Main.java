package com.yige.algorithm;

import com.yige.classes.Account;
import com.yige.system.File;

public class DP_Main {
    public static int INF = 0x3f3f3f3f;
    public static int[][] dis = new int[50][50];    //只有起点+必经点+终点的图
    public static int[] p = new int[12];            //起点+必经点+终点
    public static int[] table = new int[50];        //映射原地图与新地图点编号
    public static int num;

    public static String entrance(String sp, String ep) {
        Floyd.init();
        File.readEdges();
        Floyd.calcuDistance();

        String res = "";

        if (num == 0) {
            int startPoint = Integer.parseInt(sp);
            int endPoint = Integer.parseInt(ep);

            res = sp + "->" + ep + "距离：" + Floyd.mp[startPoint][endPoint];
            return res;
        }

        DP[][] dp = new DP[1 << 12][12];

        for (int i = 0; i < (1 << 12); i++) {
            for (int j = 0; j < 12; j++) {
                dp[i][j] = new DP();
                dp[i][j].path = new int[12];
                dp[i][j].len = INF;
                dp[i][j].now = 0;
                for(int k = 0; k < 12; k++) {
                    dp[i][j].path[k] = 0;
                }
            }
        }

        for (int i = 0; i < num - 1; i++)
            for (int j = i + 1; j < num; j++) {
                    dis[i][j] = Floyd.mp[p[i]][p[j]];
                    dis[j][i] = Floyd.mp[p[j]][p[i]];
                    table[i] = p[i];
                    table[j] = p[j];
            }

        dp[1][0].len = 0;

        for (int s = 0; s < (1 << num); s++) {

            for (int i = 0; i < num; i++) {
                int bit = (s & (1 << i));
                if (bit != 0) {
                    for (int j = 0; j < num; j++) {
                        //第j个城市与第i个城市不相同，且第j个城市也在走过的集合S内
                        int bit2 = s & (1 << j);
                        if (j != i && bit2 != 0) {
                            //用异或去掉第i个点
                            if (dp[s][i].len > dp[s ^ (1 << i)][j].len + dis[j][i]) {
                                dp[s][i].len = dp[s ^ (1 << i)][j].len + dis[j][i];

                                int ee = dp[s ^ (1 << i)][j].now;

                                for (int f = 0; f <= ee; f++)
                                    dp[s][i].path[f] = dp[s ^ (1 << i)][j].path[f];
                                dp[s][i].path[ee] = i;
                                dp[s][i].now = ee + 1;
                            }
                        }
                    }
                }
            }
        }

        int[] route = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int j = 0; j < dp[(1 << num) - 1][num - 1].now; j++) {
            route[j + 1] = dp[(1 << num) - 1][num - 1].path[j];
        }

        for (int j = 1; j < num; j++) {
            int a = table[route[j - 1]], b = table[route[j]];
            int tmp = a;

            res = res + Account.locInfo.get(a).label;
            while(tmp != b) {
                res = res + "->" + Account.locInfo.get(Floyd.path[tmp][b]).label;
                tmp = Floyd.path[tmp][b];
            }
            res = res + " ，长度：" + Floyd.mp[a][b] + "\n";
        }
        res = res + "总长度：" + dp[(1 << num) - 1][num - 1].len;
        return res;
    }
}
