import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 1부터 N까지의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n]; // 순열
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        if (nextPermutation()) {
            for (int a : arr) {
                sb.append(a).append(" ");
            }
        } else { // 사전순으로 마지막에 오는 순열
            sb.append(-1);
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static boolean nextPermutation() {
        int i = arr.length-1;

        // A[i-1] < A[i]를 만족하는 가장 큰 i를 찾기
        while (i > 0 && arr[i-1] >= arr[i]) {
            i -= 1;
        }

        // i의 위치가 0이면 내림차순 (마지막 순열)
        if (i <= 0) return false;

        // j >= i이면서 A[j] > A[i-1] 을 만족하는 가장 큰 j를 찾기
        int j = arr.length-1;
        while (arr[i-1] >= arr[j]) {
            j -= 1;
        }

        // A[i-1]과 A[j]를 swap
        int tmp = arr[j];
        arr[j] = arr[i-1];
        arr[i-1] = tmp;

        j = arr.length-1;
        // A[i]부터 순열 뒤집기
        while (i < j) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i += 1;
            j -= 1;
        }

        return true;
    }
}