import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Compare implements Comparable<Compare> {
        int date; int score;
        public Compare(int date, int score) {
            this.date = date; this.score = score;
        }
        @Override
        public int compareTo(Compare o) {
            return this.date - o.date;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Compare> list = new ArrayList<>();
        StringTokenizer st;
        int d, w;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            list.add(new Compare(d, w));
        }
        Collections.sort(list);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Compare now : list) {
            pq.offer(now.score);

            if (pq.size() > now.date) {
                pq.poll();
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }

        System.out.println(ans);
    }
}