import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, s;
    static int[] arr;
    static boolean[] checked;
    static int[] dir = {1, -1};
    static int ans = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        checked = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        s = Integer.parseInt(br.readLine())-1;

        bfs(s);

        System.out.println(ans);
    }

    private static void bfs(int now) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(now);
        checked[now] = true;

        while (!que.isEmpty()) {
            now = que.poll();
            int jump = arr[now];

            for (int d : dir) {
                int nd = (d*jump)+now;

                if (!rangeCheck(nd) && !checked[nd]) {
                    checked[nd] = true;
                    ans++;
                    que.offer(nd);
                }
            }
        }

    }

    private static boolean rangeCheck(int d) {
        return d < 0 || d >= n;
    }
}