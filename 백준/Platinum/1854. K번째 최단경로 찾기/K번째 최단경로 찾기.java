import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Integer>[] distQue;
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        int m = Integer.parseInt(st.nextToken()); // 도로의 수
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
            } else sb.append(-1).append("\n"); // k번째 경로 존재하지 않으면 -1
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        distQue = new PriorityQueue[n+1];
        // 내림차순
        for (int i = 0; i <= n; i++) {
            // 최대 k개의 경로 저장
            distQue[i] = new PriorityQueue<>((o1,o2)->o2-o1);
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(1, 0));
        distQue[1].add(0);

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int nowNode = now.end;

            for (Pos next : list.get(nowNode)) {
                int nextNode = next.end;
                int nextDist = now.dist + next.dist;

                // k개의 경로가 저장되지 않은 경우
                if (distQue[nextNode].size() < k) {
                    distQue[nextNode].add(nextDist); // 새로운 경로 추가
                    pq.offer(new Pos(nextNode, nextDist));
                } // 기존 k번째 경로보다 더 짧은 경로인 경우
                else if (distQue[nextNode].peek() > nextDist) {
                    distQue[nextNode].poll(); // 가장 긴 경로 제거
                    distQue[nextNode].add(nextDist); // 새로운 경로 추가
                    pq.offer(new Pos(nextNode, nextDist));
                }
            }
        }
    }
}