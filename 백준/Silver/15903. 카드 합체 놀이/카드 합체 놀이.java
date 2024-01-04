import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static PriorityQueue<Long> pq;
    static long ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(st.nextToken());
            pq.offer(num);
        }
        while (m --> 0) {
            long num1 = pq.poll();
            long num2 = pq.poll();

            long sum = num1 + num2;

            pq.offer(sum);
            pq.offer(sum);
        }

        while (!pq.isEmpty()) {
            ans += pq.poll();
        }

        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}