import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 100000000;
    static int n, m, t;
    static int s, g, h;
    static PriorityQueue<Pos> pq;
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
    static boolean[] checked;
    // 간선의 가중치를 저장할 때 2를 곱한 값을 저장해 짝수로 만들고,
    // 확인하고자 하는 간선의 가중치에는 -1를 해줘 홀수로 만듬
    // 저장 후 최단거리를 확인했을때, 홀수가 나온다면 해당 경로 포함, 짝수라면 포함하지 않음
    static ArrayList<Integer> candidate;
    static public class Pos implements Comparable<Pos> {
        int end, weight;
        public Pos (int end, int weight) {
            this.end = end; this.weight = weight;
        }
        @Override
        public int compareTo(Pos o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 교차로
            m = Integer.parseInt(st.nextToken()); // 도로
            t = Integer.parseInt(st.nextToken()); // 목적지 후보

            dist = new int[n+1];
            Arrays.fill(dist, INF);

            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 출발지
            //
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int u, v, w;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                if (u == g && v == h || u == h && v == g) {
                    list.get(u).add(new Pos(v, w*2-1));
                    list.get(v).add(new Pos(u, w*2-1));
                } else {
                    list.get(u).add(new Pos(v, w*2));
                    list.get(v).add(new Pos(u, w*2));
                }
            }

            dijkstra(s); // 출발점

            // 목적지 후보
            candidate = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                candidate.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(candidate);

            for (int i : candidate) {
                // 홀수면 해당 경로 포함
                if (dist[i] % 2 == 1) sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dijkstra(int s) {
        checked = new boolean[n+1];
        pq = new PriorityQueue<>();
        pq.offer(new Pos(s, 0));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (checked[now.end]) continue;
            checked[now.end] = true;

            for (Pos next : list.get(now.end)) {
                if (!checked[next.end] && dist[next.end] > dist[now.end] + next.weight) {
                    dist[next.end] = dist[now.end] + next.weight;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}