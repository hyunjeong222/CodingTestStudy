import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k;
    static char[][] map;
    static boolean[][] checked;
    static int count; // 가짓수
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        checked = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        checked[r-1][0] = true; // 출발지
        dfs(r-1 ,0 , 1);
        bw.append(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int depth) {
        if (x == 0 && y == c-1) { // 집에 도착했고
            if (depth == k) count++; // k와 같다면 가짓수 증가
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
                if (!checked[nx][ny] && map[nx][ny] != 'T') {
                    checked[nx][ny] = true;
                    dfs(nx, ny, depth+1);
                    checked[nx][ny] = false;
                }
            }
        }
    }
}