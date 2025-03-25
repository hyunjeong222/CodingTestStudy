import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> list;
    static int[] checked;
    static int flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            checked = new int[n+1];

            int x, y;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                list.get(x).add(y);
                list.get(y).add(x);
            }

            flag = 1;
            for (int i = 1; i <= n; i++) {
                if (checked[i] == 0) {
                    dfs(i, 1);
                }
            }

            if (flag == 0) sb.append("NO").append("\n");
            else sb.append("YES").append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int i, int value) {
        if (flag == 0) return;

        checked[i] = value;
        for (int next : list.get(i)) {
            if (checked[next] == 0) {
                checked[next] = value*-1;
                dfs(next, value*-1);
            } else if (checked[next] == value) {
                flag = 0;
                return;
            }
        }
    }
}