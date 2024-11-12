import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Pos> list;
    static public class Pos {
        int u; int v; int w;
        public Pos(int u, int v, int w) {
            this.u = u; this.v = v; this.w = w;
        }
    }
    static long[] dist;
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        dist = new long[n+1];
        Arrays.fill(dist, INF);

        int u, v, w;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.add(new Pos(u, v, w));
        }

        boolean flag = BellmanFord(1);

        StringBuilder sb = new StringBuilder();
        if (!flag) {
            sb.append(-1).append("\n");
        } else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == INF) sb.append(-1).append("\n");
                else sb.append(dist[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean BellmanFord(int start) {
        dist[start] = 0;
        for (int i = 0; i < n; i++) { // 정점의 개수
            for (int j = 0; j < m; j++) { // 간선의 개수
                Pos now = list.get(j); // 현재 간선

                // 현재 간선의 들어오는 정점에 대해 비교
                if (dist[now.u] != INF && dist[now.v] > dist[now.u] + now.w) {
                    dist[now.v] = dist[now.u] + now.w;
                }
            }
        }

        // 음의 가중치 확인
        for (int i = 0; i < m; i++) {
            Pos now = list.get(i); // 현재 간선

            // 현재 간선의 들어오는 정점에 대해 비교 -> 더 작은 값 생기면 음수 사이클 존재
            if (dist[now.u] != INF && dist[now.v] > dist[now.u] + now.w) {
                return false;
            }
        }

        return true;
    }
}