import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine())-1;
        int dasom = Integer.parseInt(br.readLine()); // 기호 1번 다솜
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        while (n --> 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int ans = 0;
        while (!pq.isEmpty() && dasom <= pq.peek()) {
            dasom++;
            pq.offer(pq.poll()-1);
            ans++;
        }

        System.out.println(ans);
    }
}