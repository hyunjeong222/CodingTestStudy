import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static int n;
    static int[] num = {1, 5, 10, 50};
    static HashSet<Integer> set = new HashSet<>(); // 만들 수 있는 서로 다른 수, 중복 X
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        back(0, 0, 0);

        System.out.println(set.size());
    }

    public static void back(int depth, int sum, int idx) {
        if (depth == n) {
            set.add(sum);
            return;
        }

        for (int i = idx; i < 4; i++) {
            back(depth+1, sum+num[i], i);
        }
    }
}
