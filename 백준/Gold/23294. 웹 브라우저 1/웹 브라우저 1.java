import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int n, q, c;
    static int[] cache;
    static int cacheSize, cur;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cacheSize = 0;
        cur = 0;

        cache = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cache[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> back = new LinkedList<>();
        Deque<Integer> front = new LinkedList<>();
        Deque<Integer> tmp;

        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            switch (s) {
                case "B" :
                    if (!back.isEmpty()) {
                        front.offerFirst(cur);
                        cur = back.pollFirst();
                    }
                    break;
                case "F" :
                    if (!front.isEmpty()) {
                        back.offerFirst(cur);
                        cur = front.pollFirst();
                    }
                    break;
                case "A" :
                    int page = Integer.parseInt(st.nextToken());
                    while (!front.isEmpty()) {
                        cacheSize -= cache[front.pollFirst()];
                    }

                    if (cur != 0) back.offerFirst(cur);
                    cur = page;
                    cacheSize += cache[cur];

                    while (cacheSize > c) {
                        cacheSize -= cache[back.pollLast()];
                    }
                    break;
                case "C" : // 압축
                    if (back.isEmpty()) continue;
                    tmp = new LinkedList<>();
                    int prev = back.pollFirst();
                    tmp.offer(prev);

                    for (int next : back) {
                        if (next != prev) {
                            tmp.add(next);
                            prev = next;
                        } else {
                            cacheSize -= cache[next];
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
        if (q.isEmpty()) sb.append(-1);
        else {
            while (!q.isEmpty()) sb.append(q.pollFirst()).append(" ");
        }
        sb.append("\n");
    }
}