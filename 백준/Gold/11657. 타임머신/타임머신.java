import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos {
        int end; int cost;
        public Pos (int end, int cost) {
            this.end = end; this.cost = cost;
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
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        dist = new long[n+1];
        Arrays.fill(dist, INF);

        int u, v, w;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
        }

        boolean flag = BellmanFord(1);

        StringBuilder sb = new StringBuilder();
        if (flag) {
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
        boolean isUpdate = false;

        for (int i = 1; i < n; i++) {
            isUpdate = false;
            for (int j = 1; j <= n; j++) {
                for (Pos now : list.get(j)) {
                    if (dist[j] != INF && dist[now.end] > dist[j] + now.cost) {
                        dist[now.end] = dist[j] + now.cost;
                        isUpdate = true;
                    }
                }
            }

            if (!isUpdate) break;
        }

        if (isUpdate) {
            for (int i = 1; i <= n; i++) {
                for (Pos now : list.get(i)) {
                    if (dist[i] != INF && dist[now.end] > dist[i] + now.cost) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}