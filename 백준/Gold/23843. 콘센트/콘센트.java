import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 전자기기 개수
        int m = Integer.parseInt(st.nextToken()); // 콘센트 개수

        PriorityQueue<Integer> outlet = new PriorityQueue<>(); // 사용할 수 있는 콘센트
        PriorityQueue<Integer> charging = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            charging.offer(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        while (!charging.isEmpty()) {
            int tmp = 0;
            if (outlet.size() < m) { // 콘센트 사용 가능
                tmp = charging.poll();
            } else {
                // 충전 빨리 끝나는 것 뽑기
                tmp = outlet.poll() + charging.poll();
            }
            outlet.offer(tmp);
            ans = Math.max(ans, tmp);
        }

        System.out.println(ans);
    }
}