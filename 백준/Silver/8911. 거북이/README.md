# [Silver III] 거북이 - 8911 

[문제 링크](https://www.acmicpc.net/problem/8911) 

### 성능 요약

메모리: 24868 KB, 시간: 308 ms

### 분류

구현, 시뮬레이션

### 제출 일자

2025년 7월 4일 12:02:49

### 문제 설명

<p><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/upload/images/turtle2(1).png" style="float:right; height:139px; width:183px">상근이는 2차원 평면 위에서 움직일 수 있는 거북이 로봇을 하나 가지고 있다. 거북이 로봇에게 내릴 수 있는 명령은 다음과 같이 네가지가 있다.</p>

<ol>
	<li>F: 한 눈금 앞으로</li>
	<li>B: 한 눈금 뒤로</li>
	<li>L: 왼쪽으로 90도 회전</li>
	<li>R: 오른쪽으로 90도 회전</li>
</ol>

<p>L과 R명령을 내렸을 때, 로봇은 이동하지 않고, 방향만 바꾼다. 명령을 나열한 것을 거북이 로봇의 컨트롤 프로그램이라고 한다.</p>

<p>상근이는 자신의 컨트롤 프로그램으로 거북이가 이동한 영역을 계산해보려고 한다. 거북이는 항상 x축과 y축에 평행한 방향으로만 이동한다. 거북이가 지나간 영역을 모두 포함할 수 있는 가장 작은 직사각형의 넓이를 구하는 프로그램을 작성하시오. 단, 직사각형의 모든 변은 x축이나 y축에 평행이어야 한다.</p>

<p>아래 그림에서 거북이는 가장 처음에 (0, 0)에 있고, 북쪽을 쳐다보고 있다. 컨트롤 프로그램이 FLFRFLBRBLB인 경우에 거북이는 아래와 같이 움직인다. 회색으로 빗금친 부분이 거북이가 지나간 영역을 모두 포함할 수 있는 가장 작은 직사각형이다. 넓이는 4가 된다.</p>

<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/upload/images/turtle.png" style="height:294px; width:304px"></p>

<p>거북이가 지나간 영역이 직사각형을 만들지 않는 경우도 있다. 예를 들어, FFLLFF인 경우에 거북이는 y축의 위로만 지나다닌다. 이 경우에 거북이가 지나간 영역을 모두 포함하는 직사각형은 선분이고, 선분은 한 변이 0인 직사각형으로 생각할 수 있다. 따라서, 선분의 경우에 넓이는 0이 된다.</p>

### 입력 

 <p>첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 컨트롤 프로그램이 주어진다. 프로그램은 항상 문제의 설명에 나와있는 네가지 명령으로만 이루어져 있고, 길이는 500을 넘지 않는다. </p>

### 출력 

 <p>각 테스트 케이스에 대해서, 거북이가 이동한 영역을 모두 포함하는 가장 작은 직사각형의 넓이를 출력한다.</p>

