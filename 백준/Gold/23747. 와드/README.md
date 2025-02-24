# [Gold V] 와드 - 23747 

[문제 링크](https://www.acmicpc.net/problem/23747) 

### 성능 요약

메모리: 83760 KB, 시간: 520 ms

### 분류

너비 우선 탐색, 그래프 이론, 그래프 탐색, 구현, 시뮬레이션

### 제출 일자

2025년 2월 24일 19:07:07

### 문제 설명

<p>한별이는 출근하던 도중 <strong>이세계 대환장 버스</strong>에 치였다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/3abb77e5-a490-4e6c-8fed-89d292b258f9/-/preview/" style="width: 540px; max-width: 100%;"></p>

<p style="text-align: center;">그림 B.1: 이세계 대환장 버스</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/34aa7e72-2f8a-49bd-ae2d-633f460697a8/-/preview/" style="width: 540px; max-width: 100%;"></p>

<p style="text-align: center;">그림 B.2: 출근하는 한별이</p>

<p>올해 휴가를 전부 써 버려 당장 판교로 돌아가야 하는 한별이는 돌아가기 위한 방법을 어떻게든 찾아보기 위해 이세계를 돌아다녀 보려고 한다.</p>

<p>이세계는 $R\times C$의 격자로 되어 있다. 지금은 밤이어서 한별이는 자신이 위치한 칸 및 그 칸에서 위, 아래, 왼쪽 또는 오른쪽으로 인접한 칸만을 볼 수 있지만, 와드를 설치하면 조금 더 넓은 영역의 시야를 확보할 수 있다. 구체적으로는, 격자의 모든 칸은 각각 어떤 영역 하나에 속해 있는데, 와드를 놓으면 와드가 놓인 칸이 속한 영역에 있는 모든 칸을 볼 수 있게 된다.</p>

<p>한별이의 여행 기록이 주어질 때 한별이가 얼마나 넓은 시야를 확보했을지 계산해 보자.</p>

### 입력 

 <p>첫 번째 줄에는 격자의 크기를 나타내는 두 정수 $R$과 $C$가 주어진다. ($1 \le R,C \le 1\,000$)</p>

<p>다음 줄부터 $R$개의 줄에 걸쳐 격자의 정보가 주어진다. 각 줄은 $C$개의 알파벳 소문자로 이루어져 있으며, 위, 아래, 왼쪽 또는 오른쪽으로 인접해 있는 칸이 같은 문자라는 것은 두 칸이 같은 영역에 속해 있음을 의미한다.</p>

<p>다음 줄에는 한별이가 이세계에 떨어진 위치를 나타내는 두 정수 $H_R$, $H_C$가 주어진다. 이는 한별이가 위에서 $H_R$번째 줄, 왼쪽에서 $H_C$번째 칸에 떨어졌음을 의미한다. ($1 \le H_R \le R$, $1 \le H_C \le C$)</p>

<p>마지막 줄에는 한별이의 여행 기록을 나타내는 $200\,000$글자 이하의 문자열이 주어진다. 여행 기록의 각 문자는 <code><span style="color:#e74c3c;">U</span></code>, <code><span style="color:#e74c3c;">D</span></code>, <code><span style="color:#e74c3c;">L</span></code>, <code><span style="color:#e74c3c;">R</span></code>, <code><span style="color:#e74c3c;">W</span></code> 중 하나로 이루어져 있으며, <code><span style="color:#e74c3c;">U</span></code>, <code><span style="color:#e74c3c;">D</span></code>, <code><span style="color:#e74c3c;">L</span></code>, <code><span style="color:#e74c3c;">R</span></code>은 각각 위, 아래, 왼쪽, 오른쪽으로 한 칸 이동했다는 뜻이고, <code><span style="color:#e74c3c;">W</span></code>는 지금 있는 칸에 와드를 설치했다는 뜻이다. 한별이가 격자를 벗어나는 경우는 주어지지 않는다.</p>

### 출력 

 <p>$R$개의 줄에 걸쳐 한별이의 시야를 출력한다. 각 줄은 $C$개의 문자로 되어 있어야 하며, $R$번째 줄 $C$번째 문자가 <span style="color:#e74c3c;"><code>.</code></span>이라면 한별이의 시야에 해당 칸이 들어와 있다는 뜻이고 <span style="color:#e74c3c;"><code>#</code></span>이라면 그렇지 않다는 뜻이다.</p>

