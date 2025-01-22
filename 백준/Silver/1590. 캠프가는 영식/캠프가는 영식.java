import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        // 버스 출발 시간
        ArrayList<Long> busTime = new ArrayList<>();
        long s, l, c;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s = Long.parseLong(st.nextToken());
            l = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());

            for (int j = 0; j < c; j++) {
                busTime.add(s+(l*j));
            }
        }
        Collections.sort(busTime);

        long ans;
        if (busTime.get(0) >= t) ans = busTime.get(0)-t;
        else if (busTime.get(busTime.size()-1) < t) ans = -1;
        else {
            long start = 0;
            long end = busTime.size()-1;

            while (start < end) {
                long mid = start + (end-start) / 2;

                if (busTime.get((int)mid) == t) {
                    System.out.println(0);
                    return;
                } else if (busTime.get((int)mid) > t) end = mid;
                else start = mid+1;
            }

            ans = busTime.get((int)end) - t;
        }

        System.out.println(ans);
    }
}