import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 웹페이지의 종류의 수
        q = Integer.parseInt(st.nextToken()); // 사용자가 수행하는 작업의 개수

        List<Integer> back = new LinkedList<>(); // 뒤로 가기 공간
        List<Integer> front = new LinkedList<>(); // 앞으로 가기 공간
        List<Integer> tmp = new LinkedList<>();

        int cur = 0; // 현재 페이지

        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            if (s.equals("B")) { // 뒤로 가기
                if (back.size() != 0) {
                    front.add(0, cur);
                    cur = back.remove(back.size()-1);
                }
            } else if (s.equals("F")) { // 앞으로 가기
                if (front.size() != 0) {
                    back.add(cur);
                    cur = front.remove(0);
                }
            } else if (s.equals("C")) { // 압축
                if (back.size() == 0) continue;
                tmp = new LinkedList<>();
                tmp.add(back.get(0));
                
                for (int i = 1; i < back.size(); i++) {
                    if (!tmp.get(tmp.size()-1).equals(back.get(i))) {
                        tmp.add(back.get(i));
                    }
                }
                back = tmp;
            } else { // 웹 페이지 접속
                int i = Integer.parseInt(st.nextToken());
                front.clear();
                if (cur != 0) {
                    back.add(cur);
                }
                cur = i;
            }
        }
        sb.append(cur + "\n");
        if (back.size() == 0) {
            sb.append(-1);
        } else {
            for (int i = back.size()-1; i >= 0; i--) {
                sb.append(back.get(i) + " ");
            }
        }
        sb.append("\n");
        if (front.size() == 0) {
            sb.append(-1);
        } else {
            for (int i = 0; i < front.size(); i++) {
                sb.append(front.get(i) + " ");
            }
        }

        bw.append(sb);
        bw.flush();
        bw.close();
        br.close();
    }
}