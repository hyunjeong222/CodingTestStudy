# [Silver I] 버섯 농장 - 27737 

[문제 링크](https://www.acmicpc.net/problem/27737) 

### 성능 요약

메모리: 13928 KB, 시간: 96 ms

### 분류

너비 우선 탐색, 깊이 우선 탐색, 그래프 이론, 그래프 탐색

### 제출 일자

2025년 2월 27일 20:22:46

### 문제 설명

<p>농부 해강이는 $N \times N$ 칸으로 이루어진 나무판에서 버섯 농사를 짓는다. 나무판은 버섯이 자랄 수 있는 칸과 없는 칸으로 이루어져 있다.</p>

<p>해강이는 $M$개의 버섯 포자를 가지고 있다. 버섯 포자는 버섯이 자랄 수 있는 칸에만 심을 수 있다.</p>

<p>각 버섯 포자는 포자가 심어진 칸을 포함해 최대 $K$개의 연결된 (버섯이 자랄 수 있는) 칸에 버섯을 자라게 한다. 이때 연결된 칸은 상하좌우로 적어도 한 변을 공유하는 칸들의 집합이라고 정의한다.</p>

<p>또한 한 칸에 버섯 포자를 여러 개 겹쳐서 심을 수 있으며, 만약 $x$개의 버섯 포자를 겹쳐 심으면 포자가 심어진 칸을 포함해 최대 $x \times K$개의 연결된 (버섯이 자랄 수 있는) 칸에 버섯이 자란다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/d6cf1de2-1a5a-4185-bb45-bc37eb4e4476/-/preview/" style="height: 454px; width: 700px;"></p>

<p style="text-align: center;"><그림 1> $K = 4$일 때 버섯이 자라는 모습이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/239f2d7b-7589-4f72-9088-993428eb234a/-/preview/" style="height: 443px; width: 700px;"></p>

<p style="text-align: center;"><그림 2> $K = 10$일 때 버섯이 자라는 모습이다.</p>

<p>해강이는 버섯 포자를 심을 때 최소 개수로만 심으려고 한다. 해강이가 농사가 가능할지 판단하고, 농사가 가능하다면 남은 버섯 포자의 개수를 출력하시오.</p>

<p><strong>버섯 포자를 하나라도 사용하고</strong> 버섯이 자랄 수 있는 모든 칸에 버섯이 전부 자랐을 때 농사가 가능하다고 정의한다.</p>

### 입력 

 <p>첫 번째 줄에 $N$, $M$, $K$가 공백으로 구분되어 주어진다.</p>

<p>두 번째 줄부터 $N$개의 줄에 나무판의 각 칸의 상태가 공백으로 구분되어 주어진다.</p>

<p>버섯이 자랄 수 <strong>있는</strong> 칸은 <span style="color:#e74c3c;"><code>0</code></span>, 버섯이 자랄 수 <strong>없는</strong> 칸은 <span style="color:#e74c3c;"><code>1</code></span>로 주어진다. </p>

### 출력 

 <p>만약 버섯 농사가 불가능하면 <span style="color:#e74c3c;"><code>IMPOSSIBLE</code></span>을 출력한다.</p>

<p>버섯 농사가 가능하다면, <span style="color:#e74c3c;"><code>POSSIBLE</code></span>을 출력하고 다음 줄에 남은 버섯 포자의 개수를 출력한다.</p>

