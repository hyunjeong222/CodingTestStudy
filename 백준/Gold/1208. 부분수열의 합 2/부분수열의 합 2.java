import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        comb(0, n/2, left, 0);
        comb(n/2, n, right, 0);
        Collections.sort(left);
        Collections.sort(right);

        long ans = 0;
        for (int i = 0; i < left.size(); i++) {
            int target = s-left.get(i);
            int upper = upperBound(right, target);
            int lower = lowerBound(right, target);
            ans += upper - lower;
        }

        System.out.println(s == 0 ? ans-1 : ans);

        br.close();
    }

    private static int upperBound(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left+right)/2;

            if (list.get(mid) <= target) left = mid+1;
            else right = mid;
        }

        return right;
    }

    private static int lowerBound(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left+right)/2;

            if (list.get(mid) >= target) right = mid;
            else left = mid+1;
        }

        return right;
    }

    private static void comb(int start, int end, ArrayList<Integer> list, int sum) {
        if (start == end) {
            list.add(sum);
            return;
        }

        comb(start+1, end, list, sum+arr[start]);
        comb(start+1, end, list, sum);
    }
}