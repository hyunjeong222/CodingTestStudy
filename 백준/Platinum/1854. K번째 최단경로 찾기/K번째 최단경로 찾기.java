import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static public class Pos implements Comparable<Pos> {
        int end; int dist;
        public Pos(int end, int dist) {
            this.end = end; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Integer>[] distQue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 수
        k = Integer.parseInt(st.nextToken()); // k번째 최단경로

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int a, b, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Pos(b, c));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (distQue[i].size() == k) {
                sb.append(distQue[i].peek()).append("\n");
            } else sb.append(-1).append("\n"); //k번째 경로 존재하지 않으면 -1
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        distQue = new PriorityQueue[n+1];
        // 내림차순
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        };
        for (int i = 0; i <= n; i++) {
            // 최대 k개의 값을 내림차순으로 정렬
            distQue[i] = new PriorityQueue<>(k, comparator);
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(1, 0));
        distQue[1].add(0);

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            for (Pos next : list.get(now.end)) {
                if (distQue[next.end].size() < k) {
                    distQue[next.end].add(now.dist + next.dist);
                    pq.offer(new Pos(next.end, now.dist + next.dist));
                } else if (distQue[next.end].peek() > now.dist + next.dist) {
                    distQue[next.end].poll();
                    distQue[next.end].add(now.dist + next.dist);
                    pq.offer(new Pos(next.end, now.dist + next.dist));
                }
            }
        }
    }
}