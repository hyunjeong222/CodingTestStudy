import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = n-1;

            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }
                if (end == i) {
                    end--;
                    continue;
                }

                int now = arr[start]+arr[end];
                if (now == arr[i]) {
                    cnt++;
                    break;
                } else if (now < arr[i]) start++;
                else end--;
            }
        }

        System.out.println(cnt);

        br.close();
    }
}