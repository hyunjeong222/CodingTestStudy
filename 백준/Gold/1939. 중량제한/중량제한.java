import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos {
        int to; int w;

        public Pos(int to, int w) {
            this.to = to; this.w = w;
        }
    }
    static int ans;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점
        int m = Integer.parseInt(st.nextToken()); // 간선

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int a, b, c;
        int left = 0;
        int right = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Pos(b, c));
            list.get(b).add(new Pos(a, c));
            right = Math.max(right, c); // 최대 중량
        }

        st = new StringTokenizer(br.readLine());
        // 공장이 위치한 섬의 번호
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        while (left <= right) {
            int mid = (left+right)/2;

            ans = 0;
            checked = new boolean[n+1];
            dfs(from, to, mid); // 해당 중량(mid)으로 a -> b 이동 가능한지 탐색

            if (ans != 0) { // 이동 가능
                left = mid+1; // 중량 늘리기
            } else {
                right = mid-1;
            }
        }

        System.out.println(right);

        br.close();
    }

    private static void dfs(int now, int target, int limit) {
        if (now == target) { // 현재 위치한 도시와 도착한 도시가 같다면
            ans = now;
            return;
        }

        checked[now] = true;

        for (Pos next : list.get(now)) {
            if (!checked[next.to] && limit <= next.w) {
                dfs(next.to, target, limit);
            }
        }
    }
}