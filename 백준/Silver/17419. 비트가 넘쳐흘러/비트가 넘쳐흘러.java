import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String binary = br.readLine();

        // 이진수를 정수로 변환
        int k = Integer.parseInt(binary, 2);
        // System.out.println(k);

        int cnt = 0;
        while (k != 0) {
            k = k - (k & (~k + 1));
            cnt++;
        }

        System.out.println(cnt);
    }
}