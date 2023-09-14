import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, s;
    static int[] arr;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정수의 개수
        s = Integer.parseInt(st.nextToken()); // 그 수열의 원소를 다 더한 값

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);
        ans = s == 0 ? ans-1 : ans; // s가 0인 경우 공집합이 될 수도 있음

        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int depth, int sum) {
        if (depth == n) {
            if (s == sum) {
                ans++;
            }
            return;
        }

        dfs(depth+1, sum+arr[depth]); // 원소 선택해서 합에 더하고 넘어가기
        dfs(depth+1, sum); // 원소 선택안하고 넘어가기
    }
}