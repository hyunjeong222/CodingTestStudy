import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> list;
    static int[] checked;
    static int[] white, black;
    static long ans = 0, cost = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        int u, v;
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        white = new int[n]; black = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            white[i] = Integer.parseInt(st.nextToken());
            black[i] = Integer.parseInt(st.nextToken());
        }

        checked = new int[n];
        checked[0] = 1; // 0번 흰색 설정
        cost = white[0]; // 0번 흰색 비용 설정
        dfs(0, 1);
        ans = cost;

        checked = new int[n];
        checked[0] = -1; // 0번 흰색 설정
        cost = black[0]; // 0번 흰색 비용 설정
        dfs(0, -1);

        System.out.println(Math.min(ans, cost));

        // System.out.println(Arrays.toString(checked));
    }

    private static void dfs(int i, int color) {
        for (int next : list.get(i)) {
            if (checked[next] == 0) {
                checked[next] = color*-1;
                cost += (color == 1) ? black[next] : white[next];
                dfs(next, color*-1);
            } else if (checked[next] == color) {
                return;
            }
        }
    }
}