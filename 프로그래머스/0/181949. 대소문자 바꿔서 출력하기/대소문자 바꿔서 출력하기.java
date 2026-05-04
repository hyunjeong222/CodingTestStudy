import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        
        char[] arr = a.toCharArray();
        for (char c : arr) {
            if (c >= 97 && c <= 122) { // 소문자
                sb.append(Character.toUpperCase(c));
            } else { // 대문자
                sb.append(Character.toLowerCase(c));
            }
        }

        System.out.println(sb.toString());
    }
}