import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int n = 3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine(); // 만들고자 하는 단어

        int n = Integer.parseInt(br.readLine()); // 전공책의 개수
        int[] cost = new int[n]; // 가격
        String[] title = new String[n]; // 이름
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            title[i] = st.nextToken();
        }

        int total = 1 << n;
        int ans = -1;
        for (int i = 0; i < total; i++) { // 0은 아무 책도 선택 X
            int[] cnt = new int[26];
            int costSum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    String book = title[j];
                    for (int k = 0; k < book.length(); k++) {
                        cnt[book.charAt(k)-'A']++;
                    }
                    costSum += cost[j];
                }
            }

            if (check(cnt, t)) {
                if (ans == -1) ans = costSum;
                else ans = Math.min(ans, costSum);
            }
        }

        System.out.println(ans);

        br.close();
    }

    private static boolean check(int[] cnt, String t) {
        for (int i = 0; i < t.length(); i++) {
            if (cnt[t.charAt(i)-'A'] == 0) return false;
            cnt[t.charAt(i)-'A']--;
        }

        return true;
    }
}