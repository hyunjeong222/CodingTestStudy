import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dist;
    static boolean[] checked;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos {
        int x; int d;
        public Pos(int x, int d) {
            this.x = x; this.d = d;
        }
    }
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        int a, b;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            list.get(a).add(new Pos(b, 1));
            list.get(b).add(new Pos(a, 1));
        }
        dist = new int[n+1];
        checked = new boolean[n+1];
        bfs(1);

        int maxIdx = 0;
        int maxCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == max) {
                if (maxIdx == 0) maxIdx = i;
                maxCnt++;
            }
        }
        System.out.println(maxIdx + " " + dist[maxIdx] + " " + maxCnt);
    }

    private static void bfs(int x) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, 0));
        checked[x] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            max = Math.max(max, now.d);
            dist[now.x] = max;

            for (Pos next : list.get(now.x)) {
                if (!checked[next.x]) {
                    checked[next.x] = true;
                    que.offer(new Pos(next.x, now.d+1));
                }
            }
        }
    }
}