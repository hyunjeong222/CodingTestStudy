import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
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
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 학교의 수
        m = Integer.parseInt(st.nextToken()); // 도로의 수

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        char[] university = new char[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            university[i] = st.nextToken().charAt(0);
        }

        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.add(new Node(u, v, w));
        }
        Collections.sort(list);

        int ans = 0, cnt = 0;
        for (Node next : list) {
            if (find(next.to) != find(next.from)) {
                if (university[next.to] != university[next.from]) {
                    cnt++;
                    union(next.to, next.from);
                    ans += next.value;
                }
            }
        }

        System.out.println(cnt != n-1 ? -1 : ans);

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