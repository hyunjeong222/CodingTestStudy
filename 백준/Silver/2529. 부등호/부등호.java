import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static char[] symbol;
    static boolean[] checekd;
    static ArrayList<String> list = new ArrayList<>(); // 만들어진 숫자를 담을 list
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        k = Integer.parseInt(br.readLine()); // 부등호 문자의 개수

        symbol = new char[k]; // 부등호를 담을 배열
        checekd = new boolean[10]; // 0-9까지 선택한 숫자를 체크할 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            symbol[i] = st.nextToken().charAt(0);
        }

        dfs(0, "");
        Collections.sort(list);
        bw.append(list.get(list.size()-1) + "\n");
        bw.append(list.get(0));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int idx, String num) {
        if (idx == k+1) {
            list.add(num);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!checekd[i]) {
                if (idx == 0 || checkNum(Character.getNumericValue(num.charAt(idx-1)), i, symbol[idx-1])) {
                    checekd[i] = true;
                    dfs(idx+1, num+i);
                    checekd[i] = false;
                }
            }
        }
    }

    private static boolean checkNum(int a, int b, char c) {
        if (c == '>') {
            if (a < b) return false;
        } else if (c == '<') {
            if (a > b) return false;
        }
        return true;
    }
}