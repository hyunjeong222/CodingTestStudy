import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Pos implements Comparable<Pos> {
        int idx; long num;
        public Pos(int idx, long num) {
            this.idx = idx; this.num = num;
        }
        @Override
        public int compareTo(Pos o) {
            if (this.num == o.num) return this.idx - o.idx;
            return Long.compare(this.num, o.num);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Pos> pq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine()); // 수열의 크기
        StringTokenizer st = new StringTokenizer(br.readLine()); // 최신값 저장용 배열
        long[] arr = new long[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            // 인덱스 넘버, 숫자
            pq.offer(new Pos(i, arr[i]));
        }

        int m = Integer.parseInt(br.readLine()); // 쿼리의 개수
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int i = Integer.parseInt(st.nextToken());
                long v = Long.parseLong(st.nextToken());

                arr[i] = v; // 최신값 저장
                pq.add(new Pos(i, v));
            } else { // 1 : 크기가 가장 작은 값의 인덱스를 출력
                while (!pq.isEmpty() && pq.peek().num != arr[pq.peek().idx]) {
                    pq.poll();
                }

                sb.append(pq.peek().idx).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}