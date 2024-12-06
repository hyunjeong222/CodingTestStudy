import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] liquid;
    static boolean[] checked;
    static ArrayList<Discount>[] list; // 물약 할인 정보
    static public class Discount {
        int num; int discount;
        public Discount(int num, int discount) {
            this.num = num; this.discount = discount;
        }
    }
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        liquid = new int[n+1];
        checked = new boolean[n+1];
        list = new ArrayList[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
            list[i] = new ArrayList<>();
        }

        // 물약 할인 정보
        for (int i = 1; i <= n; i++) {
            int idx = Integer.parseInt(br.readLine());
            for (int j = 0; j < idx; j++) {
                st = new StringTokenizer(br.readLine());
                int liquidNum = Integer.parseInt(st.nextToken());
                int liquidDiscount = Integer.parseInt(st.nextToken());
                list[i].add(new Discount(liquidNum, liquidDiscount));
            }
        }

        back(1, 0); // 물약 번호, 금액

        System.out.println(ans);
    }

    private static void back(int idx, int sum) {
        if (ans <= sum) return;

        if (n+1 == idx) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 1; i <= n ; i++) {
            if (checked[i]) continue;

            checked[i] = true; // 구매
            for (Discount d : list[i]) { // 할인 적용
                liquid[d.num] -= d.discount;
            }

            back(idx+1, sum+(liquid[i] <= 0 ? 1 : liquid[i]));

            for (Discount d : list[i]) { // 할인 취소
                liquid[d.num] += d.discount;
            }
            checked[i] = false; // 구매 취소
        }
    }
}