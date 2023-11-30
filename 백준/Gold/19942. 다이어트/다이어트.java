import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] minNutrient;
    static int[][] nutrient;
    static int[] isSelected;
    static ArrayList<String> list;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        minNutrient = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minNutrient[i] = Integer.parseInt(st.nextToken());
        }

        nutrient = new int[n+1][5];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            isSelected = new int[n];
            dfs(0, i, 1);
        }

        if (ans == Integer.MAX_VALUE) bw.append(-1 + "\n");
        else {
            Collections.sort(list);
            bw.append(ans + "\n");
            bw.append(list.get(0) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int select, int cnt, int start) {
        if (select == cnt) {
            isCheck(select);
            return;
        }

        for (int i = start; i <= n; i++) {
            isSelected[select] = i;
            dfs(select+1, cnt, i+1);
        }
    }

    private static void isCheck(int select) {
        int[] sumNutrient = new int[4];
        int price = 0;

        for (int i = 0; i < select; i++) {
            for (int j = 0; j < 4; j++) {
                sumNutrient[j] += nutrient[isSelected[i]][j];
            }
            price += nutrient[isSelected[i]][4];
        }

        for (int i = 0; i < 4; i++) {
            if (minNutrient[i] > sumNutrient[i]) return;
        }

        if (ans >= price) {
            if (ans > price) list.clear();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < select; i++) {
                sb.append(isSelected[i]).append(" ");
            }
            list.add(sb.toString());

            ans = price;
        }
    }
}