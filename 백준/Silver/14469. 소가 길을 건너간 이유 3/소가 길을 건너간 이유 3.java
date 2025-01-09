import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static public class Time implements Comparable<Time> {
        int getIn; int during;
        public Time (int getIn, int during) {
            this.getIn = getIn; this.during = during;
        }
        @Override
        public int compareTo(Time o) {
            if (this.getIn == o.getIn) return this.during - o.during;
            return this.getIn - o.getIn;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Time> pq = new PriorityQueue<>();
        int getIn, during;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            getIn = Integer.parseInt(st.nextToken());
            during = Integer.parseInt(st.nextToken());
            pq.offer(new Time(getIn, during));
        }
        
        int ans = 0;
        while (!pq.isEmpty()) {
            Time now = pq.poll();
            if (now.getIn > ans) ans = now.getIn + now.during;
            else ans += now.during;
        }

        System.out.println(ans);
    }
}