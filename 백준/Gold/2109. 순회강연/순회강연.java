import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int pay; int day;
        public Pos (int pay, int day) {
            this.pay = pay; this.day = day;
        }
        @Override
        public int compareTo(Pos o) {
            if (this.pay == o.pay) return this.day - o.day;
            return o.pay - this.pay;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        StringTokenizer st;
        int p, d;
        while(n --> 0) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            pq.offer(new Pos(p, d));
        }
        boolean[] checked = new boolean[10001];
        int ans = 0;
        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            for (int i = now.day-1; i >= 0; i--) {
                if (!checked[i]) {
                    checked[i] = true;
                    ans += now.pay;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}