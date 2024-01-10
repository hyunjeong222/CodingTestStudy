import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static ArrayList<Pos> list;
    static Pos[] virus;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0 ,0, -1, 1};
    static public class Pos {
        int x; int y; int time;
        public Pos(int x, int y, int time) {
            this.x = x; this.y = y; this.time = time;
        }
    }
    static int ans = Integer.MAX_VALUE; // 최소 시간
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 연구소의 크기
        m = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스 개수
        map = new int[n][n];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) list.add(new Pos(i, j, 0));
            }
        }
        
        virus = new Pos[m];
        dfs(0, 0);

        if (ans == Integer.MAX_VALUE) bw.append(-1 + "\n");
        else bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start, int count) {
        if (count == m) {
            bfs();
            return;
        }

        for (int i = start; i < list.size(); i++) {
            virus[count] = list.get(i);
            dfs(i+1, count+1);
        }
    }

    private static void bfs() {
        Queue<Pos> que = new LinkedList<>();
        checked = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            int x = virus[i].x; int y = virus[i].y;
            checked[x][y] = true;
            que.offer(new Pos(x, y, 0));
        }

        int time = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            Pos now = que.poll();
            time = now.time;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 4; j++) {
                    int nx = dx[j] + now.x;
                    int ny = dy[j] + now.y;

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !checked[nx][ny]) {
                        if (map[nx][ny] != 1) {
                            checked[nx][ny] = true;
                            que.offer(new Pos(nx, ny, now.time+1));
                        }
                    }
                }
            }
        }

        if (mapCheck(checked)) ans = Math.min(ans, time);
    }

    private static boolean mapCheck(boolean[][] checked) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 1 && !checked[i][j]) return false;
            }
        }
        return true;
    }
}