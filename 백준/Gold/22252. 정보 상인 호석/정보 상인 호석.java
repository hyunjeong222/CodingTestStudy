import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int q = Integer.parseInt(br.readLine()); // 쿼리의 개수
        HashMap<String, PriorityQueue<Long>> map = new HashMap<>();
        long ans = 0;
        while (q --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 쿼리의 번호
            String name = st.nextToken(); // 고릴라 이름
            int count = Integer.parseInt(st.nextToken()); // 정보의 개수
            if (num == 1) {
                for (int i = 0; i < count; i++) {
                    if (!map.containsKey(name)) {
                        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
                        pq.offer(Long.parseLong(st.nextToken())); // 정보
                        map.put(name, pq);
                    } else {
                        map.get(name).offer(Long.parseLong(st.nextToken()));
                    }
                }
            } else {
                if (map.get(name) == null) continue;
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