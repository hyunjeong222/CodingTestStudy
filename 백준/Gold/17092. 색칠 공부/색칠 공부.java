import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int h, w, n;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static Set<Pair> board = new HashSet<>();
    static Set<Pair> check = new HashSet<>();
    static long[] cntInfo = new long[10];
    static long makeCnt = 0;
    static public class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x; this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Pair)) return false;
            Pair p = (Pair) obj;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 크기가 H×W인 모눈종이
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        // 검정 칸의 개수
        n = Integer.parseInt(st.nextToken());

        int x, y;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            board.add(new Pair(x, y));
        }

        for (Pair cell : board) {
            for (int i = 0; i < 9; i++) {
                checkCenter(cell, i);
            }
        }

        cntInfo[0] = (long)(h-2)*(w-2)-makeCnt;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(cntInfo[i]).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void checkCenter(Pair now, int idx) {
        int cx = now.x + dx[idx];
        int cy = now.y + dy[idx];
        Pair center = new Pair(cx, cy);

        if (check.contains(center)) return;

        int cnt = 0;
        for (int i = 0; i < 9; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx < 1 || nx > h || ny < 1 || ny > w) return;

            if (board.contains(new Pair(nx, ny))) cnt++;
        }

        check.add(center);
        cntInfo[cnt]++;
        makeCnt++;
    }
}