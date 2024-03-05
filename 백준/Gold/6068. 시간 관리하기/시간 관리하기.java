import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static public class Pos implements Comparable<Pos>{
        int time; int end;
        public Pos(int time, int end) {
            this.time = time; this.end = end;
        }
        @Override
        public int compareTo(Pos o) {
            return o.end - this.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 작업의 개수
        StringTokenizer st;
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int time, end;
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            time = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            pq.offer(new Pos(time, end));
        }
        int deadLine = pq.peek().end;
        while (!pq.isEmpty() && deadLine >= 0) {
            Pos now = pq.poll();
            if (now.end < deadLine) {
                deadLine = now.end - now.time;
            } else {
                deadLine -= now.time;
            }
        }
        if (deadLine > 0) System.out.println(deadLine);
        else System.out.println(-1);
    }
}