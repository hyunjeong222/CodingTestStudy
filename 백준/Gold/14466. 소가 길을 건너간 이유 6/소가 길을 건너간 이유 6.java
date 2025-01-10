import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k, r;
    static int[][] cow;
    static boolean[][] checked;
    static ArrayList<Pos>[][] road;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // n*n
        k = Integer.parseInt(st.nextToken()); // 소의 수
        r = Integer.parseInt(st.nextToken()); // 길의 위치 정보

        road = new ArrayList[n][n]; // 길에 대한 정보 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                road[i][j] = new ArrayList<>();
            }
        }
        int a, b, c, d;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            d = Integer.parseInt(st.nextToken())-1;

            road[a][b].add(new Pos(c, d));
            road[c][d].add(new Pos(a, b));
        }
        
        cow = new int[k][2]; // 소가 있는 위치
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            
            cow[i][0] = a;
            cow[i][1] = b;
        }

        // 결과적으로 쌍을 구하는 것이기 때문에 자기보다 뒤에 있는 소들만 계산하면 된다.
        for (int i = 0; i < k; i++) {
            bfs(cow[i][0], cow[i][1], i);
        }

        // 길을 건너지 않으면 만날 수 없는 소가 몇 쌍 ?
        System.out.println(ans);
    }

    private static void bfs(int x, int y, int idx) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y));

        checked = new boolean[n][n];
        checked[x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int d = 0; d < 4; d++) {
                int nx = dx[d] + now.x;
                int ny = dy[d] + now.y;

                if (rangeCheck(nx, ny)) continue;

                boolean flag = false; // 길이 있는지 체크
                for (int i = 0; i < road[now.x][now.y].size(); i++) {
                    Pos tmp = road[now.x][now.y].get(i); // 길 정보

                    if (nx == tmp.x && ny == tmp.y) {
                        flag = true;
                        break;
                    }
                }

                // 길이 있다면 탐색 X
                if (flag || checked[nx][ny]) continue;

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny));
            }
        }

        for (int i = idx; i < cow.length; i++) {
            if (!checked[cow[i][0]][cow[i][1]]) ans++;
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}