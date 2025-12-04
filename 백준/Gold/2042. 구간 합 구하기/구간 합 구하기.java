import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static long[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수의 개수
        m = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        k = Integer.parseInt(st.nextToken()); // 구간 합을 구하는 횟수

        arr = new long[n+1];
        tree = new long[n*4];

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        init(1, n, 1);

        while (true) {
            if (m == 0 && k == 0) break;

            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken()); // 명령
            if (cmd == 1) { // b번째 수를 c로 바꾸기
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                long dif = c-arr[b];

                update(1, n, 1, b, dif);

                arr[b] = c;

                m--;
            } else { // 2 : b번째 수부터 c번째 수까지의 합 구해서 출력
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                long sum = prefixSum(1, n, 1, b, c);
                sb.append(sum).append("\n");

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
        return tree[node] = init(start, mid, node*2)+init(mid+1, end, node*2+1);
    }

    private static void update(int start, int end, int node, int idx, long dif) {
        if (start <= idx && idx <= end) {
            tree[node] += dif;
        } else return;

        if (start == end) return;

        int mid = (start+end)/2;
        update(start, mid, node*2, idx, dif);
        update(mid+1, end, node*2+1, idx, dif);
    }

    private static long prefixSum(int start, int end, int node, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start+end)/2;
        return prefixSum(start, mid, node*2, left, right)+prefixSum(mid+1, end, node*2+1, left, right);
    }
}