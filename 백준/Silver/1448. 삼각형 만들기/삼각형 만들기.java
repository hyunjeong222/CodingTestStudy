import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[] len = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(len);

        int max = -1;
        // 뒤에서부터 만들어서 최댓값 찾기
        for (int i = n-1; i >= 2; i--) {
            // 가장 긴 변의 길이는 나머지 두 변의 길이의 합보다 작아야 한다.
            if (len[i] < len[i-1]+len[i-2]) {
                max = len[i]+len[i-1]+len[i-2];
                break;
            }
        }

        System.out.println(max);

        br.close();
    }
}