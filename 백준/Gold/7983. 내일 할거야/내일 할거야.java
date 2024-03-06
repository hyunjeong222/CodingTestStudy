import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static public class Pos implements Comparable<Pos> {
        int needTime; int end;
        public Pos(int needTime, int end) {
            this.needTime = needTime; this.end = end;
        }
        @Override
        public int compareTo(Pos o) {
            return o.end - this.end; // 끝나는 시간 내림차순
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int needTime, end;
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            needTime = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            pq.offer(new Pos(needTime, end));
        }
        int deadLine = pq.peek().end;
        while (!pq.isEmpty() && deadLine >= 0) {
            Pos now = pq.poll();
            if (deadLine > now.end) {
                deadLine = now.end - now.needTime;
            } else deadLine -= now.needTime;
        }
        System.out.println(deadLine);
    }
}