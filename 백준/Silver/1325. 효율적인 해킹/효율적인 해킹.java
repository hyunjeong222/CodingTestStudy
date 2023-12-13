import java.io.*;
import java.util.ArrayList;

public class Main {
    static int n, m;
    static computer[] list;
    static boolean[] checked;
    static int[] result;
    static public class computer {
        int idx; ArrayList<computer> node;
        public computer(int idx) {
            this.idx = idx;
            this.node = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        list = new computer[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new computer(i);
        }

        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);

            list[b].node.add(list[a]);
        }

        result = new int[n+1];
        for (int i = 1; i <= n; i++) {
            checked = new boolean[n+1];
            checked[i] = true;
            dfs(i, i);
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, result[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (result[i] == max) bw.write(i + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start, int now) {
        for (computer next : list[now].node) {
            if (!checked[next.idx]) {
                checked[next.idx] = true;
                dfs(start, next.idx);
                result[start]++;
            }
        }
    }
}