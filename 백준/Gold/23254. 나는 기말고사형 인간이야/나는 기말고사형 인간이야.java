import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Compare implements Comparable<Compare> {
        int idx; int growthScore;
        public Compare(int idx, int growthScore) {
            this.idx = idx; this.growthScore = growthScore;
        }
        @Override
        public int compareTo(Compare o) {
            return o.growthScore - this.growthScore;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;
        int n = Integer.parseInt(st.nextToken()) * 24;
        int m = Integer.parseInt(st.nextToken());

        int[] score = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Compare> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            pq.offer(new Compare(i, Integer.parseInt(st.nextToken())));
        }

        while (n > 0 && !pq.isEmpty()) {
            Compare now = pq.poll();

            int time = (100-score[now.idx])/now.growthScore; // 성장률 잠수만으로 최고점을 얻는데 필요한 시간
            int restTime = Math.min(n, time); // 사용할 수 있는 시간

            n -= restTime;
            score[now.idx] += restTime*now.growthScore;
            if (score[now.idx] != 100) {
                pq.offer(new Compare(now.idx, 100-score[now.idx]));
            }
        }

        int ans = 0;
        for (int i =0; i < m; i++) {
            ans += score[i];
        }

        System.out.println(ans);
    }
}