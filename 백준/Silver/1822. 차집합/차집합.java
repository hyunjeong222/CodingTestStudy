import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // a 집합 원소의 개수
        int m = Integer.parseInt(st.nextToken()); // b 집합 원소의 개수

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(b);

        StringBuilder sb = new StringBuilder();
        // A에 속하면서 B에는 속하지 않는 원소 찾기
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!binarySearch(b, a[i])) {
                sb.append(a[i]).append(" ");
                cnt++;
            }
        }

        if (cnt == 0) {
            System.out.println(cnt);
        } else {
            System.out.println(cnt);
            System.out.println(sb);
        }
    }

    private static boolean binarySearch(int[] b, int target) {
        int left = 0;
        int right = b.length-1;

        while (left <= right) {
            int mid = left + (right-left) / 2;

            if (b[mid] == target) return true;
            else if (b[mid] >= target) right = mid-1;
            else left = mid+1;
        }

        return false;
    }
}