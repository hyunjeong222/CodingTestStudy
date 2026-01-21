import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Trie {
        String value;
        TreeMap<String, Trie> children = new TreeMap<>();

        // 알파벳 주어졌을 때 생성자
        public Trie(String value) {
            this.value = value;
        }

        // 기본 생성자
        public Trie() {}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 먹이의 정보 개수

        StringTokenizer st;

        // 트라이 구조 만들기
        Trie head = new Trie();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // 먹이 정보 개수
            Trie idxTrie = head;

            for (int j = 0; j < k; j++) {
                String food = st.nextToken();

                // 새로운 정점인 경우 하위에 추가
                if (!idxTrie.children.containsKey(food)) {
                    idxTrie.children.put(food, new Trie(food));
                }
                // 해당 value 정점으로 이동
                idxTrie = idxTrie.children.get(food);
            }
        }

        printTrie(head, 0, sb);

        System.out.println(sb.toString());

        br.close();
    }

    private static void printTrie(Trie node, int depth, StringBuilder sb) {
        for (String key : node.children.keySet()) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(key).append("\n");
            printTrie(node.children.get(key), depth+1, sb);
        }
    }
}