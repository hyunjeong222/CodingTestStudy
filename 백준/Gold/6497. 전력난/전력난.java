import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m, n;
    static int[] parent;
    static class Node implements Comparable<Node> {
        int to; int from;
        int value;
        public Node(int to, int from, int value) {
            this.to = to; this.from = from;
            this.value = value;
        }
        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();

        StringTokenizer st;
        ArrayList<Node> list;
        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); // 집의 수
            n = Integer.parseInt(st.nextToken()); // 길의 수

            if (m == 0 && n == 0) break;

            parent = new int[m+1];
            for (int i = 0; i <= m; i++) {
                parent[i] = i;
            }

            list = new ArrayList<>();
            int a, b, c;
            int totalCost = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                list.add(new Node(a, b, c));
                totalCost += c;
            }
            Collections.sort(list);

            int minCost = 0;
            for (Node next : list) {
                if (find(next.to) != find(next.from)) {
                    union(next.to, next.from);
                    minCost += next.value;
                }
            }

            sb.append(totalCost-minCost).append("\n"); // 절약할 수 있는 최대 비용
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u != v) {
            if (u > v) parent[u] = v;
            else parent[v] = u;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}