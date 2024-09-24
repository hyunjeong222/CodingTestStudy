import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Pos implements Comparable<Pos> {
        int deadLine; int cnt;
        public Pos (int deadLine, int cnt) {
            this.deadLine = deadLine; this.cnt = cnt;
        }
        @Override
        public int compareTo(Pos o) {
            if (this.deadLine == o.deadLine) {
                return o.cnt - this.cnt;
            }
            return this.deadLine - o.deadLine;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 숙제의 개수
        int deadLine, cnt;
        StringTokenizer st;
        ArrayList<Pos> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 푼 문제 저장
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            deadLine = Integer.parseInt(st.nextToken());
            cnt = Integer.parseInt(st.nextToken());
            list.add(new Pos(deadLine, cnt));
        }
        Collections.sort(list);

        for (Pos now : list) {
            // 데드라인 : 풀고자 하는 문제를 포함해서 이전까지 풀었던 문제의 개수가
            // 해당 문제의 데드라인보다 같거나 작아야함
            if (pq.size() < now.deadLine) pq.offer(now.cnt);
            else if (pq.size() == now.deadLine) {
                if (pq.peek() < now.cnt) {
                    pq.poll();
                    pq.offer(now.cnt);
                }
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        System.out.println(ans);
    }
}