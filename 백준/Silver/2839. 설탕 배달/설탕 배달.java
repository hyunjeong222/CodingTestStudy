import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 설탕
        int ans = 0;
        while (n > 0) {
            if (n % 5 == 0) {
                ans += n / 5;
                System.out.println(ans);
                return;
            } else if (n < 3) {
                System.out.println(-1);
                return;
            }
            n -= 3;
            ans++;
        }
        System.out.println(ans);
        br.close();
    }
}