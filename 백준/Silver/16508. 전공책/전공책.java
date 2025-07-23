import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine(); // 만들고자 하는 단어

        int n = Integer.parseInt(br.readLine()); // 전공책의 개수
        int[] cost = new int[n]; // 각 책의 가격
        String[] title = new String[n]; // 각 책의 이름
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken()); // 가격
            title[i] = st.nextToken(); // 제목
        }

        int total = 1 << n; // 총 조합 수
        int ans = -1; // 최소 비용, 단어를 만들 수 없다면 -1
        for (int i = 1; i < total; i++) { // 0은 공집합이므로 제외, 책 선택 조합을 나타내는 비트마스크
            int[] cnt = new int[26]; // 사용된 각 알파벳 별 갯수
            int costSum = 0; // 선택된 책들의 가격 합
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) { // j번 책이 선택되었으면
                    String book = title[j];
                    for (int k = 0; k < book.length(); k++) {
                        cnt[book.charAt(k)-'A']++;
                    }
                    costSum += cost[j];
                }
            }

            if (check(cnt, target)) {
                if (ans == -1) ans = costSum; // 처음 발견된 경우
                else ans = Math.min(ans, costSum); // 더 작은 비용으로 갱신
            }
        }

        System.out.println(ans);

        br.close();
    }

    private static boolean check(int[] cnt, String target) {
        for (int i = 0; i < target.length(); i++) {
            if (cnt[target.charAt(i)-'A'] == 0) return false;
            cnt[target.charAt(i)-'A']--; // 사용한 알파벳 차감
        }

        return true;
    }
}