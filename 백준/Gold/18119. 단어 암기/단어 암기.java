import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정수
        int m = Integer.parseInt(st.nextToken());

        int alphabet = (1 << 27)-1;
        int[] arr = new int[n];
        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                int c = str.charAt(j)-'a';
                arr[i] |= 1 << c;
            }
        }

        int type, x;
        int cnt;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            x = st.nextToken().charAt(0)-'a';

            if (type == 1) { // 알파벳 x를 잊음
                alphabet &= ~(1 << x);
            } else { // 알파벳 x를 기억해냄
                alphabet |= (1 << x);
            }

            cnt = 0;
            for (int i : arr) {
                if ((alphabet&i) >= i) cnt++;
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}