import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int a, b, c;
    static long ans = Integer.MIN_VALUE;
    static boolean[] checked;
    static ArrayList<Edge>[] list;
    static public class Edge {
        int to, c;
        public Edge(int to, int c) {
            this.to = to;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        checked = new boolean[n+1];
        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= n-1; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(b,c));
            list[b].add(new Edge(a,c));
        }

        checked[1] = true;
        dfs(1, 0);
        System.out.println(ans);
    }

    private static void dfs(int start, long cnt) {
        ans = Math.max(ans, cnt);

        for (Edge next : list[start]) {
            if (checked[next.to]) continue;
            checked[next.to] = true;
            dfs(next.to, cnt+next.c);
        }
    }
}