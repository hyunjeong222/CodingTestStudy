import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 점의 수
        m = Integer.parseInt(st.nextToken()); // 선분의 수

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int lower = binarySearch(s, 0);
            int upper = binarySearch(e, 1);
            
            sb.append(upper-lower).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int binarySearch(int target, int type) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left+right)/2;

            if (type == 0 && arr[mid] >= target) right = mid;
            else if (type == 1 && arr[mid] > target) right = mid;
            else left = mid+1;
        }

        return left;
    }
}