import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];
        tree = new int[n*4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, n, 1);

        m = Integer.parseInt(br.readLine());
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                // 기존 값 arr[i]는 홀수 → 새 값 x는 짝수
                if (arr[l]%2==1 && r%2==0) update(1, n, 1, l, 1);
                // 기존 값 arr[i]는 짝수 → 새 값 x는 홀수
                else if (arr[l]%2==0 && r%2==1) update(1, n, 1, l, 0);

                arr[l] = r;
            } else if (cmd == 2 || cmd == 3) {
                long evenCnt = getEvenCnt(1, n, 1, l, r); // 짝수의 개수
                if (cmd == 2) {
                    sb.append(evenCnt).append("\n");
                } else if (cmd == 3) {
                    sb.append(r-l+1-evenCnt).append("\n");
                }
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int init(int start, int end, int node) {
        if (start == end) {
            if (arr[start]%2==0) return tree[node] = 1;
            else return 0;
        }

        int mid = (start+end)/2;
        return tree[node] = init(start, mid, node*2)+init(mid+1, end, node*2+1);
    }

    private static int update(int start, int end, int node, int idx, int val) {
        if (idx < start || end < idx) return tree[node];
        if (start == end) return tree[node] = val;

        int mid = (start+end)/2;
        return tree[node] = update(start, mid, node*2, idx, val)+update(mid+1, end, node*2+1, idx, val);
    }

    private static long getEvenCnt(int start, int end, int node, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return tree[node];

        int mid = (start+end)/2;
        return getEvenCnt(start, mid, node*2, left, right)+getEvenCnt(mid+1, end, node*2+1, left, right);
    }
}