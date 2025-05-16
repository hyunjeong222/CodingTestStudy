import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Preference implements Comparable<Preference> {
        int aCost, bCost;
        public Preference(int aCost, int bCost) {
            this.aCost = aCost; this.bCost = bCost;
        }
        @Override
        public int compareTo (Preference o) {
            // A, B 메뉴 차이가 큰 순서로 정렬
            return Math.abs(o.aCost-o.bCost) - Math.abs(this.aCost-this.bCost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 날짜
        int x = Integer.parseInt(st.nextToken()); // 사용할 수 있는 금액

        PriorityQueue<Preference> pq = new PriorityQueue<>();
        int aCost, bCost;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            aCost = Integer.parseInt(st.nextToken());
            bCost = Integer.parseInt(st.nextToken());
            pq.offer(new Preference(aCost, bCost));
        }

        int ans  = 0;
        while (!pq.isEmpty()) {
            Preference now = pq.poll();

            // 나머지 날 밥 못 먹는 경우 방지
            // 천원짜리 메뉴에 대한 만족도가 더 높다면
            // 돈이 남더라도 천원짜리 메뉴 선택 가능
            if (x-5000 >= pq.size() * 1000 && now.aCost > now.bCost) {
                x -= 5000;
                ans += now.aCost;;
            } else {
                x -= 1000;
                ans += now.bCost;;
            }
        }

        System.out.println(ans);

        br.close();
    }
}