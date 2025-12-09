import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static long[] arr, tree;
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수의 개수
        m = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        k = Integer.parseInt(st.nextToken()); // 구간의 곱을 구하는 횟수

        arr = new long[n+1];
        tree = new long[n*4];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        init(1, n, 1);

        while (true) {
            if (m == 0 && k == 0) break; // 종료 조건

            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) { // b번째 수를 c로 바꾸고
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                arr[b] = c;
                update(1, n, 1, b, c);
                m--;
            } else if (cmd == 2) { // b부터 c까지의 곱을 구하여 출력
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                sb.append(mul(1, n, 1, b, c)).append("\n");
                k--;
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start+end)/2;
        return tree[node] = (init(start, mid, node*2)*init(mid+1, end, node*2+1))%MOD;
    }

    private static long update(int start, int end, int node, int idx, long value) {
        if (idx < start || idx > end) {
            return tree[node];
        }

        if (start == end) {
            return tree[node] = value;
        }

        int mid = (start+end)/2;
        return tree[node] = (update(start, mid, node*2, idx, value)*update(mid+1, end, node*2+1, idx, value))%MOD;
    }

    private static long mul(int start, int end, int node, int left, long right) {
        if (left > end || right < start) return 1;

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start+end)/2;
        return (mul(start, mid, node*2, left, right)*mul(mid+1, end, node*2+1, left, right))%MOD;
    }
}