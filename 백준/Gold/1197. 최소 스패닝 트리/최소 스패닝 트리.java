import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int v, e;
    static ArrayList<ArrayList<Node>> list;
    static boolean[] checked;
    static class Node implements Comparable<Node> {
        int to; int value;
        public Node(int to, int value) {
            this.to = to; this.value = value;
        }
        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수

        list = new ArrayList<>();
        checked = new boolean[v+1];
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        int a, b, c;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        prim(1);

        System.out.println(ans);

        br.close();
    }

    private static void prim(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int node = now.to;
            int value = now.value;

            if (checked[node]) continue;
            checked[node] = true;
            ans += value;

            for (Node next : list.get(node)) {
                if (!checked[next.to]) pq.offer(next);
            }
        }
    }
}