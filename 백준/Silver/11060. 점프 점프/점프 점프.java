import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] map;
    static boolean[] checked;
    static public class Pos {
        int idx; int cnt;
        public Pos (int idx, int cnt) {
            this.idx = idx; this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 미로는 1xn

        map = new int[n]; // 미로
        checked = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        // 미로의 가장 왼쪽에서 시작
        int ans = bfs(0);

        System.out.println(ans);
        br.close();
    }

    private static int bfs(int start) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(start, 0));
        checked[start] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();
            int nowIdx = now.idx;

            // 오른쪽 끝
            if (nowIdx == n-1) return now.cnt;

            for (int i = 1; i <= map[nowIdx]; i++) {
                int tmpIdx = nowIdx + i;

                if (rangeCheck(tmpIdx)) {
                    checked[tmpIdx] = true;
                    que.offer(new Pos(tmpIdx, now.cnt+1));
                }
            }

        }

        return -1; // 오른쪽 끝으로 갈 수 없음
    }

    private static boolean rangeCheck(int idx) {
        return idx < n && !checked[idx];
    }
}