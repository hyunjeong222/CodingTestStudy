import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 선물 상자의 수
        int m = Integer.parseInt(st.nextToken()); // 아이들의 수

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> box = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            // 각 선물 상자에 들어있는 선물의 개수
            box.offer(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int now = box.poll();
            int want = Integer.parseInt(st.nextToken());

            if (now < want) {
                System.out.println(0);
                return;
            }

            box.offer(now-want);
        }

        System.out.println(1);

        br.close();
    }
}