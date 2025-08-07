import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, c;
    static int[] arr;
    static ArrayList<Integer> left, right;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 물건의 개수
        c = Integer.parseInt(st.nextToken()); // 가방에 넣을 수 있는 무게

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        left = new ArrayList<>();
        right = new ArrayList<>();
        comb(left, 0, n/2, 0);
        comb(right, n/2, n, 0);
        Collections.sort(right);

        int ans = 0;
        for (int i = 0; i < left.size(); i++) {
            int searchValue = c - left.get(i);
            ans += binarySearch(right, searchValue)+1;
        }

        System.out.println(ans);

        br.close();
    }

    private static int binarySearch(ArrayList<Integer> sum, int target) {
        int left = 0;
        int right = sum.size()-1;

        while (left <= right) {
            int mid = (left+right)/2;

            if (sum.get(mid) <= target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return right;
    }

    private static void comb(ArrayList<Integer> list, int start, int end, int sum) {
        if (sum > c) return; // 탈출 조건

        if (start == end) {
            list.add(sum);
            return;
        }

        // 물건을 넣는 경우
        comb(list, start+1, end, sum+arr[start]);
        // 물건을 넣지 않는 경우
        comb(list, start+1, end, sum);
    }
}