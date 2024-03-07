import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Pos> list;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos (int x, int y){
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        StringTokenizer st;
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') list.add(new Pos(i, j));
            }
        }
        dfs(0);
        System.out.println("NO");
    }

    private static void dfs(int depth) {
        if (depth == 3) {
            if (isAvailable()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = 'O';
                    dfs( depth+1);
                    map[i][j] = 'X';
                }
            }
        }
    }

    private static boolean isAvailable() {
        for (int l = 0; l < list.size(); l++) {
            Pos now = list.get(l);
            for (int i = 0; i < 4; i++) {
                int nx = now.x;
                int ny = now.y;

                while (true) {
                    nx += dx[i];
                    ny += dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
                    if (map[nx][ny] == 'O') break;
                    if (map[nx][ny] == 'S') return false;
                }
            }
        }
        return true;
    }
}