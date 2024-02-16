import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static HashMap<String, Integer> hm;
    static int len = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        hm = new HashMap<>();
        String[] ans = new String[k];
        for (int i = 0; i < k; i++) {
            String str = br.readLine();
            ans[i] = str;
            len = Math.max(len, str.length());
            hm.put(str, 0);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 1, Character.toString(map[i][j]));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : ans) {
            sb.append(hm.get(s)).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y, int depth, String str) {
        if (hm.containsKey(str)) {
            hm.put(str, hm.get(str) + 1);
        }

        if (depth == len) return; //

        for (int i = 0; i < 8; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            // 환형
            if (nx < 0) nx = n-1;
            else if (nx >= n) nx = 0;
            if (ny < 0) ny = m-1;
            else if (ny >= m) ny = 0;

            dfs(nx, ny, depth+1, str+map[nx][ny]);
        }
    }
}