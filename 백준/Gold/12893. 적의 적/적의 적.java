import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> list;
    static int[] checked;
    static int flag = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        checked = new int[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            if (checked[i] == 0) {
                dfs(i, 1);
            }
        }

        System.out.println(flag);

        br.close();
    }

    private static void dfs(int i, int value) {
        if (flag == 0) {
            return;
        } else {
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
}