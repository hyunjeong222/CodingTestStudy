import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static ArrayList<Long> list = new ArrayList<>();
    static final int INF = 1023;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        backtracking(0, 0);
        Collections.sort(list);
        
        if (n > INF) System.out.println(-1); // 줄어드는 수는 1023개
        else System.out.println(list.get(n-1));
    }

    private static void backtracking(long num, int depth) {
        if (!list.contains(num)) list.add(num);

        if (depth >= 10) { // 모든 수 탐색 완료
            return;
        }

        backtracking(num*10+arr[depth], depth+1);
        backtracking(num, depth+1);
    }
}