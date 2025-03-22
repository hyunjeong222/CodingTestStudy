import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Pos {
        int idx; int num;
        public Pos (int idx, int num) {
            this.idx = idx; this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 풍선의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] balloon = new int[n]; // 터트릴 풍선의 위치
        for (int i = 0; i < n; i++) {
            balloon[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Pos> dq = new ArrayDeque<>();
        // 우선, 제일 처음에는 1번 풍선을 터뜨림
        StringBuilder sb = new StringBuilder();
        sb.append(1).append(" ");
        int move = balloon[0];
        for (int i = 1; i < n; i++) {
            dq.offer(new Pos(i+1, balloon[i]));
        }
        while (!dq.isEmpty()) { // 모든 풍선이 터질 때까지
            if (move > 0) { // 양수
                // 앞에 있는 요소 모두 뒤로 보내기
                for (int i = 1; i < move; i++) {
                    dq.offer(dq.poll());
                }
                Pos next = dq.poll();
                move = next.num;
                sb.append(next.idx).append(" ");
            } else { // 음수
                for (int i = 1; i < -move; i++) {
                    dq.addFirst(dq.pollLast());
                }
                Pos next = dq.pollLast();
                move = next.num;
                sb.append(next.idx).append(" ");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}