import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Pos implements Comparable<Pos> {
        int start; int end;
        public Pos (int start, int end) {
            this.start = start; this.end = end;
        }
        @Override
        public int compareTo(Pos o) {
            if (this.end != o.end) return this.end - o.end;
            return this.start-o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken()); // 닭의 수
        int n = Integer.parseInt(st.nextToken()); // 소의 수

        int[] chickens = new int[c];
        for (int i = 0; i < c; i++) {
            chickens[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(chickens);

        PriorityQueue<Pos> cow = new PriorityQueue<>();
        int s, e;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            cow.offer(new Pos(s, e));
        }

        // 몇 마리의 소가 닭에게 도움을 받을 수 있는지
        int ans = 0;;
        while (!cow.isEmpty()) {
            boolean flag = false; // 닭에게 도움을 받을 수 있는지
            for (int i = 0; i < c; i++) {
                // 소가 닭에게 도움을 받을 수 있음
                if (cow.peek().start <= chickens[i] && chickens[i] <= cow.peek().end && chickens[i] > 0) {
                    // 소는 닭 한마리에게만 도움받을 수 있고
                    cow.poll();
                    // 닭은 한번만 소를 도와줄 수 있음
                    chickens[i] = -1;
                    ans++;
                    flag = true;
                    break;
                }
            }

            // 도움받을 수 없다면 지우기
            if (!flag) cow.poll();
        }

        System.out.println(ans);
    }
}