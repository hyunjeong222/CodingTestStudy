import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<Integer> list = new ArrayList<>();
        // 두 수의 합 구하기
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                list.add(arr[i]+arr[j]);
            }
        }
        Collections.sort(list);

        // sum + arr[i] = arr[j]
        // sum = arr[j] - arr[i]
        int ans = 0;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[j] - arr[i] < 0) continue;

                int start = 0;
                int end = list.size()-1;
                int target = arr[j] - arr[i];
                while (start < end) {
                    int mid = start + (end-start) / 2;
                    int num = list.get(mid);

                    if (num > target) {
                        end = mid-1;
                    } else if (num < target) {
                        start = mid+1;
                    } else {
                        ans = Math.max(ans, num+arr[i]);
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}