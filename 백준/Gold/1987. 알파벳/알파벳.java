import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static int[][] map;
    static boolean[] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0; // 말이 지난 최대 칸 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 세로
        c = Integer.parseInt(st.nextToken()); // 가로

        map = new int[r][c];
        checked = new boolean[26];
        // 보드 입력
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }

        dfs(0, 0, 0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int depth) {
        if (checked[map[x][y]]) {
            ans = Math.max(ans, depth);
            return;
        } else {
            checked[map[x][y]] = true;
            for (int i = 0; i < 4; i++) {
                
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                    dfs(nx, ny, depth+1);
                }
            }
            checked[map[x][y]] = false;
        }
    }
}