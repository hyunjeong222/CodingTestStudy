import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Rank> list;
    static public class Rank implements Comparable<Rank> {
        int num;
        int gold; int silver; int bronze;
        int rank;
        public Rank (int num, int gold, int silver, int bronze, int rank) {
            this.num = num;
            this.gold = gold; this.silver = silver; this.bronze = bronze;
            this.rank = rank;
        }
        @Override
        public int compareTo(Rank o) {
            if (this.gold == o.gold) {
                if (this.silver == o.silver) {
                    return o.bronze - this.bronze;
                }
                return o.silver - this.silver;
            }
            return o.gold - this.gold;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        int num;
        int gold; int silver; int bronze;
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            gold = Integer.parseInt(st.nextToken());
            silver = Integer.parseInt(st.nextToken());
            bronze = Integer.parseInt(st.nextToken());

            list.add(new Rank(num, gold, silver, bronze, 0));
        }
        Collections.sort(list);

        list.get(0).rank = 1;
        int node = 0;
        for (int i = 1; i < list.size(); i++) {
            Rank now = list.get(i);

            int prevG = list.get(i-1).gold;
            int prevS = list.get(i-1).silver;
            int prevB = list.get(i-1).bronze;

            if (list.get(i).num == m) {
                node = i;
            }

            if (now.gold == prevG && now.silver == prevS && now.bronze == prevB) {
                list.get(i).rank = list.get(i-1).rank;
            } else list.get(i).rank = i+1;
        }

        System.out.println(list.get(node).rank);
    }
}