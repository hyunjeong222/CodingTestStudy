import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static public class pos {
        int num;
        int idx;
        public pos(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        Queue<pos> que;
        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 문서의 개수
            int m = Integer.parseInt(st.nextToken()); // 궁금한 문서의 위치

            que = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                que.offer(new pos(Integer.parseInt(st.nextToken()), i));
            }

            int cnt = 0;
            while (!que.isEmpty()) {
                pos now = que.poll();
                boolean flag = true;

                for (pos next : que) {
                    if (next.num > now.num) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    cnt++;
                    if (now.idx == m) break;
                } else que.offer(new pos(now.num, now.idx));
            }
            bw.append(cnt + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}