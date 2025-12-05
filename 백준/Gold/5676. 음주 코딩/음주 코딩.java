import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String input = "";
        while ((input = br.readLine()) != null) { // EOF 처리
            st = new StringTokenizer(input);
            int n = Integer.parseInt(st.nextToken()); // 수열의 크기
            int k = Integer.parseInt(st.nextToken()); // 게임 라운드 수

            arr = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                // 오버플로우 방지
                arr[i] = (tmp == 0) ? 0 : (tmp > 0) ? 1 : -1;
            }
            tree = new int[n*4];
            init(1, n, 1);

            while (k --> 0) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int i = Integer.parseInt(st.nextToken());

                if (cmd.equals("C")) { // 변경 명령
                    int v = Integer.parseInt(st.nextToken());
                    v = (v == 0) ? 0 : (v > 0) ? 1 : -1; // 오버플로우 방지

                    update(1, n, 1, i, v);
                } else if (cmd.equals("P")) { // 곱셈 명령
                    int j = Integer.parseInt(st.nextToken());
                    long res = mul(1, n, 1, i, j);

                    sb.append((res == 0) ? 0 : (res > 0) ? "+" : "-");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start+end)/2;
        return tree[node] = init(start, mid, node*2)*init(mid+1, end, node*2+1);
    }

    private static int mul(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 1;

        if (left <= start && end <= right) return tree[node];

        int mid = (start+end)/2;
        return mul(start, mid, node*2, left, right)*mul(mid+1, end, node*2+1, left, right);
    }

    private static int update(int start, int end, int node, int idx, int value) {
        if (idx < start || idx > end) {
            return tree[node];
        }

        if (start == end) {
            return tree[node] = value;
        }

        int mid = (start+end)/2;
        return tree[node] = update(start, mid, node*2, idx, value)*update(mid+1, end, node*2+1, idx, value);
    }
}