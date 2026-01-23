import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class TrieNode {
        String str;
        TreeMap<String, TrieNode> children = new TreeMap<>();

        public TrieNode (String str) {
            this.str = str;
        }

        public TrieNode() {}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());

        // 라이이 구조 만들기
        TrieNode head = new TrieNode();
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split("\\\\");
            TrieNode nowTrie = head;

            for (int j = 0; j < arr.length; j++) {
                if (!nowTrie.children.containsKey(arr[j])) {
                    nowTrie.children.put(arr[j], new TrieNode((arr[j])));
                }
                nowTrie = nowTrie.children.get(arr[j]);
            }
        }

        printTrie(head, 0, sb);

        System.out.println(sb.toString());

        br.close();
    }

    private static void printTrie(TrieNode node, int depth, StringBuilder sb) {
        for (String key : node.children.keySet()) {
            for (int i = 0; i < depth; i++) {
                sb.append(" ");
            }
            sb.append(key).append("\n");
            printTrie(node.children.get(key), depth+1, sb);
        }
    }
}