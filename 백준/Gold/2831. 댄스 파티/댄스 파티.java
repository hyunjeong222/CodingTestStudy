import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 각각 여자 남자 수

        ArrayList<Integer> boy1 = new ArrayList<>();
        ArrayList<Integer> boy2 = new ArrayList<>();
        ArrayList<Integer> girl1 = new ArrayList<>();
        ArrayList<Integer> girl2 = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) boy1.add(num); // 자신보다 키가 큰 여자와 춤을 추기를 원하는 남자
            else boy2.add(num*-1); // 자신보다 키가 작은 사람과 춤을 추기를 원하는 남자
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) girl1.add(num); // 자신보다 키가 큰 여자와 춤을 추기를 원하는 여자
            else girl2.add(num*-1); // 자신보다 키가 작은 사람과 춤을 추기를 원하는 여자
        }

        Collections.sort(boy1);
        Collections.sort(boy2);
        Collections.sort(girl1);
        Collections.sort(girl2);

        int ans = pair(girl2, boy1) + pair(boy2, girl1);

        System.out.println(ans);

        br.close();
    }

    private static int pair(ArrayList<Integer> taller, ArrayList<Integer> shorter) {
        int result = 0;

        for (int i = 0, j = 0; i < taller.size() && j < shorter.size();) {
            int t = taller.get(i);
            int s = shorter.get(j);

            if (t <= s) {
                i++;
                continue;
            }

            i++;
            j++;
            result++;
        }

        return result;
    }
}