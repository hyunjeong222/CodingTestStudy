import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, p;
    static char[][] map;
    static int[] move, castles;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Pos>[] que;
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        move = new int[p+1];
        castles = new int[p+1];
        que = new Queue[p+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= p; i++) {
            move[i] = Integer.parseInt(st.nextToken());
            que[i] = new LinkedList<>();
        }

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (Character.isDigit(map[i][j])) {
                    int userNum = map[i][j]-'0';
                    que[userNum].add(new Pos(i, j));
                    castles[userNum]++;
                }
            }
        }
        
        int user = 1;
        int round = 0;
        while (true) {
            int maxDist = move[user];
            int cnt = extendCastle(que[user], maxDist, user);

            castles[user] += cnt;
            round += cnt;

            user++;
            if (user == p+1) {
                if (round == 0) break;
                round = 0;
                user = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= p; i++) {
            sb.append(castles[i]).append(" ");
        }

        System.out.println(sb.toString());

    }

    private static int extendCastle(Queue<Pos> q, int maxDist, int user) {
        int cnt = 0;
        int dist = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Pos now = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = dx[d] + now.x;
                    int ny = dy[d] + now.y;

                    if (rangeCheck(nx, ny)) continue;
                    if (map[nx][ny] == '.') {
                        q.offer(new Pos(nx, ny));
                        map[nx][ny] = (char)(user+'0');
                        cnt++;
                    }
                }
            }
            dist++;
            if (dist > maxDist) break;
        }
        return cnt;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}