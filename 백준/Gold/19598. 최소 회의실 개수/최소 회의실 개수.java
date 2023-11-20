import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static public class pos implements Comparable<pos>{
        int start; int end;
        public pos(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(pos o) {
            if (this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<pos> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.offer(new pos(s, e));
        }

        PriorityQueue<Integer> room = new PriorityQueue<>();
        room.offer(pq.poll().end);

        while (!pq.isEmpty()) {
            pos time = pq.poll();
            if (room.peek() <= time.start) {
                room.poll();
            }
            room.offer(time.end);
        }
        bw.append(room.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}