import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] floyd = new int[n+1][n+1];
        for (int i = 0; i < results.length; i++) {
            int a = results[i][0];
            int b = results[i][1];

            floyd[a][b] = 1; // a가 b 이김
            floyd[b][a] = -1; // b는 a에게 짐
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    // 1 > 2 2 > 5 1 > 5
                    if (floyd[i][k] == 1 && floyd[k][j] == 1) {
                        floyd[i][j] = 1;
                        floyd[j][i] = -1;
                    }
                    if (floyd[i][k] == -1 && floyd[k][j] == -1) {
                        floyd[i][j] = -1;
                        floyd[j][i] = 1;
                    }
                }
            }
        }

        int ans = 0; // 정확하게 순위를 매길 수 있는 선수의 수

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (floyd[i][j] != 0) cnt++;
            }
            if (cnt == n-1) ans++;
        }
        
        return ans;
    }
}