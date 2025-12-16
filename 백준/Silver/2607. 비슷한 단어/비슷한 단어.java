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
        String firstStr = br.readLine();

        int ans = 0;
        for (int i = 0; i < n-1; i++) {
            String str = br.readLine();

            int  cnt = 0; // 같은 단어 개수
            int[] words = new int[26];
            for (int j = 0; j < firstStr.length(); j++) {
                words[firstStr.charAt(j)-'A']++;
            }

            for (int j = 0; j < str.length(); j++) {
                if (words[str.charAt(j)-'A'] > 0) {
                    cnt++;
                    words[str.charAt(j)-'A']--;
                }
            }

            // 같은 경우 or 하나만 다른 경우
            if (firstStr.length() == str.length() && (firstStr.length() == cnt || firstStr.length()-1 == cnt)) ans++;
            // 비교 문자열이 짧은 경우 - 제거
            else if (firstStr.length() == str.length()-1 && str.length()-1 == cnt) ans++;
            // 비교 문자열이 긴 경우 - 추가
            else if (firstStr.length() == str.length()+1 && str.length() == cnt) ans++;
        }

        System.out.println(ans);

        br.close();
    }
}