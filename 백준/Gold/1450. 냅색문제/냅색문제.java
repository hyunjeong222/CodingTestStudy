import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 물건의 개수
        c = Integer.parseInt(st.nextToken()); // 가방에 넣을 수 있는 무게

        ArrayList<Integer> bag1 = new ArrayList<>();
        ArrayList<Integer> bag2 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (i < n/2) bag1.add(Integer.parseInt(st.nextToken()));
            else bag2.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> sum1 = new ArrayList<>();
        ArrayList<Integer> sum2 = new ArrayList<>();

        dfs(0, 0, bag1, sum1);
        dfs(0, 0, bag2, sum2);

        Collections.sort(sum2);

        int ans = 0;
        for (int i = 0; i < sum1.size(); i++) {
            int searchValue = c - sum1.get(i);
            ans += binarySearch(sum2, searchValue)+1;
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

    private static void dfs(int idx, int sum, ArrayList<Integer> bag, ArrayList<Integer> ans) {
        if (sum > c) return; // 탈출 조건

        if (idx == bag.size()) {
            ans.add(sum);
            return;
        }

        // 물건을 넣는 경우
        dfs(idx+1, sum+bag.get(idx), bag, ans);
        // 물건을 넣지 않는 경우
        dfs(idx+1, sum, bag, ans);
    }
}