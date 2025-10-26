import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k, sum;
    static int[][] elements;
    static int[] comb;
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 마라탕 재료의 수
        k = Integer.parseInt(st.nextToken()); // 고를 재료의 수

        if (k == 1) { // 재료를 하나만 넣으면 궁합이 없으므로
            // 마라탕의 맛은 0
            System.out.println(0);
            return;
        }

        elements = new int[n][n]; // 궁합을 나타내는 수열
        comb = new int[k]; // 재료 조합

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                elements[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 먼저 재료 조합
        materialComb(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void materialComb(int idx, int depth) {
        if (depth == k) {
            // 궁합의 합 조합해서 최댓값 찾기
            int[] chemistry = new int[2];

            sum = 0;
            chemistryComb(0, 0, chemistry);
            ans = Math.max(ans, sum);

            return;
        }

        for (int i = idx; i < n; i++) {
            comb[depth] = i;
            materialComb(i+1, depth+1);
        }
    }

    private static void chemistryComb(int idx, int depth, int[] arr) {
        if (depth == 2) {
            sum += elements[arr[0]][arr[1]];

            return;
        }

        for (int i = idx; i < k; i++) {
            arr[depth] = comb[i];
            chemistryComb(i+1, depth+1, arr);
        }
    }
}