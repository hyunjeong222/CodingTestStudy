import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static int n, m;
    static ArrayList<ArrayList<Integer>> list;
    static int[] checked;
    static int flag = 1;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        input();
        solve();

        int color1 = 0, color2 = 0;
        if (flag == 0) System.out.println(0);
        else {
            for (int i = 1; i <= n; i++) {
                if (checked[i] == 1) color1++;
                else color2++;
            }

            System.out.println((color1*color2)*2);
        }

        br.close();
    }

    private static void solve() {
        for (int i = 1; i <= n; i++) {
            if (checked[i] == 0) {
                dfs(i ,1);
            }
        }
    }

    private static void dfs(int i, int color) {
        if (flag == 0) return;

        for (int next : list.get(i)) {
            if (checked[next] == 0) {
                checked[next] = color*-1;
                dfs(next, color*-1);
            } else if (checked[next] == color) {
                flag = 0;
                return;
            }
        }
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수풀의 수
        m = Integer.parseInt(st.nextToken()); // 오솔길의 수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int u, v;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        checked = new int[n+1];
    }
}