import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static String operation;
    static int x;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        m = Integer.parseInt(br.readLine()); // 연산의 수

        StringTokenizer st;
        HashSet<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());

            operation = st.nextToken();
            x = 0;
            if (!operation.equals("all") && !operation.equals("empty")) x = Integer.parseInt(st.nextToken());

            switch (operation) {
                case "add" :
                    set.add(x);
                    break;
                case "remove" :
                    set.remove(x);
                    break;
                case "check" :
                    if (set.contains(x)) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "toggle" :
                    if (set.contains(x)) set.remove(x);
                    else set.add(x);
                    break;
                case "all" :
                    for (int i = 1; i <= 20; i++) {
                        set.add(i);
                    }
                    break;
                case "empty" :
                    set.clear();
                    break;
            }
        }
        bw.append(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}