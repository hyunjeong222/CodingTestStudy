import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

            int m = Integer.parseInt(br.readLine());

            sb.append((m/2)+1).append("\n"); // 중앙 값 개수

            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if (i % 10 == 0) { // 한줄에 10개씩
                    st = new StringTokenizer(br.readLine());
                }
                int num = Integer.parseInt(st.nextToken());
                if (minHeap.size() == maxHeap.size()) {
                    maxHeap.offer(num);
                } else minHeap.offer(num);

                if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                    if (maxHeap.peek() > minHeap.peek()) {
                        int tmp = minHeap.poll();
                        minHeap.offer(maxHeap.poll());
                        maxHeap.offer(tmp);
                    }
                }

                if (i % 2 == 0) {
                    if (cnt == 9 || i == m-1) {
                        sb.append(maxHeap.peek()).append("\n");
                        cnt = 0;
                    } else {
                        sb.append(maxHeap.peek()).append(" ");
                    }
                    cnt++;
                }
            }
        }

        System.out.println(sb);
    }
}