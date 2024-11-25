import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static int n, m;
    static int[] root;
    static boolean[] checked;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 마약 공급책 수
        m = Integer.parseInt(st.nextToken()); // 관계 수

        root = new int[n];
        checked = new boolean[n];

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int u, v;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = st.nextToken().charAt(0) - 'A';
            v = st.nextToken().charAt(0) - 'A';

            list.get(u).add(v);
            root[v]++;
        }

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken()); // 경찰이 소재를 파악하고 있는 마약 공급책들의 수
        for (int i = 0; i < num; i++) {
            int target = st.nextToken().charAt(0) - 'A';
            checked[target] = true;
        }

        for (int i = 0; i < n; i++) {
            if (root[i] == 0) {
                dfs(i);
            }
        }

        System.out.println(ans);
    }

    private static void dfs(int i) {
        if (!checked[i]) {
            for (int now : list.get(i)) {
                if (!checked[now]) {
                    ans++;
                    dfs(now);
                }
            }
        }
    }
}