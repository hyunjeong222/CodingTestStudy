import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    static Queue<Integer> que;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        checked = new boolean[n+1];
        StringTokenizer st;
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        ans = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
        }
        if (ans[0] != 1) {
            System.out.println(0);
            System.exit(0);
        }
        if (bfs(1)) System.out.println(1);
        else System.out.println(0);
    }

    private static boolean bfs(int x) {
        que = new LinkedList<>();
        que.offer(x);
        checked[x] = true;
        HashSet<Integer> set = new HashSet<>();
        int idx = 1;
        while (!que.isEmpty()) {
            set.clear();
            int now = que.poll();
            for (int y : list.get(now)) {
                if (!checked[y]) {
                    set.add(y);
                    checked[y] = true;
                }
            }
            int size = set.size();
            for (int i = idx; i < idx + size; i++) {
                if (set.contains(ans[i])) que.offer(ans[i]);
                else return false;
            }
            idx += size;
        }
        return true;
    }
}