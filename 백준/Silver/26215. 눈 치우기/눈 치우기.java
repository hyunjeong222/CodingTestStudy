import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            int now = pq.poll();

            if (pq.isEmpty()) {
                ans += now; // 한 집을 선택해서 그 집 앞의 눈을 1만큼 치움
                break;
            }
            
            if (ans > 1440) break;

            int next = pq.poll();
            ans += next;
            pq.offer(now-next);
        }

        System.out.println(ans > 1440 ? -1 : ans);

        br.close();

    }
}