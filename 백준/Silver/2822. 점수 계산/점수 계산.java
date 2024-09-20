import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static public class Pos implements Comparable<Pos> {
        int num; int score;
        public Pos(int num, int score) {
            this.num = num; this.score = score;
        }
        @Override
        public int compareTo(Pos o) {
            return o.score - this.score;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int score;
        for (int i = 1; i <= 8; i++) {
            score = Integer.parseInt(br.readLine());
            pq.offer(new Pos(i, score));
        }
        int[] result = new int[5];
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            Pos now = pq.poll();
            sum += now.score;
            result[i] = now.num;
        }
        Arrays.sort(result);

        sb.append(sum).append("\n");
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}