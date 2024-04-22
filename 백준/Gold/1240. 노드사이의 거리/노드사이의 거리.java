import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos {
        int node; int d;
        public Pos(int node, int d) {
            this.node = node; this.d = d;
        }
    }
    static Queue<Pos> que;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());

        }
        int u, v, d;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            list.get(u).add(new Pos(v, d));
            list.get(v).add(new Pos(u, d));
        }

        StringBuilder sb = new StringBuilder();
        int start, end;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            sb.append(find(start, end)).append("\n");
        }

        System.out.println(sb);
    }

    private static int find(int start, int end) {
        checked = new boolean[n+1];
        que = new LinkedList<>();
        que.offer(new Pos(start, 0));
        checked[start] = true;

        int dist = 0;
        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.node == end) {
                dist = now.d;
                break;
            }

            for (Pos next : list.get(now.node)) {
                if (!checked[next.node]) {
                    que.offer(new Pos(next.node, now.d+next.d));
                    checked[next.node] = true;
                }
            }
        }

        return dist;
    }
}