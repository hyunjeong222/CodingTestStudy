import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
    static int n, m, x;
    // 원래 그래프 (x에서 각 노드로 가는 최단 거리)
    static ArrayList<ArrayList<Pos>> list;
    // 간선을 반대로 저장한 그래프 (각 노드에서 x로 가는 최단 거리)
    static ArrayList<ArrayList<Pos>> reverseList;
    static int[] dist; // x -> 각 노드 최단 거리
    static int[] reverseDist; // 각 노드 -> x 최단 거리
    static boolean[] checked;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int end; // 도착 노드
        int dist; // 가중치
        public Pos(int end, int dist) {
            this.end = end; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            // 거리 기준 오름차순 정렬
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 마을 수
        m = Integer.parseInt(st.nextToken()); // 도로 수
        x = Integer.parseInt(st.nextToken()); // 파티 장소
 
        list = new ArrayList<>();
        reverseList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }
 
        // 도로 정보
        int u, v, w;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            
            // 원래 방향 그래프
            list.get(u).add(new Pos(v, w));
            // 간선을 반대로 저장한 그래프
            reverseList.get(v).add(new Pos(u, w));
        }
        
        // 각 노드 -> x 최단 거리
        reverseDist = new int[n+1];
        Arrays.fill(reverseDist, Integer.MAX_VALUE);
        Dijkstra(reverseList, reverseDist, x);
        
        // x -> 각 노드 최단 거리
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Dijkstra(list, dist, x);
 
        int ans = 0; // 왕복 시간 중 최대값 계산
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, reverseDist[i]+dist[i]);
        }
        
        System.out.println(ans);
        
        br.close();
    }
 
    private static void Dijkstra(ArrayList<ArrayList<Pos>> list, int[] dist, int start) {
        checked = new boolean[n+1];
        
        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        
        dist[start] = 0;
 
        while (!pq.isEmpty()) {
            int now = pq.poll().end;
 
            if (checked[now]) continue;
            checked[now] = true;
 
            for (Pos next : list.get(now)) {
                if (dist[next.end] > dist[now]+ next.dist) {
                    dist[next.end] = dist[now]+ next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}