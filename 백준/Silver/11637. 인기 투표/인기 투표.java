import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());
            int sum = 0;
            int maxVal = 0;
            int maxCount = 0;
            int idx = 0;
            for (int i = 1; i <= n; i++) {
                int now = Integer.parseInt(br.readLine());
                sum += now;

                if (maxVal < now) {
                    maxVal = now;
                    idx = i;
                    maxCount = 1;
                } else if (maxVal == now) {
                    maxCount++;
                }
            }
            if(maxCount >= 2) {
                bw.write("no winner\n");
                continue;
            }
            if(maxVal <= sum/2) {
                bw.write("minority winner ");
            } else {
                bw.write("majority winner ");
            }
            bw.write(idx + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}