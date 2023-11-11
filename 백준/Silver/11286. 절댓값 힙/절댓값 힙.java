import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> {
            int abs1 = Math.abs(x);
            int abs2 = Math.abs(y);

            if (abs1 > abs2) {
                return abs1 - abs2;
            } else if (abs1 == abs2) {
                return x - y;
            }
            return -1;
        });
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(pq.poll()).append("\n");
                }
            } else {
                pq.offer(num);
            }
        }
        System.out.println(sb.toString());
    }
}