import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int ans = 0;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new boolean[n+1][m+1];

        dfs(0);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int depth) {
        if (depth == n*m) {
            ans++;
            return;
        }

        int x = depth/m+1;
        int y = depth%m+1;

        if (map[x-1][y] && map[x][y-1] && map[x-1][y-1]) { // 오른쪽 아래 빼고 모두 넴모가 있는 상태
            dfs(depth+1);
        }  else {
            // 아닐 경우 오른쪽 아래 넴모를 놓아도 되고 아니어도 됨
            dfs(depth+1);
            map[x][y] = true;
            dfs(depth+1);
            map[x][y] = false;
        }
    }
}