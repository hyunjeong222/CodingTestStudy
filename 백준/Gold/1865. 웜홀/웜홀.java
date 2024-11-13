import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, w;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos {
        int end, cost;
        public Pos(int end, int cost) {
            this.end = end; this.cost = cost;
        }
    }
    static int[] dist;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            int s, e, c;
            for (int i = 0; i < m+w; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                if (i < m) { // 도로는 양방향
                    list.get(s).add(new Pos(e, c));
                    list.get(e).add(new Pos(s, c));
                } else { // 웜홀은 단방향
                    list.get(s).add(new Pos(e, -c));
                }
            }

            dist = new int[n+1];
            boolean flag = false;
            for (int i = 1; i <= n; i++) { // 어떠한 한 지점에서 탐색
                if (BellmanFord(i)) { // 음수 사이클 ㅇ
                    flag = true;
                    sb.append("YES").append("\n");
                    break;
                }
            }

            if (!flag) sb.append("NO").append("\n");
        }

        System.out.println(sb.toString());
    }

    private static boolean BellmanFord(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean isUpdate = false;

        // (정점의 개수 - 1)번 동안 최단거리 초기화 작업을 반복
        for (int i = 1; i < n; i++) {
            isUpdate = false;
            // 최단거리 초기화
            for (int j = 1; j <= n; j++) {
                for (Pos now : list.get(j)) {
                    if (dist[j] != INF && dist[now.end] > dist[j] + now.cost) {
                        dist[now.end] = dist[j] + now.cost;
                        isUpdate = true;
                    }
                }
            }

            // 더 이상 최단거리 초기화가 일어나지 않았을 경우 반복문 종료
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