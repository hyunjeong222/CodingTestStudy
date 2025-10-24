import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 체인의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] chain = new int[n];
        for (int i = 0; i < n; i++) {
            chain[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(chain);

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            deque.offer(chain[i]);
        }

        int ans = 0;
        while (deque.size() > 1) {
            ans++; // 고리 개수
            
            // 가장 긴 체인 두 개
            int chain2 = deque.pollLast();
            int chain3 = deque.pollLast();
            deque.offerLast(chain2+chain3);

            // 가장 짧은 체인을 분해해 고리로 만들어
            // 합치기
            if (deque.size() >= 2) {
                int chain1 = deque.pollFirst();
                if (chain1 > 1) {
                    deque.offerFirst(chain1-1);
                }
            }
        }

        System.out.println(ans);

        br.close();
    }
}