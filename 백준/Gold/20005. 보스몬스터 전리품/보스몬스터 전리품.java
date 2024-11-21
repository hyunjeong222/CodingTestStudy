import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m, n, p;
    static int bossX, bossY, hp;
    static char[][] map;
    static int[] attack;
    static Queue<Pos> que;
    static public class Pos {
        int x; int y; int time;
        public Pos(int x, int y, int time) {
            this.x = x; this.y = y; this.time = time;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        init();
        
        int ans = bfs();
        System.out.println(ans);
    }

    private static int bfs() {
        que = new LinkedList<>();
        boolean[][] checked = new boolean[m][n];

        que.offer(new Pos(bossX, bossY, 0));
        checked[bossX][bossY] = true;

        int totalPower = 0; // 보스를 공격할 수 있는 플레이어들의 총 파워
        int playerCnt = 0; // 전리품을 가져갈 수 있는 플레이어의 수
        int prev = 0; // 이전시간

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.time != prev) {
                hp -= totalPower;
                prev = now.time;

                if (hp <= 0) break; // 보스를 잡았다면 종료
            }

            if (map[now.x][now.y] != '.') { // 보스가 플레이어를 만났다면
                int player = map[now.x][now.y]-'a';
                playerCnt++; // 플레이어 증가
                totalPower += attack[player]; // 만난 플레이어의 공격력 누적
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (rangeCheck(nx, ny)) continue;
                if (checked[nx][ny] || map[nx][ny] == 'X') continue;

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny, now.time+1));
            }
        }

        return playerCnt;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken()); // 플레이어수

        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'B') {
                    bossX = i; bossY = j;
                    map[i][j] = '.';
                }
            }
        }

        attack = new int[p];
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int player = st.nextToken().charAt(0)-'a';
            int dps = Integer.parseInt(st.nextToken()); // 공격력
            attack[player] = dps;
        }

        hp = Integer.parseInt(br.readLine()); // 보스몬스터의 체력

        br.close();
    }
}