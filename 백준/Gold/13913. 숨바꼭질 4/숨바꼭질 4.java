import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] checked;
    static int[] before; // 이동 전 위치 저장
    static int size = 100001;
    static int ans; // 동생을 찾는 가장 빠른 시간
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 수빈이의 현재 위치
        k = Integer.parseInt(st.nextToken()); // 동생의 위치

        checked = new int[size];
        before = new int[size];

        ans = bfs(n);
        bw.append(ans + "\n");

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int idx = k;
        while (idx != n) {
            stack.push(idx);
            idx = before[idx];
        }
        stack.push(idx);
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
        bw.append(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        checked[x] = 1;

        while (!que.isEmpty()) {
            x = que.poll();
            if (x == k) return checked[x]-1;

            if (x-1 >= 0 && checked[x-1] == 0) {
                checked[x-1] = checked[x]+1;
                que.offer(x-1);
                before[x-1] = x;
            }
            if (x+1 < size && checked[x+1] == 0) {
                checked[x+1] = checked[x]+1;
                que.offer(x+1);
                before[x+1] = x;
            }
            if (x*2 < size && checked[x*2] == 0) {
                checked[x*2] = checked[x]+1;
                que.offer(x*2);
                before[x*2] = x;
            }
        }

        return -1;
    }
}