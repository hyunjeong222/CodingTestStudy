import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int q = Integer.parseInt(br.readLine());
        HashMap<String, PriorityQueue<Long>> map = new HashMap<>();
        StringTokenizer st;
        long ans = 0;
        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int count = Integer.parseInt(st.nextToken());
            if (num == 1) {
                for (int i = 0; i < count; i++) {
                    if (!map.containsKey(name)) {
                        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
                        pq.offer(Long.parseLong(st.nextToken()));
                        map.put(name, pq);
                    } else {
                        map.get(name).offer(Long.parseLong(st.nextToken()));
                    }
                }
            } else {
                if (!map.containsKey(name)) continue;

                while (!map.get(name).isEmpty() && count > 0) {
                    ans += map.get(name).poll();
                    count--;
                }
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}