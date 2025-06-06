import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Satisfaction implements Comparable<Satisfaction> {
        int num;
        public Satisfaction(int num) {
            this.num = num;
        }
        @Override
        public int compareTo(Satisfaction o) {
            return o.num-this.num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 할 일의 개수
        int m = Integer.parseInt(st.nextToken()); // 감소하는 중요도
        int k = Integer.parseInt(st.nextToken()); // 중요도의 최댓값

        PriorityQueue<Satisfaction> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(new Satisfaction(Integer.parseInt(br.readLine())));
        }

        StringBuilder sb = new StringBuilder();
        int prevSatisfaction = 0;
        int day = 0;
        while (!pq.isEmpty()) {
            day++;
            int nowSatisfaction = pq.poll().num;
            int todaySatisfaction = (prevSatisfaction/2)+nowSatisfaction;
            sb.append(todaySatisfaction).append("\n");
            prevSatisfaction = todaySatisfaction;

            nowSatisfaction -= m;
            if (nowSatisfaction > k) {
                pq.offer(new Satisfaction(nowSatisfaction));
            }
        }

        System.out.println(day);
        System.out.println(sb.toString());

        br.close();
    }
}