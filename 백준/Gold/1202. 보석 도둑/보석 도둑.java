import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Pos {
        // 무게, 가격
        int cost; int value;
        public Pos (int cost, int value) {
            this.cost = cost; this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // n개의 보석
        int k = Integer.parseInt(st.nextToken()); // 가방의 개수

        ArrayList<Pos> list = new ArrayList<>();
        int m, v;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            list.add(new Pos(m, v));
        }
        Collections.sort(list, (o1, o2) -> o1.cost - o2.cost); // 무게순
        // System.out.println(list.get(1).cost + " " + list.get(1).value);

        int[] weight = new int[k];
        for (int i = 0; i < k; i++) {
            weight[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(weight);

        int idx = 0;
        long ans = 0;
        PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2) -> o2.value - o1.value); // 가격순
        for (int i = 0; i < k; i++) {
            while (idx < n && list.get(idx).cost <= weight[i]) {
                Pos now = list.get(idx++);
                pq.offer(new Pos(now.cost, now.value));
            }
            if (!pq.isEmpty()) ans += pq.poll().value;
        }

        System.out.println(ans);

    }
}