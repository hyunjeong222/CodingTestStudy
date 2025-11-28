import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken()); // 문자열 길이
        int n = Integer.parseInt(st.nextToken()); // 문자열 개수

        List<String> lotto = new ArrayList<>(); // 로또에 작성한 문자열
        for (int i = 0; i < n; i++) {
            lotto.add(br.readLine());
        }

        int k = Integer.parseInt(br.readLine()); // 추첨할 문자열 길이

        Set<String> set = new HashSet<>(); // 부분 문자열
        for (int i = 0; i < n; i++) {
            String now = lotto.get(i);

            for (int j = 0; j <= l-k; j++) {
                set.add(now.substring(j, j+k));
            }
        }
        // System.out.println(set);

        int ans = 0;
        for (String partial : set) {
            int score = 0;

            for (int i = 0; i < n; i++) {
                String now = lotto.get(i);

                for (int j = 0; j <= l-k; j++) {
                    if (now.substring(j, j+k).equals(partial)) score++;
                }
            }

            ans = Math.max(ans, score);
        }

        System.out.println(ans);

        br.close();
    }
}