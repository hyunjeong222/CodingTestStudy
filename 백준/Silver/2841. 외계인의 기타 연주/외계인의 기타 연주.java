import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int total = 0;
        Stack<Integer>[] stack = new Stack[7];
        for (int i = 0; i < 7; i++) {
            stack[i] = new Stack<Integer>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int line_num = Integer.parseInt(st.nextToken());
            int plat_num = Integer.parseInt(st.nextToken());
            while (!stack[line_num].isEmpty() && stack[line_num].peek() > plat_num) {
                stack[line_num].pop();
                total++;
            }
            if (stack[line_num].isEmpty() || stack[line_num].peek() < plat_num) {
                stack[line_num].push(plat_num);
                total++;
            }
        }
        bw.append(total + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}