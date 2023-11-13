import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<pos> list;
    static public class pos {
        int x; int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int cheeseCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    list.add(new pos(i, j));
                    cheeseCnt++;
                }
            }
        }

        int time = 0;
        while (cheeseCnt != 0) {
            time++;
            checked = new boolean[n][m];
            dfs(0, 0);
            melting(); 
        }

        bw.append(time + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void melting() {
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i).x;
            int y = list.get(i).y;
            int cnt = 0;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (map[nx][ny] == 2) cnt++;
            }

            if (cnt >= 2) {
                map[x][y] = 0;
                cheeseCnt--;
                list.remove(i);
                i--;
            }
        }
    }

    private static void dfs(int x, int y) {
        checked[x][y] = true;
        map[x][y] = 2;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!checked[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == 2)) {
                    dfs(nx, ny);
                }
            }
        }
    }
}