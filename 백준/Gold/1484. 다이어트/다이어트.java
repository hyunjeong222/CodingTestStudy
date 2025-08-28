import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 성원이의 현재 몸무게의 제곱에서 성원이가 기억하고 있던 몸무게의 제곱을 뺀 것
        int g = Integer.parseInt(br.readLine());

        long left = 1, right = 2;
        boolean flag = false;
        while (right < 100_000) {
            long ow = left*left;
            long aw = right*right;

            if (aw-ow == g) {
                sb.append(right).append("\n");
                flag = true;
            }

            if (aw-ow > g) {
                left++;
            } else {
                right++;
            }
        }

        if (!flag) {
            System.out.println(-1);
            return;
        }

        System.out.println(sb.toString());

        br.close();
    }
}