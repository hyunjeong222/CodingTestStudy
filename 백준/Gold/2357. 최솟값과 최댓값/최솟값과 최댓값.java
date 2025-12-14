import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr, minTree, maxTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수열의 크기
        m = Integer.parseInt(st.nextToken()); // 쿼리의 개수
        arr = new int[n+1];
        minTree = new int[n*4];
        maxTree = new int[n*4];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        minInit(1, n, 1);
        maxInit(1, n, 1);

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(getMin(1, n, 1, a, b)).append(" ").append(getMax(1, n, 1, a, b)).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int minInit(int start, int end, int node) {
        if (start == end) return minTree[node] = arr[start];

        int mid = (start+end)/2;
        return minTree[node] = Math.min(minInit(start, mid, node*2), minInit(mid+1, end, node*2+1));
    }

    private static int maxInit(int start, int end, int node) {
        if (start == end) return maxTree[node] = arr[start];

        int mid = (start+end)/2;
        return maxTree[node] = Math.max(maxInit(start, mid, node*2), maxInit(mid+1, end, node*2+1));
    }

    private static int getMin(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return Integer.MAX_VALUE;
        if (left <= start && right >= end) return minTree[node];

        int mid = (start+end)/2;
        return Math.min(getMin(start, mid, node*2, left, right), getMin(mid+1, end, node*2+1, left, right));
    }

    private static int getMax(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return Integer.MIN_VALUE;
        if (left <= start && right >= end) return maxTree[node];

        int mid = (start+end)/2;
        return Math.max(getMax(start, mid, node*2, left, right), getMax(mid+1, end, node*2+1, left, right));
    }
}