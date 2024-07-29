import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ySum = 0; int mSum = 0;
        for (int i = 0; i < n; i++) {
            int sec = Integer.parseInt(st.nextToken());
            ySum += ((sec/30)+1)*10;
            mSum += ((sec/60)+1)*15;
        }

        if (ySum == mSum) {
            System.out.println("Y M " + ySum);
        } else if (ySum > mSum) {
            System.out.println("M " + mSum);
        } else {
            System.out.println("Y " + ySum);
        }
    }
}