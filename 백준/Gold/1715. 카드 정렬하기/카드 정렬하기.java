import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (n --> 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int first, second;
        int sum = 0;
        while (pq.size() > 1) {
            first = pq.poll();
            second = pq.poll();
            sum += first + second;
            pq.offer(first + second);
        }
        System.out.println(sum);
    }
}