import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, x, y;
    static int cnt = 0;
    static int[] arr;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 2n
        // x번째 수와 y번째 수는 같다
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        arr = new int[2*n+1];
        checked = new boolean[2*n+1];
        arr[x] = arr[y] = y-x-1;
        checked[y-x-1] = true;
        
        dfs(1);

        System.out.println(cnt);

        br.close();
    }

    private static void dfs(int idx) {
        if (idx == 2*n) {
            cnt++; // 랭퍼드 수열의 개수
            return;
        }

        if (arr[idx] == 0) {
            for (int i = 1; i <= n; i++) {
                if (checked[i]) continue;

                if (idx+i+1 <= 2*n && arr[idx+i+1] == 0) {
                    arr[idx] = arr[idx+i+1] = i;
                    checked[i] = true;
                    dfs(idx+1);
                    arr[idx] = arr[idx+i+1] = 0;
                    checked[i] = false;
                }
            }
        } else {
            dfs(idx+1);
        }
    }
}