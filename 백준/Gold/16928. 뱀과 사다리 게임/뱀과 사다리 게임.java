import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] map;
    static int[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[101];
        for (int i = 1; i < map.length; i++) {
            map[i] = i;
        }

        while (n+m --> 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u] = v;
        }

        checked = new int[101];
        int ans = bfs();
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        checked[1] = 0;

        while (true) {
            int cur = que.poll();

            for (int i = 1; i < 7; i++) {
                int next = cur+i;

                if (next > 100) continue;

                if (checked[map[next]] == 0) {
                    que.offer(map[next]);
                    checked[map[next]] = checked[cur]+1;
                }
                if (map[next] == 100) return checked[100];
            }
        }
    }
}