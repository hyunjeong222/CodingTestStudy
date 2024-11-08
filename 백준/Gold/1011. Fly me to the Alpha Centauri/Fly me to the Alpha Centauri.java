import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int x, y;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int distance = y-x;
            int sqrtDist = (int)Math.sqrt(distance);

            if (distance == sqrtDist*sqrtDist) { // case1
                sb.append(2*sqrtDist-1).append("\n");
            }
            else if (distance <= sqrtDist*sqrtDist+sqrtDist) { // case2
                sb.append(2*sqrtDist).append("\n");
            }
            else { // case3
                sb.append(2*sqrtDist+1).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}