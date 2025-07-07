import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<String>[] que = new LinkedList[n]; // 각 앵무새가 말한 문장
        for (int i = 0; i < n; i++) {
            que[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                que[i].offer(str[j]);
            }
        }

        Queue<String> l = new LinkedList<>(); // 받아 적은 문장
        String[] lArr = br.readLine().split(" ");
        for (int i = 0; i < lArr.length; i++) {
            l.offer(lArr[i]);
        }

        while (!l.isEmpty()) {
            String now = l.poll();
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (now.equals(que[i].peek())) {
                    que[i].poll();
                    found = true;
                }
            }
            if (!found) { // 모든 앵무새의 말을 들었는데, 일치하는게 없는 경우
                System.out.println("Impossible");
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            while (!que[i].isEmpty()) { // 받아적은 문장의 단어의 수와 N개의 앵무새가 말한 단어의 수가 같아야 함
                System.out.println("Impossible");
                return;
            }
        }

        System.out.println("Possible");

        br.close();
    }
}