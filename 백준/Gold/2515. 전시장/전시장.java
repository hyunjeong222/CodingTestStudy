import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, s;
    static Info[] pictures;
    static public class Info implements Comparable<Info> {
        int height; int cost;
        public Info(int height, int cost) {
            this.height = height; this.cost = cost;
        }
        @Override
        public int compareTo(Info o) {
            if (this.height == o.height) return o.cost-this.cost;
            return this.height - o.height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 그림의 개수
        s = Integer.parseInt(st.nextToken()); // 세로 길이가 특정 s인 그림만 판매 가능

        pictures = new Info[n+1];
        pictures[0] = new Info(0, 0);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            pictures[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(pictures);

        int[] dp = new int[n+1];
        dp[0] = pictures[0].cost;
        for (int i = 1; i <= n; i++) {
            int idx = binarySearch(i);

            if (idx > 0) {
                dp[i] = Math.max(dp[i-1], dp[idx]+pictures[i].cost);
            } else dp[i] = Math.max(dp[i-1], pictures[i].cost);
        }

        System.out.println(dp[n]);
    }

    private static int binarySearch(int nowPictureIdx) {
        int start = 0;
        int end = nowPictureIdx;
        int mid;
        while (start <= end) {
            mid = start + (end-start) / 2;

            int diff = pictures[nowPictureIdx].height-pictures[mid].height;

            if (diff >= s) { // s와 같거나 커서 판매 그림이라면
                start = mid+1; // 범위를 오른쪽으로 이동해서 다음으로 길이가 긴 그림과 비교
            } else end = mid-1;
        }

        return end;
    }
}