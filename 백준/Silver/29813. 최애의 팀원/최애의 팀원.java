import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Pair {
        String initial; int num;
        public Pair (String initial, int num) {
            this.initial = initial; this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Queue<Pair> que = new LinkedList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String initial = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            que.offer(new Pair(initial, num));
        }

        while (que.size() != 1) {
            Pair now = que.poll();

            while (now.num --> 1) que.offer(que.poll());
            que.poll();
        }

        System.out.println(que.poll().initial);

        br.close();
    }
}