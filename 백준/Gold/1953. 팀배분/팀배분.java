import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] hate;
    static boolean[] checked;
    static ArrayList<Integer> blue;
    static ArrayList<Integer> white;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int a, b;
        hate = new boolean[n+1];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            while (a --> 0) {
                b = Integer.parseInt(st.nextToken());
                list.get(i).add(b);
                if (i == 1) hate[b] = true;
            }
        }

        checked = new boolean[n+1];
        blue = new ArrayList<>();
        white = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!checked[i]) {
                if (!hate[i]) {
                    dfs(i, true);
                } else {
                    dfs(i, false);
                }
            }
        }

        Collections.sort(blue);
        Collections.sort(white);
        StringBuilder sb = new StringBuilder();
        sb.append(blue.size()).append("\n");
        for (int i : blue) sb.append(i).append(" ");
        sb.append("\n");
        sb.append(white.size()).append("\n");
        for (int i : white) sb.append(i).append(" ");

        System.out.println(sb);
    }

    private static void dfs(int x, boolean flag) {
        checked[x] = true;
        if (flag) blue.add(x);
        else white.add(x);

        for (int next : list.get(x)) {
            if (!checked[next]) {
                if (flag) dfs(next, false);
                else dfs(next, true);
            }
        }
    }
}