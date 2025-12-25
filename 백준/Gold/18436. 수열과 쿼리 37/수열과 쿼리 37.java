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
       StringTokenizer st = new StringTokenizer(br.readLine());
       arr = new int[n+1];
       tree = new int[n*4];
       for (int i = 1; i <= n; i++) {
           arr[i] = Integer.parseInt(st.nextToken());
       }
       init(1, n, 1);

       m = Integer.parseInt(br.readLine());
       while (m --> 0) {
           st = new StringTokenizer(br.readLine());
           int cmd = Integer.parseInt(st.nextToken());
           int i = Integer.parseInt(st.nextToken());
           int x = Integer.parseInt(st.nextToken());

           if (cmd == 1) {
               if (arr[i]%2 == 1 && x%2 == 0) {
                   update(1, n, 1, i, 1);
               } else if (arr[i]%2 == 0 && x%2 == 1) {
                   update(1, n, 1, i, 0);
               }
               arr[i] = x;
           } else if (cmd == 2 || cmd == 3) { // 짝수, 홀수의 개수
                long cnt = query(1, n, 1, i, x);
                if (cmd == 2) {
                    sb.append(cnt).append("\n");
                } else {
                    sb.append(x-i+1-cnt).append("\n");
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

    private static long query(int start, int end, int node, int left, int right) {
       if (right < start || end < left) return 0;
       if (left <= start && end <= right) return tree[node];

       int mid = (start+end)/2;
       return query(start, mid, node*2, left, right)+query(mid+1, end, node*2+1, left, right);
    }

    private static int update(int start, int end, int node, int idx, int val) {
       if (idx < start || end < idx) return tree[node];
       if (start == end) return tree[node] = val;

       int mid = (start+end)/2;
       return tree[node] = update(start, mid, node*2, idx, val)+update(mid+1, end, node*2+1, idx, val);
    }
}