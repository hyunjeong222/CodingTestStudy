import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static Node head = new Node('A', null, null); // 트리는 A부터 시작
    static public class Node {
        char value;
        Node left; Node right;
        public Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left; this.right = right;
        }
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char root, left, right;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            root = st.nextToken().charAt(0);
            left = st.nextToken().charAt(0);
            right = st.nextToken().charAt(0);

            insertNode(head, root, left, right);
        }

        preOrder(head);
        sb.append("\n");
        inOrder(head);
        sb.append("\n");
        postOrder(head);

        System.out.println(sb.toString());
    }

    private static void preOrder(Node node) {
        if (node == null) return;

        sb.append(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        sb.append(node.value);
        inOrder(node.right);
    }

    private static void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value);
    }

    private static void insertNode(Node tmp, char root, char left, char right) {
        if (tmp.value == root) {
            tmp.left = (left == '.' ? null : new Node(left, null, null));
            tmp.right = (right == '.' ? null : new Node(right, null, null));
        } else {
            if (tmp.left != null) {
                insertNode(tmp.left, root, left, right);
            }
            if (tmp.right != null) {
                insertNode(tmp.right, root, left, right);
            }
        }
    }
}