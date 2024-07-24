# [Gold IV] Packing - 15188 

[문제 링크](https://www.acmicpc.net/problem/15188) 

### 성능 요약

메모리: 22432 KB, 시간: 1256 ms

### 분류

배낭 문제

### 제출 일자

2024년 7월 24일 17:09:56

### 문제 설명

<p>It was bound to happen. Modernisation has reached the North Pole. Faced with escalating costs for feeding Santa Claus and the reindeer, and serious difficulties with security, NP Management has decided to do away with the traditional sleigh and adopt delivery by drone (magic, superfast drone). Lack of investment capital means that the new system will start small, and hopefully grow in the years to come. For the first test run in 2017 there will be only two drones and they will have limited carrying capacity. PR is, of course, all important. There will be disappointment, and NP Management has decided to focus on delivering only the most expensive toys to the richest children, so as to focus the worst of the disappointment on those who have the greatest experience of coping (the poor). </p>

<p>Choosing the presents to deliver is your problem. You are being asked to develop an algorithm to select the cargo to deliver, given weight limits for each of the drones and a list of candidate presents with weights and values. Your goal is to maximise the value of gifts delivered. </p>

### 입력 

 <p>Input will consist of a series of problems. The first line of the input holds a single integer P being the number of problems. Then for each problem there will be three lines of input. The first line holds three integers: N (1 <= N <= 100) being the number of candidate presents; W<sub>1</sub> and W<sub>2</sub> (1 <= W<sub>1</sub>, W<sub>2</sub> <= 1000) being the weight limits of the two drones respectively. The second line holds N integers (1 <= w<sub>i</sub> <= 100) being the weights of each of the candidate presents and the third line holds N integers (1 <= v<sub>i</sub> <= 100) being the values of the presents (in thousand dollar units). All lines are formatted with single spaces between numbers and no leading or trailing spaces. </p>

### 출력 

 <p>For each problem your program should output one line with the text “Problem “ and the number of the problem (counting from 1) followed by a colon, a space and the total value of presents shipped by the drone pair. </p>

