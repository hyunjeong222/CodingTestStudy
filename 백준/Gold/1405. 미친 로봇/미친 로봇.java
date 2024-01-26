import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static double[] percent;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static double ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        percent = new double[4];
        for (int i = 0; i < 4; i++) {
            percent[i] = Double.parseDouble(st.nextToken()) * 0.01;
        }
        checked = new boolean[n*2+1][n*2+1];
        checked[n][n] = true;
        dfs(n, n, 0, 1);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int cnt, double per) {
        if (cnt == n) {
            ans += per;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (percent[i] == 0) continue;

            if (checked[nx][ny]) continue;

            checked[nx][ny] = true;
            dfs(nx, ny, cnt+1, percent[i]*per);
            checked[nx][ny] = false;
        }
    }
}