import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Integer.parseInt(st.nextToken());

        int cal = 0;
        int start = 1;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int t = Integer.parseInt(st.nextToken());

            if ((t|k) != k) { // t를 추가해도 목표 값 k를 넘지 않도록 하는지 판단
                cal = 0;
                start = i+1;
            } else {
                cal |= t;

                if (cal == k) {
                    System.out.println(start + " " + i);
                    return;
                }
            }
        }

        System.out.println(-1);
    }
}