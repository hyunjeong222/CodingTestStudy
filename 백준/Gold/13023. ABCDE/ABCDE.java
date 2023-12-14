import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int a, b;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        for (int i = 0; i < n; i++) {
            checked = new boolean[n];
            dfs(i, 1);
            if (flag) {
                System.out.println(1);
                System.exit(0);
            }
        }
        System.out.println(0);
        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == 5) {
            flag = true;
            return;
        }

        checked[start] = true;
        for (int next : list.get(start)) {
            if (!checked[next]) {
                dfs(next, depth+1);
            }
        }
        checked[start] = false;
    }
}