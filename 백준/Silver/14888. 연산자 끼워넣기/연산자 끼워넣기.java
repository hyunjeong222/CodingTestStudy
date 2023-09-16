import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int[] operation;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        operation = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < operation.length; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        dfs(arr[0], 1);
        bw.append(max + "\n" + min);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int num, int depth) {
        if (depth == n) {
            max = Math.max(num, max);
            min = Math.min(num, min);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operation[i] > 0) {
                operation[i]--;

                switch (i) {
                    case 0 : dfs(num+arr[depth], depth+1); break;
                    case 1 : dfs(num-arr[depth], depth+1); break;
                    case 2 : dfs(num*arr[depth], depth+1); break;
                    case 3 : dfs(num/arr[depth], depth+1); break;
                }

                operation[i]++;
            }
        }
    }
}