import java.io.*;

public class Main {
    static int n;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine()); // 3의 거듭제곱, 전체 패턴 크기 NxN
        arr = new char[n][n]; // 별/공백을 채울 2차원 배열
        
        // 전체 영역을 대상으로 재귀 시작(처음에는 공백 구역 X)
        partition(0, 0, n, false);
        
        // 결과 출력
        for (char[] line : arr) {
            bw.write(line);
            bw.write("\n");
        }
        
        bw.flush();
        
        bw.close();
        br.close();
    }

    private static void partition(int x, int y, int size, boolean blank) {
        // blank == true라면 이 영역 전체가 공백 
        if (blank) { 
            for (int i = x; i < x+size; i++) {
                for (int j = y; j < y+size; j++) {
                    arr[i][j] = ' ';
                }
            }
            
            return;
        }

        // 더 이상 쪼갤 수 없는 최소 단위
        if (size == 1) {
            arr[x][y] = '*';
            return;
        }

        int newSize = size/3; // 한 블록(9개 중 하나)의 크기
        int idx = 0; // 9개 블록 중 몇 번째 블록인지 체크하기 위한 변수
        // 3x3 블록 순회 (총 9개 영역)
        for (int i = x; i < x+size; i+=newSize) {
            for (int j = y; j < y+size; j+=newSize) {
                idx++;
                // 5번째 블록(가운데 블록)은 항상 공백
                if (idx == 5) partition(i, j, newSize, true);
                else partition(i, j, newSize, false);
            }
        }
    }
}