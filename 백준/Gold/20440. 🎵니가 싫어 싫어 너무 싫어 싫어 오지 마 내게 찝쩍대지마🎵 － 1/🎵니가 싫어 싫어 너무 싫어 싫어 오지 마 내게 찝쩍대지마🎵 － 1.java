import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Mosquito implements Comparable<Mosquito> {
        int start; int end;
        public Mosquito(int start, int end) {
            this.start = start; this.end = end;
        }
        @Override
        public int compareTo(Mosquito m) {
            return this.start - m.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 모기의 마릿수

        List<Mosquito> list = new ArrayList<>();

        StringTokenizer st;
        // 모기의 입장 시각, 퇴장 시각
        int sTime, eTime;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            sTime = Integer.parseInt(st.nextToken());
            eTime = Integer.parseInt(st.nextToken());

            list.add(new Mosquito(sTime, eTime));
        }

        // 모기가 있는 시각별 모기의 수를 누적해서 합해야 함
        Collections.sort(list);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int start = 0, end = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            int prev = -1;

            while (!pq.isEmpty() && list.get(i).start >= pq.peek()) {
                prev = pq.poll();
            }

            pq.offer(list.get(i).end);

            if (pq.size() > ans) {
                start = list.get(i).start;
                end = pq.peek();
                ans = pq.size();
            } else if (pq.size() == ans && prev == list.get(i).start) {
                end = pq.peek();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans).append("\n");
        sb.append(start).append(" ").append(end).append("\n");

        System.out.println(sb.toString());
    }
}