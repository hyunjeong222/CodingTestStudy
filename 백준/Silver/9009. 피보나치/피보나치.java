import java.awt.image.RGBImageFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            long n = Long.parseLong(br.readLine());

            for (long x : solution(n)) {
                sb.append(x).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static ArrayList<Long> solution(long n) {
        ArrayList<Long> fivo = new ArrayList<>();
        ArrayList<Long> ans = new ArrayList<>();

        fivo.add(0L); fivo.add(1L); // 초기화
        int idx = 2;
        // 주어진 수보다 작은 피보나치 리스트 만들기
        while (true) {
           long plus = fivo.get(idx-1)+fivo.get(idx-2);
           if (plus > n) break;
           fivo.add(plus);
           idx++;
        }
        Collections.sort(fivo, Collections.reverseOrder());

        // 큰 값부터 빼며 0 만들기
        while (n != 0) {
            for (int i = 0; i < fivo.size(); i++) {
                if (fivo.get(i) <= n) {
                    n -= fivo.get(i);
                    ans.add(fivo.get(i));
                }
            }
        }

        Collections.sort(ans);
        ans.remove(0);
        return ans;
    }
}