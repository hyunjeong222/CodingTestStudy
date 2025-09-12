import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static ArrayList<Pos> virus;
    static Pos[] active;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y; int time;
        public Pos(int x, int y, int time) {
            this.x = x; this.y = y; this.time = time;
        }
    }
    static int zeroCnt;
    static int minTime = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 연구소 크기
        m = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스 개수

        // 0 : 빈 칸, 1 : 벽, 2 : 비활성 바이러스
        map = new int[n][n];
        virus = new ArrayList<>();
        zeroCnt = 0; // 바이러스로 채울 칸의 개수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) zeroCnt++;
                else if (map[i][j] == 2) {
                    virus.add(new Pos(i, j, 0));
                }
            }
        }

        active = new Pos[m];
        if (zeroCnt == 0) { // 빈 칸이 없다면 퍼트릴 곳도 없음
            System.out.println(0);
        } else {
            // m개의 바이러스 활성화 조합 구하기
            dfs(0, 0);
            System.out.println(minTime != Integer.MAX_VALUE ? minTime : -1);
        }

        br.close();
    }

    private static void dfs(int depth, int start) {
        if (depth == m) { // 모두 활성화됐다면
            bfs(zeroCnt); // 최소 시간 구하기
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            active[depth] = virus.get(i);
            dfs(depth+1, i+1);
        }
    }

    private static void bfs(int zeroCnt) {
        Queue<Pos> que = new LinkedList<>();
        boolean[][] checked = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            Pos now = active[i];
            que.offer(new Pos(now.x, now.y, 0));
            checked[now.x][now.y] = true;
        }

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (rangeCheck(nx, ny)) continue;
                if (checked[nx][ny] || map[nx][ny] == 1) continue;

                if (map[nx][ny] == 0) { // 빈 칸이라면 바이러스를 퍼트릴 수 있음
                    zeroCnt--;
                }

                if (zeroCnt == 0) { // 모든 칸을 바이러스로 채웠다면
                    minTime = Math.min(minTime, now.time+1);
                    return;
                }

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny, now.time+1));
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}