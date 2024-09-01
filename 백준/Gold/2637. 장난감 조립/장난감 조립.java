import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Pos>> list;
    static int[] nomal;
    static int[] indegreeY;
    static public class Pos {
        int num; int cnt;
        public Pos (int num, int cnt) {
            this.num = num; this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        nomal = new int[n+1]; // 기본 부품 찾기
        indegreeY = new int[n+1]; // 위상정렬
        StringTokenizer st;
        int x, y, k; // x를 만드는데 y 부품이 k개 필요
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            list.get(x).add(new Pos(y, k));
            nomal[x]++;
            indegreeY[y]++;
        }

        StringBuilder sb = new StringBuilder();
        int[] ans = topologySort();
        for (int i = 1; i <= n; i++) {
            if (nomal[i] == 0) sb.append(i).append(" ").append(ans[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static int[] topologySort() {
        Queue<Pos> que = new LinkedList<>();
        // n은 완제품
        que.offer(new Pos(n, 1));
        int[] counter = new int[n+1]; // 기본 부품은 다른 부품들로 만들 수 없는 부품
        counter[n] = 1;

        while (!que.isEmpty()) {
            Pos now = que.poll();
            int num = now.num;

            for (Pos next : list.get(num)) {
                counter[next.num] += counter[num] * next.cnt;
                indegreeY[next.num]--;
                if (indegreeY[next.num] == 0) que.offer(new Pos(next.num, counter[next.num]));
            }
        }
        return counter;
    }
}