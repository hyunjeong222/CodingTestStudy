import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int cnt = 0; // 자릿수
        int plus = 1; // 자릿수에 더하는 값
        int num = 10; // 몇 자리 수인지 체크
        for (int i = 1; i <= n; i++) {
            if (i%num == 0) {
                plus++;
                num *= 10;
            }

            cnt += plus;
        }

        System.out.println(cnt);

        br.close();
    }
}