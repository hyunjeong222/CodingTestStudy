import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 웹페이지의 종류의 수
        q = Integer.parseInt(st.nextToken()); // 사용자가 수행하는 작업의 개수

        Deque<Integer> back = new LinkedList<>();
        Deque<Integer> front = new LinkedList<>();
        Deque<Integer> tmp;

        int cur = 0; // 현재 페이지

        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            switch (s) {
                case "B" :
                    if (back.isEmpty()) break;
                    front.offerFirst(cur);
                    cur = back.pollFirst();
                    break;
                case "F" :
                    if (!front.isEmpty()) {
                        back.offerFirst(cur);
                        cur = front.pollFirst();
                    }
                    break;
                case "A" :
                    int p = Integer.parseInt(st.nextToken());
                    front.clear();
                    if (cur != 0) {
                        back.offerFirst(cur);
                    }
                    cur = p;
                    break;
                case "C" :
                    if (back.isEmpty()) continue;
                    tmp = new LinkedList<>();
                    int prev = back.pollFirst();
                    tmp.add(prev);

                    for (int next : back) {
                        if (next != prev) {
                            tmp.add(next);
                            prev = next;
                        }
                    }
                    back = tmp;
                    break;
            }
        }

        sb.append(cur).append("\n");
        getPage(sb, back);
        getPage(sb, front);

        bw.append(sb);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void getPage(StringBuilder sb, Deque<Integer> q) {
        if(q.isEmpty()) sb.append(-1);
        else {
            while(!q.isEmpty()) sb.append(q.pollFirst()).append(" ");
        }
        sb.append("\n");
    }
}