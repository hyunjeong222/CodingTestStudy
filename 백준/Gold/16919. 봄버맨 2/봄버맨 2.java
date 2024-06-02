import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c, n;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c ; j++) {
                if (str.charAt(j) == '.') map[i][j] = 0;
                else map[i][j] = 3;
            }
        }

        if (n % 2 == 0) {
            set(n);
        } else {
            for (int i = 2; i <= n; i++) {
                n = n % 4 + 4;
                if (i % 2 == 0) set(i);
                else bomb(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] arr : map) {
            for (int a : arr) {
                if (a == 0) sb.append(".");
                else sb.append("O");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void bomb(int n) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == n) {
                    map[i][j] = 0;

                    for (int k = 0; k < 4; k++) {
                        int nx = dx[k] + i;
                        int ny = dy[k] + j;

                        if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                        if (map[nx][ny] == n) continue;
                        map[nx][ny] = 0;
                    }
                }
            }
        }
    }

    private static void set(int n) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 0) map[i][j] = n+3;
            }
        }
    }
}