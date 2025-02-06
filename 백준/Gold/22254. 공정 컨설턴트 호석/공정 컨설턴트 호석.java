import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, x;
    static int[] ProcessTime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 선물 주문 개수
        x = Integer.parseInt(st.nextToken()); // 제작 완료까지 남은 시간

        ProcessTime = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ProcessTime[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = start + (end-start) / 2; // 공정 라인 개수

            if (isPossible(mid)) { // 현재 공정 라인 개수로 시간에 맞춰 제작을 끝낼 수 있다면
                end = mid-1;
            } else start = mid+1;
        }

        System.out.println(start);
    }

    private static boolean isPossible(int mid) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < mid; i++) { // 공정 라인 개수만큼 0으로 채움
            pq.offer(0);
        }
        for (int i = 0; i < n; i++) {
            int nowTime = pq.poll();

            // 시간 안에 끝내지 못함
            if (nowTime+ProcessTime[i] > x) return false;
            pq.offer(nowTime+ProcessTime[i]);
        }
        return true;
    }
}