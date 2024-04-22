import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = 1;
            }
        }

        arr = new int[m*2-1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            for (int j = 0; j < 3; j++) {
                int cnt = Integer.parseInt(st.nextToken());
                while (cnt --> 0) {
                    arr[idx++] = j;
                }
            }

            sizeUp();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void sizeUp() {
        int idx = 0;
        for (int i = m-1; i > 0; i--) {
            map[i][0] += arr[idx++];
        }
        for (int i = 0; i < m; i++) {
            map[0][i] += arr[idx++];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                map[i][j] = Math.max(map[i-1][j-1], Math.max(map[i-1][j], map[i][j-1]));
            }
        }
    }
}