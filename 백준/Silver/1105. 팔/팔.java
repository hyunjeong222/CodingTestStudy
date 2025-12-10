import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        String l = st.nextToken();
        String r = st.nextToken();

        int ans = 0;

        if (l.length() != r.length()) { // 자릿수가 다르다면 8의 개수가 0인 수 존재
            ans = 0;
        } else { // 자릿수가 같다면 각 맨 앞자리 수부터 비교해 8의 개수 세기
            int i = 0;
            while (i < l.length() && l.charAt(i) == r.charAt(i)) {
                if (l.charAt(i) == '8') {
                    ans++;
                }
                i++;
            }
        }

        System.out.println(ans);

        br.close();
    }
}