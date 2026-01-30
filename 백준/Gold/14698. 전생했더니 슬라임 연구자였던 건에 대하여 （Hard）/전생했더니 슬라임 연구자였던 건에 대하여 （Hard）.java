import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq;
        StringTokenizer st;
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                long energy = Long.parseLong(st.nextToken());
                pq.offer(energy);
            }

            
            long ans = 1;
            while (pq.size() > 1) {
                long fir = pq.poll();
                long sec = pq.poll();
                long tmp = fir * sec;

                ans = ((ans % MOD) * (tmp % MOD)) % MOD;

                pq.offer(tmp);
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}