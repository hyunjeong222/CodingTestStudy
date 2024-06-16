import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 트럭의 개수
        int w = Integer.parseInt(st.nextToken()); // 다리의 길이
        int l = Integer.parseInt(st.nextToken()); // 최대하중

        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < w; i++) { // 다리의 길이만큼 큐 채워주기
            que.offer(0);
        }

        st = new StringTokenizer(br.readLine());
        int truck = Integer.parseInt(st.nextToken()); // 첫번째 트럭
        int sum = 0;
        int time = 0;
        while (true) {
            time++; // 트럭이 다리에 올라오자 마자 시간 증가

            sum -= que.poll(); // 다리에서 트럭 제거 
            if (sum + truck <= l) {
                sum += truck;
                que.offer(truck);
                if (--n == 0) break;
                truck = Integer.parseInt(st.nextToken());
            } else {
                que.offer(0);
            }
        }
        System.out.println(time+que.size());
    }
}