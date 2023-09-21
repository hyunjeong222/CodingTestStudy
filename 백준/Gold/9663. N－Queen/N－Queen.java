import java.io.*;

public class Main {
    static int n; // 체스판 크기
    static int[] arr; // 각 열에 퀸이 들어갈 수 있는 위치
    static int count; // 경우의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        count = 0;

        nQueen(0); // 첫번째 열
        bw.append(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void nQueen(int depth) {
        if (depth == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[depth] = i;

            // 해당 열에서 i 번째 행에 놓을 수 있는지 검사
            if (Possibility(depth)) {
                nQueen(depth+1);
            }
        }
    }

    private static boolean Possibility(int col) {
        for (int i = 0; i < col; i++) {
            // 일직선상 체크
            if (arr[col] == arr[i]) return false;
            // 대각선 체크
            // 열의 차와 행의 차가 같은 경우 대각선에 놓여있음
            else if(Math.abs(col-i) == Math.abs(arr[col]-arr[i])) return false;
        }
        return true;
    }
}