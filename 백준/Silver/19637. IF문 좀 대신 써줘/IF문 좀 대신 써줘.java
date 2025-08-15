import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] limit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 칭호의 게수
        int m = Integer.parseInt(st.nextToken()); // 캐릭터들의 개수

        String[] name = new String[n];
        limit = new int[n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            name[i] = st.nextToken();
            limit[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        while (m --> 0) {
            int target = Integer.parseInt(br.readLine());

            int idx = binarySearch(target);
            sb.append(name[idx]).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int binarySearch(int target) {
        int left = 0;
        int right = n-1;

        while (left <= right) {
            int mid = (left+right)/2;

            if (limit[mid] < target) left = mid+1;
            else right = mid-1;
        }

        return left;
    }
}