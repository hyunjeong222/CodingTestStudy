import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, r, c;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 2N × 2N
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        int size = (int)Math.pow(2, n); // 한 변의 길이

        partition(0, 0, size); // 시작점, 한 변의 길이
        
        bw.append(ans + "\n");
        
        bw.flush();
        bw.close();
        br.close();
    }

    private static void partition(int x, int y, int size) {
        // 더 이상 쪼갤 수 없을 때 종료
        if (size == 1) return;

        int newSize = size/2; // 현재 정사각형을 4등분한 한 변의 길이
        int area = (size*size)/4; // 각 사분면이 차지하는 칸 개수
        
        // 1사분면 (좌상단): r < x+newSize, c < y+newSize
        if (r < x+newSize && c < y+newSize) {
            partition(x, y, newSize);
        }
        
        // 2사분면 (우상단)
        else if (r < x+newSize && c >= y+newSize) {
            ans += area; // 1사분면 개수만큼 더함
            partition(x, y + newSize, newSize);
        }
        
        // 3사분면 (좌하단)
        else if (r >= x + newSize && c < y + newSize) {
            ans += area * 2; // 1·2사분면 개수만큼 더함
            partition(x + newSize, y, newSize);
        }

        // 4사분면 (우하단)
        else {
            ans += area * 3; // 1·2·3 사분면 개수만큼 더함
            partition(x + newSize, y + newSize, newSize);
        }
    }
}