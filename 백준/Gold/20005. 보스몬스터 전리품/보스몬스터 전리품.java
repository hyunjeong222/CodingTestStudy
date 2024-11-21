import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m, n, p;
    static char[][] map;
    static Queue<Pos> que;
    static HashMap<Character, Player> playerMap;
    static public class Pos {
        char player;
        int x; int y;
        public Pos(char player, int x, int y) {
            this.player = player;
            this.x = x; this.y = y;
        }
    }
    static public class Player {
        int id; int dps;
        public Player(int id, int dps) {
            this.id = id; this.dps = dps;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken()); // 플레이어 수
        map = new char[m][n];
        que = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] != '.' && map[i][j] != 'X' && map[i][j] != 'B') { // 플레이어 아이디
                    que.offer(new Pos(map[i][j], i, j)); // 플레이어와 현재 위치 저장
                }
            }
        }

        playerMap = new HashMap<>();
        int num = 0; // 플레이어 고유번호, 각 플레이어의 방문체크를 위해
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            char id = st.nextToken().charAt(0);
            int dps = Integer.parseInt(st.nextToken()); // 1초당 보스몬스터의 체력을 줄일 수 있는지 의미
            playerMap.put(id, new Player(num++, dps));
        }

        int hp = Integer.parseInt(br.readLine()); // 보스 모스터 체력

        int ans = bfs(hp);
        System.out.println(ans);
    }

    private static int bfs(int hp) {
        boolean[][][] checked = new boolean[m][n][p];
        ArrayList<Character> attack = new ArrayList<>(); // 보스랑 같은 위치라 공격할 수 있는 플레이어

        while (hp > 0) {
            int size = que.size();
            while (size-- > 0) {
                Pos now = que.poll();
                
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + now.x;
                    int ny = dy[i] + now.y;

                    if (rangeCheck(nx, ny)) continue;
                    // 현재 플레이어가 방문했던 곳이나 길이 X
                    if (checked[nx][ny][playerMap.get(now.player).id] || map[nx][ny] == 'X') continue;

                    checked[nx][ny][playerMap.get(now.player).id] = true;
                    if (map[nx][ny] == 'B') attack.add(now.player);
                    else que.offer(new Pos(now.player, nx, ny));
                }
            }

            for (char p : attack) { // 공격할 수 있는 모든 플레이어 공격
                hp -= playerMap.get(p).dps;
            }
        }

        return attack.size();
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}