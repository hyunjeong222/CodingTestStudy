import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int a, b;
    static int x, y;
    static int cnt = -1;
    static int[][] map;
    static int[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine()); // 사람의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 촌수를 계산해야 하는 서로 다른 두 사람의 번호
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine()); // 부모 자식들 간의 관계의 개수
        map = new int[n+1][n+1];
        checked = new int[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()); // 부모
            y = Integer.parseInt(st.nextToken()); // 자식

            // 부모 자식은 연결되어 있으므로 노드로 생각
            map[x][y] = 1;
            map[y][x] = 1;
        }

        bfs(a, b);
        bw.append(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int a, int b) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(a);

        while (!que.isEmpty()) {
            int now = que.poll();

            if (now == b) {
                cnt = checked[now];
                return;
            }

            for (int i = 1; i <= n; i++) {
                if (map[now][i] == 1 && checked[i] == 0) {
                    checked[i] = checked[now] + 1;
                    que.offer(i);
                }
            }
        }
    }
}