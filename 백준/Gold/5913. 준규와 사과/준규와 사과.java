import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][] map = new boolean[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int total; // 방문해야 할 전체 칸 수
    static int cnt = 0; // 사과를 모두 수확하는 방법의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb  = new StringBuilder();
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            map[a][b] = true;
        }

        total = 25-k;
        map[0][0] = true; // 시작점
        dfs(0, 0, 1);

        System.out.println(cnt);

        br.close();
    }

    private static void dfs(int x, int y, int checkFlag) {
        if (x == 4 && y == 4) { // 도착점
            if (checkFlag == total) { // 모든 사과 수확
                cnt++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i]+x;
            int ny = dy[i]+y;

            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !map[nx][ny]) {
                map[nx][ny] = true;
                dfs(nx, ny, checkFlag+1);
                map[nx][ny] = false;
            }
        }
    }
}