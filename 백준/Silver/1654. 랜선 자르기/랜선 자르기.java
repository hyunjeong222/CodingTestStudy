import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()); // 갖고있는 랜선의 수
        int n = Integer.parseInt(st.nextToken()); // 필요한 랜선의 수

        int[] len = new int[k];
        for (int i = 0; i < k; i++) {
            len[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(len);

        long left = 1;
        long right = len[k-1];
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 0; i < k; i++) {
                cnt += len[i] / mid;
            }
            if (cnt >= n) left = mid+1;
            else right = mid-1;
        }

        System.out.println(right);

        br.close();
    }
}