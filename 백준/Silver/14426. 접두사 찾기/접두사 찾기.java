import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Trie {
        char alpha;
        Map<Character, Trie> children = new HashMap<>();

        // 알파벳 주어졌을 때 생성자
        public Trie(char alpha) {
            this.alpha = alpha;
        }
        // 기본 생성자
        public Trie() {

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 집합 S 문자열의 개수
        int m = Integer.parseInt(st.nextToken()); // 검사 문자열의 개수

        Trie head = new Trie();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            Trie idxTrie = head;

            for (int j = 0; j < word.length(); j++) {
                char alpha = word.charAt(j);

                // 새로운 정점인 경우 하위에 추가
                if (!idxTrie.children.containsKey(alpha)) {
                    idxTrie.children.put(alpha, new Trie(alpha));
                }
                // 해당 알파벳 정점으로 이동
                idxTrie = idxTrie.children.get(alpha);
            }
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            String word = br.readLine();
            Trie idxTrie = head;

            for (int j = 0; j < word.length(); j++) {
                char alpha = word.charAt(j);

                if (!idxTrie.children.containsKey(alpha)) break;
                idxTrie = idxTrie.children.get(alpha);
                if (j == word.length()-1) cnt++;
            }
        }

        System.out.println(cnt);

        br.close();
    }
}