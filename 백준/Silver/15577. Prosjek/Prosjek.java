import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(Double.parseDouble(br.readLine()));
        }

        double ans = 0;
        while (pq.size() > 1) {
            double fir = pq.poll();
            double sec = pq.poll();

            ans = (fir+sec)/2.0;

            pq.offer(ans);
        }

        System.out.printf("%.6f\n", pq.poll());

        br.close();
    }
}