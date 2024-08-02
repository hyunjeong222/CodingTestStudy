import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        LinkedList<Integer> deque = new LinkedList<>(); // 덱
        for (int i = 1; i <= n; i++) {
            deque.offer(i);
        }
        int[] peek = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            peek[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            // 찾아야하는 숫자
            int targetIndex = deque.indexOf(peek[i]);
            int middleIndex;
            if (deque.size() % 2 == 0) {
                middleIndex = deque.size() / 2 -1;
            } else {
                middleIndex = deque.size() / 2;
            }

            if (targetIndex <= middleIndex) {
                for (int j = 0; j < targetIndex; j++) {
                    deque.offerLast(deque.pollFirst());
                    ans++;
                }
            } else {
                for (int j = 0; j < deque.size() - targetIndex; j++) {
                    deque.offerFirst(deque.pollLast());
                    ans++;
                }
            }
            deque.pollFirst();
        }

        System.out.println(ans);
    }
}