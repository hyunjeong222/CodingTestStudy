import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] honey;
    static int total;
    static int[] toRightTotal;
    static int[] toLeftTotal;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        honey = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        toRightTotal = new int[n+1];
        toLeftTotal = new int[n+1];
        for (int i = 1; i <= n; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            toRightTotal[i] = toRightTotal[i-1] + honey[i];
        }
        for (int i = n-1; i >= 0; i--) {
            toLeftTotal[i] = honey[i+1] + toLeftTotal[i+1];
        }
        total = toLeftTotal[0];
        max = Integer.MIN_VALUE;
        case1();
        case2();
        case3();
        bw.append(max + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void case3() {
        int bee1, bee2 = 0;
        for (int i = 2; i <= n-1; i++) {
            bee1 = toRightTotal[i] - honey[1];
            bee2 = toLeftTotal[i-1] - honey[n];
            max = Math.max(max, bee1+bee2);
        }
    }

    private static void case2() {
        int bee1, bee2 = 0;
        for (int i = n-1; i >= 2; i--) {
            bee1 = total - honey[n] - honey[i];
            bee2 = total - toLeftTotal[i-1];
            max = Math.max(max, bee1+bee2);
        }
    }

    private static void case1() {
        int bee1, bee2 = 0;
        for (int i = 2; i <= n-1; i++) {
            bee1 = total - honey[1] - honey[i];
            bee2 = total - toRightTotal[i];
            max = Math.max(max, bee1+bee2);
        }
    }
}
