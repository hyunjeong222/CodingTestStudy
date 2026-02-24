import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Node> list;
    static int[] parent;
    static class Point {
        int num; double x; double y;
        public Point(int num, double x, double y) {
            this.num = num; this.x = x; this.y = y;
        }
    }
    static class Node implements Comparable<Node> {
        int to; int from;
        double value;
        public Node(int to, int from, double value) {
            this.to = to; this.from = from;
            this.value = value;
        }
        @Override
        public int compareTo(Node o) {
            return Double.compare(this.value, o.value);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 별의 수

        Point[] point = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            point[i] = new Point(i, x, y);
        }

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double value = distance(point[i], point[j]);

                list.add(new Node(point[i].num, point[j].num, value));
            }
        }
        Collections.sort(list);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        double ans = 0;
        for (Node next : list) {
            if (find(next.to) != find(next.from)) {
                union(next.to, next.from);
                ans += next.value;
            }
        }

        System.out.println(ans);


        br.close();
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x- p2.x, 2)+Math.pow(p1.y- p2.y, 2));
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