import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st; //일단 미리 선언(유저 닉네임 입력받을 때 사용)
        String userA, userB; //유저 이름 잠시 입력받을 공간

        HashSet<String> enterUser = new HashSet(); //춤을 추고 있는 모든 유저의 닉네임 저장(중복값 저장X)
        enterUser.add("ChongChong"); //제일 먼저 추고 있는 총총이(처음엔 혼자 추고 있음!)

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            userA = st.nextToken();
            userB = st.nextToken();

            ////만약 춤을 추고 있는 사람 중에 포함하고 있으면 지금 들어온 사람은 모두 춤을 춰야함
            if(enterUser.contains(userA) || enterUser.contains(userB)){
                enterUser.add(userA);
                enterUser.add(userB);
            }
        }
        br.close();

        bw.write(enterUser.size() + "\n");
        bw.flush();
        bw.close();
    }

}