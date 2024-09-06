import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        int m = Integer.parseInt(st.nextToken()); // 도로의 개수

        map = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                map[i][j] = INF;
            }
        }

        int a, b, t;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            map[a][b] = t;
        }

        floyd();

        int k = Integer.parseInt(br.readLine());
        int[] friends = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }

        // 왕복시간들 중 최대가 최소가 되는 도시 X 찾기
        int[] maxDist = new int[n+1];
        int min = INF;
        for (int i = 1; i <= n; i++) {
            for (int f : friends) {
                // 준형이와 친구들의 도시 별 왕복시간 중 최댓값 저장
                maxDist[i] = Math.max(maxDist[i], map[i][f] + map[f][i]);
            }
            min = Math.min(min, maxDist[i]);
        }

        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (min >= maxDist[i]) list.add(i);
        }
        Collections.sort(list);
        for (int l : list) {
            sb.append(l).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void floyd() {
        for (int k = 1; k <= n; k++) { // 경유지
            for (int i = 1; i <= n; i++) { // 출발지
                for (int j = 1; j <= n; j++) { // 도착지
                    if (i == j) continue;
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
    }
}