import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int snow1 = arr[i] + arr[j];
                int start = 0;
                int end = n-1;
                while (start < end) {
                    if (start == i || start == j) {
                        start++;
                        continue;
                    }
                    if (end == i || end == j) {
                        end--;
                        continue;
                    }
                    int snow2 = arr[start] + arr[end];
                    ans = Math.min(ans, Math.abs(snow1-snow2));
                    if (snow1 > snow2) start++;
                    else if (snow1 < snow2) end--;
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}