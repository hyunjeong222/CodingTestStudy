import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static PriorityQueue<Pos> pq;
    static ArrayList<Pos>[] list;
    static public class Pos implements Comparable<Pos> {
        // 근무일수, 급한정도, 줄 번호
        int d; int h; int l;
        // 데카인지
        boolean deca;
        public Pos (int d, int h, int l, boolean deca) {
            this.d = d; this.h = h; this.l = l; this.deca = deca;
        }
        @Override
        public int compareTo(Pos o) {
            if (this.d == o.d) { // 근무일수가 같다면
                if (this.h == o.h) { // 급한정도가 같다면
                    return this.l - o.l; // 줄의 번호가 가장 낮은 사람
                }
                return o.h - this.h; // 급한정도가 높은 사람
            }
            return o.d - this.d; // 근무일수가 높은 사람
        }
    }
    static int n, m, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사원의 수
        m = Integer.parseInt(st.nextToken()); // 줄의 수
        k = Integer.parseInt(st.nextToken()); // 데카 앞에 서 있던 사원 수

        list = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            list[i] = new ArrayList<>();
        }

        int d, h;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken()); // 근무일수
            h = Integer.parseInt(st.nextToken()); // 급한정도
            boolean deca = false;
            if (k == i) deca = true; // k+1번째 데카
            list[i%m].add(new Pos(d, h, i%m, deca));
        }

        pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            if (!list[i].isEmpty()) {
                pq.offer(list[i].remove(0));
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.deca) { // 데카라면
                System.out.println(ans);
                System.exit(0);
            }

            if (!list[now.l].isEmpty()) {
                pq.offer(list[now.l].remove(0));
            }
            ans++;
        }
    }
}