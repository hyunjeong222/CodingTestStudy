import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int b = Integer.parseInt(br.readLine());
            arr[i] = b;
            set.add(b);
        }

        int ans = 1;
        for (int s : set) {
            int pre = arr[0];
            int cnt = 1;
            for (int i = 1; i < n; i++) {
                if (s == arr[i]) continue;

                if (pre == arr[i]) {
                    cnt++;
                    ans = Math.max(ans, cnt);
                } else {
                    cnt = 1;
                }

                pre = arr[i];
            }
        }

        System.out.println(ans);

        br.close();
    }
}