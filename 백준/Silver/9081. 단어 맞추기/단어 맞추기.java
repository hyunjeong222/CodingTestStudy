import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            char[] arr = br.readLine().toCharArray();
            int lastIdx = arr.length-1;
            int top = lastIdx;

            // 감소하는 위치 찾기
            while (top > 0 && arr[top] <= arr[top-1]) top--;

            if (top > 0) {
                // top 다음으로 큰 숫자 찾기
                // top 이후는 내림차순 되어있음
                int next = lastIdx;
                while (arr[top-1] >= arr[next]) next--;

                // top과 다음으로 큰 숫자 swap
                swap(arr, top-1, next);

                // top 이후 오름차순 정렬
                while (top < lastIdx) {
                    swap(arr, top, lastIdx);
                    top++;
                    lastIdx--;
                }
            }
            for (char c : arr) {
                sb.append(c);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void swap(char[] arr, int top, int next) {
        char tmp = arr[next];
        arr[next] = arr[top];
        arr[top] = tmp;
    }
}