import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] checked, finish; // finish : 방문한 노드에서 싸이클을 이미 뽑아냈었는가 확인
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            checked = new boolean[n+1];
            finish = new boolean[n+1];
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= n; i++) {
                if (finish[i]) continue;
                dfs(i);
            }
            sb.append(n-cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int now) {
        if (finish[now]) return;

        if (checked[now]) { // 방문 했었다 == 사이클 구성원이다
            finish[now] = true;
            cnt++;
        }

        checked[now] = true;
        dfs(arr[now]); // 연결된 다음 노드
        finish[now] = true; // 사이클 아닌 애도 검사 끝

        checked[now] = false; // 방문 체크 초기화
    }
}