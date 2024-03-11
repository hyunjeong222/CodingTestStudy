import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 센서의 개수
        int k = Integer.parseInt(br.readLine()); // 집중국의 개수
        int[] arr = new int[n]; // 센서의 좌표
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if (n <= k) {
            System.out.println(0);
            System.exit(0);
        }
        Arrays.sort(arr);
        Integer[] diff = new Integer[n-1];
        for (int i = 0; i < n-1; i++) {
            diff[i] = arr[i+1] - arr[i];
        }
        Arrays.sort(diff, Comparator.reverseOrder());
        int ans = 0;
        for (int i = k-1; i < n-1; i++) {
            ans += diff[i];
        }
        System.out.println(ans);
    }
}