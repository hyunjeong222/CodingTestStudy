import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int r, c;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dfs(r, c, 0, 0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int r, int c, int move, int cnt) {
        if (map[r][c] == 1) cnt++;
        
        if (move == 3) {
            if (cnt >= 2) {
                ans = 1;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + r;
            int ny = dy[i] + c;

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || map[nx][ny] == -1) continue;
            
            map[r][c] = -1;
            dfs(nx, ny, move+1, cnt);
            map[r][c] = 0;
        }
    }
}