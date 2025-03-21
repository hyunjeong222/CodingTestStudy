import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static char[] arr;
    static Set<String> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();

        set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            // L, R, 만들어진 숫자, 만드는 방법
            dfs(i, i, ""+arr[i], ""+arr[i]);
        }

        System.out.println(set.size());
    }

    private static void dfs(int L, int R, String s, String path) {
        if (L == 0 && R == arr.length-1) {
            set.add(path);
            return;
        }

        if (L-1 >= 0) {
            dfs(L-1, R, arr[L-1]+s, path+arr[L-1]+s);
        }
        if (R+1 < arr.length) {
            dfs(L, R+1, s+arr[R+1], path+s+arr[R+1]);
        }
    }
}