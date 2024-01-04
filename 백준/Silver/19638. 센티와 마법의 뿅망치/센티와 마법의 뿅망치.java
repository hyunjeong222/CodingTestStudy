import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 인구수
        int h = Integer.parseInt(st.nextToken()); // 센티의 키
        int t = Integer.parseInt(st.nextToken()); // 뿅망치 횟수
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int cnt = 0;
        while (t --> 0) {
            if (pq.peek() < h || pq.peek() == 1) break;
            pq.offer(pq.poll()/2);
            cnt++;
        }
        if (pq.peek() < h) sb.append("YES").append("\n").append(cnt);
        else sb.append("NO").append("\n").append(pq.peek());
        System.out.println(sb);
    }
}