import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int ans = 0;

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            int money = 0;

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == b && b == c) {
                money = 10000 + (a * 1000);
            } else if (a != b && b != c && a != c) {
                money = Math.max(a, Math.max(b, c)) * 100;
            } else {
                if (a == b || a == c) {
                    money = 1000 + (a*100);
                } else { // b == c
                    money = 1000 + (b*100);
                }
            }
            ans = Math.max(ans, money);
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}