import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            que.offer(i);
        }
        while (true) {
            if (que.size() == 1) break;

            que.remove();
            que.offer(que.poll());
        }
        bw.append(que.peek() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}