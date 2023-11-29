import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static char[] arr, copy;
    static String arrStr, copyStr;
    static public class pos {
        String str;
        int cnt;
        public pos (String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 순열의 크기
        k = Integer.parseInt(st.nextToken()); // 뒤집어야 하는 수
        arr = br.readLine().replace(" ", "").toCharArray();
        copy = Arrays.copyOf(arr, n);
        Arrays.sort(arr);
        arrStr = new String(arr);
        copyStr = new String(copy);
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<pos> que = new LinkedList<>();
        Set<String> set = new HashSet<>();
        que.offer(new pos(copyStr, 0));

        while (!que.isEmpty()) {
            pos now = que.poll();
            String str = now.str;
            int cnt = now.cnt;

            if (arrStr.equals(str)) return cnt;

            if (!set.contains(str)) {
                set.add(str);
                for (int i = 0; i <= n-k; i++) {
                    que.offer(new pos(reverseStr(str, i, i+k), cnt+1));
                }
            }
        }

        return -1;
    }

    private static String reverseStr(String str, int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, i));

        String reverse = str.substring(i, j);
        for (int r = k-1; r >= 0; r--) {
            sb.append(reverse.charAt(r));
        }

        sb.append(str.substring(j, n));
        return sb.toString();
    }
}