import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 주차 공간의 수
        int m = Integer.parseInt(st.nextToken()); // 차량의 수

        int[] parkingFee = new int[n+1]; // 주차 공간 s의 단위 무게당 요금
        for (int i = 1; i <= n; i++) {
            parkingFee[i] = Integer.parseInt(br.readLine());
        }

        int[] carWeight = new int[m+1]; // 차량 k의 무게
        for (int i = 1; i <= m; i++) {
            carWeight[i] = Integer.parseInt(br.readLine());
        }

        // 빈 주차 공간이 여러 곳 있다면 그 중 번호가 가장 작은 주차 공간에 주차
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Queue<Integer> que = new LinkedList<>(); // 대기하는 차량

        int[] parkingNum = new int[m+1]; // 각 차들의 주차 공간 번호 저장

        for (int i = 1; i <= n; i++) {
            pq.offer(i);
        }

        int ans = 0;
        for (int i = 0; i < 2*m; i++) {
            int car = Integer.parseInt(br.readLine());

            if (car > 0) { // 차량 i가 주차장에 들어오는 것
                if (pq.isEmpty()) {
                    que.offer(car);
                } else {
                    parkingNum[car] = pq.poll();
                }
            } else { // 차량 i가 주차장에서 나가는 것
                car = Math.abs(car);
                ans += carWeight[car]*parkingFee[parkingNum[car]];
                if (!que.isEmpty()) {
                    parkingNum[que.poll()] = parkingNum[car];
                } else {
                    pq.offer(parkingNum[car]);
                }
            }
        }

        System.out.println(ans);

        br.close();
    }
}