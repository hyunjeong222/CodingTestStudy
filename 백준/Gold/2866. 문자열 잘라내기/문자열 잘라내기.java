import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 행
        c = Integer.parseInt(st.nextToken()); // 열

        arr = new char[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int start = 0;
        int end = r-1;

        while (start <= end) {
            int mid = start + (end-start) / 2;

            if (check(mid)) {
                start = mid+1;
            } else end = mid-1;
        }

        System.out.println(start);
    }

    private static boolean check(int mid) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < c; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = mid + 1; j < r; j++) {
                str.append(arr[j][i]);
            }
            if (set.contains(String.valueOf(str))) return false;
            else set.add(String.valueOf(str));
        }

        return true;
    }
}
