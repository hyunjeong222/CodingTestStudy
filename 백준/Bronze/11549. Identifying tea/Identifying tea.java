import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int N = 5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int correct = Integer.parseInt(br.readLine());
        int[] ansArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ansArr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        for (int i : ansArr) {
            if (correct == i) ans++;
        }

        System.out.println(ans);
    }
}