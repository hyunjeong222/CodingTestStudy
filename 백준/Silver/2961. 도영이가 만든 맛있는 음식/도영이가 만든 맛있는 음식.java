import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n ;
    static int[][] taste;
    static boolean[] checked;
    static int ans = Integer.MAX_VALUE; // 신맛과 쓴맛의 최솟값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine()); // 재료의 개수

        taste = new int[n][2]; // 신맛과 쓴맛을 저장 할 배열
        checked = new boolean[n]; // 사용한 신맛과 쓴맛을 체크 할 방문배열
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            taste[i][0] = Integer.parseInt(st.nextToken());
            taste[i][1] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, 1, 0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int idx, int material, int mulSour, int sumBitter) {
        if (idx == n) {
            if (material != 0) {
                ans = Math.min(ans, Math.abs(mulSour-sumBitter));
            }
            return;
        }

        checked[idx] = true;
        dfs(idx+1, material+1, mulSour*taste[idx][0], sumBitter+taste[idx][1]);
        checked[idx] = false;
        dfs(idx+1, material, mulSour, sumBitter);
    }
}