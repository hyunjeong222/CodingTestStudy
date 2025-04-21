import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int N = 10;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] mushrooms = new int[N];
        for (int i = 0; i < N; i++) {
            mushrooms[i] = Integer.parseInt(br.readLine());
        }

        int sum = 0; int tmp = 0;
        for (int i = 0; i < N; i++) {
            sum += mushrooms[i];

            if (sum > 100) break;

            tmp = sum;
        }

        if (100 - tmp < sum - 100) {
            System.out.println(tmp);
            return;
        }

        System.out.println(sum);

        br.close();
    }
}