import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static public class Time implements Comparable<Time> {
        int start; int end;
        public Time (int start, int end) {
            this.start = start; this.end = end;
        }
        @Override
        public int compareTo(Time o) {
            return this.start - o.start;
        }
    }
    static public class Pos implements Comparable<Pos> {
        int end; int idx;
        public Pos (int end, int idx) {
            this.end = end; this.idx = idx;
        }
        @Override
        public int compareTo(Pos o) {
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Time> list = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);

        int cnt = 0; // 컴퓨터의 최소 개수
        int[] room = new int[100001]; // 각 자리를 사용한 사람의 수

        PriorityQueue<Pos> pq = new PriorityQueue<>(); // 활성화 된 방
        PriorityQueue<Integer> idx = new PriorityQueue<>(); // 비활성화 된 방

        for (Time now : list) {
            while (!pq.isEmpty() && pq.peek().end < now.start) {
                idx.offer(pq.poll().idx);
            }

            if (!idx.isEmpty()) {
                Integer i = idx.poll();
                room[i] += 1;
                pq.offer(new Pos(now.end, i));
            } else {
                cnt++;
                room[cnt] += 1;
                pq.offer(new Pos(now.end, cnt));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n");
        for (int i = 1; i <= cnt; i++) {
            sb.append(room[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}