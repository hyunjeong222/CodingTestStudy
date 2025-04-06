import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n ,k;
    static int[] students;
    static boolean[] checked;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        students = new int[n];
        for (int i = 0; i < n; i++) {
            students[i] = Integer.parseInt(br.readLine());
        }

        checked = new boolean[n];

        dfs(0);

        System.out.println(ans);
    }

    private static void dfs(int i) {
        if (checked[i]) {
            ans = -1;
            return;
        }

        if (i == k) {
            return;
        } else {
            checked[i] = true;
            ans++;
            dfs(students[i]);
        }
    }
}