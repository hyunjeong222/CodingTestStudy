import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, h;
    static int[][] map;
    static boolean[] checked;
    static int ans = 0; // 진우가 마신 민초우유의 최대 개수
    static ArrayList<pos> list;
    static pos home;
    static public class pos {
        int x, y;
        public pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 민초마을의 크기 (NXN)
        m = Integer.parseInt(st.nextToken()); // 진우의 초기체력
        h = Integer.parseInt(st.nextToken()); // 민초우유 마실때 마다 증가하는 체력의 양
        
        map = new int[n][n]; // 민초마을
        // 민초 마을 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1 진우의 집, 2 민초 우유, 0 빈 땅
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) home = new pos(i, j);
                else if (map[i][j] == 2) list.add(new pos(i, j)); 
            }
        }

        checked = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            pos cur = list.get(i);
            int dist = Math.abs(home.x - cur.x) + Math.abs(home.y - cur.y);

            if (dist <= m) {
                dfs(cur, i, m-dist+h, 1);
            }
        }

        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(pos cur, int idx, int hp, int depth) {
        checked[idx] = true;

        for (int i = 0; i < list.size(); i++) {
            if (!checked[i]) {
                pos next = list.get(i);
                int dist = Math.abs(cur.x - next.x) + Math.abs(cur.y - next.y);

                if (dist <= hp) {
                    dfs(next, i, hp-dist+h, depth+1);
                }
            }
        }

        int dist = Math.abs(cur.x - home.x) + Math.abs(cur.y - home.y);
        if (dist <= hp) ans = Math.max(ans, depth);

        checked[idx] = false;
    }
}