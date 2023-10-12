import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int t, n, k, a, b, m, u, v;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine()); // 테스트 케이스
        int idx = 1;
        while (t --> 0) {
            sb.append("Scenario ").append(idx++).append(":").append("\n");
            n = Integer.parseInt(br.readLine()); // 유저의 수
            k = Integer.parseInt(br.readLine()); // 친구 관계의 수

            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                // 친구 관계
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                union(a,b);
            }
            m = Integer.parseInt(br.readLine()); // 미리 구할 쌍의 수
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                // 구해야하는 쌍
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                if (find(u) == find(v)) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
            sb.append("\n");
        }
        bw.append(sb);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) return;

        if (x < y) parents[y] = x;
        else parents[x] = y;
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        else return parents[a] = find(parents[a]);
    }
}