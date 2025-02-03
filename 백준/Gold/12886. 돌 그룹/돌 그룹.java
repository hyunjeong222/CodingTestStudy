import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int a, b, c;
    static boolean[][] checked;
    static public class Pos {
        int a; int b; int c;
        public Pos(int a, int b, int c) {
            this.a = a; this.b = b; this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        checked = new boolean[1501][1501];

        System.out.println(bfs() ? 1 : 0);
    }

    private static boolean bfs() {
        if ((a+b+c) % 3 != 0) return false;

        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(a, b, c));
        checked[a][b] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();
            a = now.a;
            b = now.b;
            c = now.c;

            if (a == b && b == c) return true;

            if (a != b) { // 갯수가 다른 두 개 골라서 연산
                int na = a > b ? a-b : a+a;
                int nb = a > b ? b+b : b-a;

                if (!checked[na][nb]) {
                    que.offer(new Pos(na, nb, c));
                    checked[na][nb] = true;
                }
            }

            if (b != c) {
                int nb = b > c ? b-c : b+b;
                int nc = b > c ? c+c : c-b;

                if (!checked[nb][nc]) {
                    que.offer(new Pos(a, nb, nc));
                    checked[nb][nc] = true;
                }
            }

            if (a != c) {
                int na = a > c ? a-c : a+a;
                int nc = a > c ? c+c : c-a;

                if (!checked[na][nc]) {
                    que.offer(new Pos(na, b, nc));
                    checked[na][nc] = true;
                }
            }
        }

        return false;
    }
}