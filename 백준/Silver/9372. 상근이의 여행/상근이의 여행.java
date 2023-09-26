import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int t;
    static int n, m;
    static int u, v;
    static ArrayList<Integer>[] list;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

        StringTokenizer st;

        while (t --> 0) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); // 국가의 수 (정점)
            m = Integer.parseInt(st.nextToken()); // 비행기의 종류 (간선)

            checked = new boolean[n+1];
            list = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                // 정점의 정보
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                list[u].add(v);
                list[v].add(u);
            }

            System.out.println(dfs(1));
        }
        br.close();
    }

    private static int dfs(int start) {
        checked[start] = true;
        int cnt = 0;

        for (int next : list[start]) {
            if (!checked[next]) {
                cnt += dfs(next)+1;
            }
        }
        return cnt;
    }
}