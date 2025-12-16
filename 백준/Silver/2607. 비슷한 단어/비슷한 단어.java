import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine()); // 단어의 개수
        int ans = 0; // 비슷한 단어의 개수
        String first = br.readLine();
        for (int i = 0; i < n-1; i++) {
            String str = br.readLine();

            int cnt = 0; // 같은 문자 개수

            int[] alpha = new int[26];
            for (int j = 0; j < first.length(); j++) {
                alpha[first.charAt(j)-'A']++;
            }

            for (int j = 0; j < str.length(); j++) {
                if (alpha[str.charAt(j)-'A'] > 0) {
                    cnt++;
                    alpha[str.charAt(j)-'A']--;
                }
            }

            // 기준 문자열과 비교 문자열의 길이가 같다면
            // 모든 문자의 종류가 같거나 하나의 문자를 다른 문자로 바꾸거나
            if (first.length() == str.length() && (first.length() == cnt || first.length()-1 == cnt)) ans++;
            // 기준 문자열의 길이가 짧다면
            else if (first.length() == str.length()-1 && str.length()-1 == cnt) ans++;
            // 기준 문자열의 길이가 길다면
            else if (first.length() == str.length()+1 && str.length() == cnt) ans++;
        }

        System.out.println(ans);
        
        br.close();
    }
}