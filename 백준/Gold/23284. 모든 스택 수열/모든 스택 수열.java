import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] num;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        num = new int[n+1];
        checked = new boolean[n+1];

        recur(1, 0);

        System.out.println(sb.toString());

        br.close();
    }

    private static void recur(int depth, int next) { // next : 다음에 선택 가능한 최소 값
        if (depth == n+1) {
            for (int i = 1; i <= n; i++) {
                sb.append(num[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = 1; i <= n; i++) {
            if (checked[i]) continue;

            if (i > num[depth-1] && i < next) break;

            num[depth] = i;
            checked[i] = true;
            if (i >= next) recur(depth+1, i+1); // 오름차순 유지 시작
            else recur(depth+1, next); // 아직 제한 구간
            checked[i] = false;

        }
    }
}