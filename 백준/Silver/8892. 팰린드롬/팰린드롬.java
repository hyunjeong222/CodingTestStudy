import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        String[] arr;
        while (t --> 0) {
            int k = Integer.parseInt(br.readLine()); // 단어의 수
            arr = new String[k];
            for (int i = 0; i < k; i++) {
                arr[i] = br.readLine();
            }

            boolean flag = false;
            loop : for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if (i == j) continue;

                    String str = arr[i]+arr[j];
                    if (isPalind(str)) {
                        flag = true;
                        sb.append(str).append("\n");
                        break loop;
                    }
                }
            }
            if (!flag) sb.append(0).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static boolean isPalind(String s) {
        int left = 0;
        int right = s.length()-1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;

            left++;
            right--;
        }
        return true;
    }
}