import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 굴다리 길이
        m = Integer.parseInt(br.readLine()); // 가로등 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        len = new int[m]; // 가로등 위치
        for (int i = 0; i < m; i++) {
            len[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1;
        int end = n;
        int ans = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (possible(mid)) {
                ans = mid;
                end = mid-1;
            } else start = mid+1;
        }

        System.out.println(ans);
    }

    private static boolean possible(int target) {
        int prev = 0; // 이전 가로등이 비춘 마지막 지점, 0지점부터 시작

        for (int i = 0; i < len.length; i++) {
            if (len[i]-target <= prev) prev = len[i]+target;
            else return false;
        }

        return n-prev <= 0;
    }
}