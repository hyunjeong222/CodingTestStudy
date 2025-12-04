import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 수열의 크기

        arr = new int[n+1];
        tree = new int[n*4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, n, 1);

        int m = Integer.parseInt(br.readLine()); // 쿼리의 개수
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken()); // 명령

            if (cmd == 1) {
                // Ai를 v로 바꿈
                int i = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                arr[i] = v;
                
                update(1, n, 1, i);
            } else { // 2 : 가장 작은 값의 인덱스 출력, 그러한 값이 여러개인 경우 인덱스가 작은 것 출력
                sb.append(tree[1]).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = start;
        }

        int mid = (start+end)/2;

        return tree[node] = minIdx(init(start, mid, node*2), init(mid+1, end, node*2+1));
    }

    private static int minIdx(int x, int y) {
        if (arr[x] == arr[y]) {
            return Math.min(x, y);
        }

        return arr[x] < arr[y] ? x : y;
    }

    private static int update(int start, int end, int node, int idx) {
        if (start > idx || end < idx) return tree[node];

        if (start == end) return tree[node] = idx;

        int mid = (start+end)/2;
        return tree[node] = minIdx(update(start, mid, node*2, idx), update(mid+1, end, node*2+1, idx));
    }
}