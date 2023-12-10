import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int n, k;
    static String[] arr;
    static boolean[] checked;
    static Set<String> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        arr = new String[n];
        checked = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }
        set = new HashSet<>();
        dfs(0, "");
        System.out.println(set.size());
    }

    private static void dfs(int depth, String str) {
        if (depth == k) {
            set.add(str);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
                checked[i] = true;
                dfs(depth+1, str+arr[i]);
                checked[i] = false;
            }
        }
    }
}