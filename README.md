# 코딩 테스트 스터디

## 방식

### 진행 방식

1. 문제는 매일 한 명씩 돌아가면서 선정합니다.
    - 이름 기준으로 로테이션 됩니다.
    - 문제는 선정자 자유며, 백준 및 프로그래머스 모두 가능합니다.
      - 백준 : [링크](https://github.com/tony9402/baekjoon) 참고
      - 프로그래머스 : [링크](https://school.programmers.co.kr/learn/challenges?tab=algorithm_practice_kit) 참고
2. 정해진 시간(평일 오후 8시)에 모여서 디스코드 혹은 슬랙으로 문제 풀이 과정 발표합니다.
3. 모든 참여자가 발표를 진행합니다.
4. 블로그 참고는 가능하지만 발표 시 설명할 수 있어야 합니다.

### 문제 풀이 업로드 방식

- 본인 브랜치로 이동합니다.
  - 브랜치 이름은 패키지 네임과 동일합니다.

```bash
# 예) git checkout ka
git checkout {브랜치이름}
```

- 문제 풀이 파일을 생성합니다.
  - 패키징 및 파일 네이밍은 아래 예시에 맞춰서 만들어주세요!

```bash
# 백준 문자열 문제(27866)일 경우
/src/ka/string/BOJ_27866.java

# 프로그래머스 컬렉션(List, Set, Queue, Map, Deque, Stack) 문제(92341)일 경우
# 프로그래머스 문제 번호는 페이지 URL에 있습니다!
/src/ka/collection/PRG_92341.java
```

- `JavaDoc`을 이용해 클래스 단에 문제이름(난이도), 시간, 메모리, 링크를 기입해주세요!
- 프로그래머스 시간, 메모리는 **제출 후 채점하기**를 눌러서 나오는 **가장 마지막 테스트 케이스**를 기준으로 입력해주세요!

```java
/**
 * 문제 이름(난이도) : 두 수의 합(LV0)
 * 시간 : 0.02ms
 * 메모리: 77MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/120802
 * */
public class Main {
    
    public static int solution(int a, int b){
        return a + b;
    }
    
    public static void main(String[] args) {
        System.out.println(solution(1, 2) == 3);
    }
}
```

- 코드 작성이 완료되면 `commit`, `push`를 진행해주세요!

```bash
git add .

# 백준일 경우 브(BRO), 실(SIL), 골(GOL) 앞 3글자만 작성
git commit -m "[BOJ][BRO] - 단어 길이 재기"
# 프로그래머스일 경우 문제 레벨 작성
git commit -m "[PRG][LV2] - 두 수의 합"

# 예) git push origin ka
git push origin {브랜치이름}
```