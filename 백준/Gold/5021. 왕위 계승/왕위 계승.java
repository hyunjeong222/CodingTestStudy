import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static HashMap<String, ArrayList<String>> familyTree; // 자식, 아버지/어머니
    static HashMap<String, Double> bloodTree; // 이름, 왕의 혈통 비율
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        String rootNode = br.readLine(); // 왕

        familyTree = new HashMap<>();
        bloodTree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String son = st.nextToken();

            if (familyTree.get(son) == null) {
                familyTree.put(son, new ArrayList<>());
            }

            String father = st.nextToken();
            familyTree.get(son).add(father);
            String mother = st.nextToken();
            familyTree.get(son).add(mother);

            bloodTree.put(son, -1d);
            bloodTree.put(father, -1d);
            bloodTree.put(mother, -1d);
        }

        bloodTree.put(rootNode, 1d);

        dfsAll(); // 모든 사람의 혈통 계산

        String successor = br.readLine();
        for (int i = 1; i < m; i++) {
            String competitor = br.readLine();

            if (bloodTree.getOrDefault(successor, 0d) < bloodTree.getOrDefault(competitor, 0d)) {
                successor = competitor;
            }
        }

        System.out.println(successor);

        br.close();
    }

    private static void dfsAll() {
        for (String name : bloodTree.keySet()) {
            dfs(name);
        }
    }

    private static Double dfs(String name) {
        if (bloodTree.get(name) != -1) { // 이미 계산된 경우
            return bloodTree.get(name);
        }

        // 부모가 존재하지 않는 왕족이 아닌 경우
        if (familyTree.get(name) == null) {
            bloodTree.put(name, 0d); // 혈통 0
            return bloodTree.get(name);
        }

        // 부모가 있는 경우
        double fatherBlood = dfs(familyTree.get(name).get(0));
        double motherBlood = dfs(familyTree.get(name).get(1));
        bloodTree.put(name, (fatherBlood+motherBlood)/2);

        return bloodTree.get(name);
    }
}