package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;


public class Main_17135_캐슬디펜스_고민호 {
	/**
	 * @author 고민호
	 * 백준_17135_캐슬디펜스
	 * 난이도 G3
	 * 결과 : 통과		메모리 : 36,772KB		시간 : 264ms
	 * 소요시간 : 약 90분
	 * 
	 * 풀이방법
	 * 1. 주어진 N보다 크기가 1이 더 큰 2차원 배열 만들기 -> 성 칸
	 * 2. 궁수가 존재할 수 있는 칸 조합 만들기(재귀)
	 * 3. 조합이 완성되면 BFS를 통해 죽일 수 있는 적 찾기
	 * 4. 적을 죽일 수 있다면 죽이고 한칸 씩 아래로 이동시키기
	 * 5. 3,4 번 과정이 끝나면 day+1
	 * 6. map 2차원 배열을 완전 탐색하며 적이 없다면 종료, 존재한다면 3,4,5 반복
	 * 
	 * 문제점
	 * 1-1. 같은 적을 공격하는 경우를 고려하지 못함
	 * 1-2. A 궁수가 해당 적을 죽이면 B 궁수도 해당 적을 죽어야 하지만 이미 죽었음을(0) 확인하고 같은 거리에 다른 적을 죽이는 경우 발생
	 * 
	 * 2-1. 조합마다 임시 지도 2차원 배열을 만들어 임시 배열에서 적을 죽이고, 적을 이동시켜야 다음 조합에서 기존 지도를 사용할 수 있음
	 * 2-2. 위의 조건을 고려하지 못해 시간이 오래 결렸습니다.
	 */
	
	static class Node{
		int r;	// 행
		int c;	// 열
		int dis;	// 궁수로부터 위치
		
		public Node(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}
	}
	
	static int N, M, D;	// 행, 열, 거리 제한
	static int[][] map;
	static int[][] tempmap;
	static int[] dr = {0, -1, 0}; // 왼쪽, 위, 오른쪽 -> 같은 거리에 죽일 수 있는 적이 여러명이라면 가장 왼쪽부터 조건 충족
	static int[] dc = {-1, 0, 1};
	static int[] comb;
	static int killCount;
	static int result = 0; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		D = parseInt(st.nextToken());
		
		map = new int[N+1][M];	// 성이 존재하는 행 추가
		comb = new int[3];	// 궁수 위치 조합(배열 크기는 가로크기)
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parseInt(st.nextToken());	// 0 = 빈칸, 1 = 적
			}
		}
		
		for(int i=0; i<M; i++) {
			map[N][i] = 2;	// 2 = 성
		}
		
		makeComb(0, 0);
		
		System.out.println(result);
		
	}
	
	/** 궁수 위치 조합 생성 메서드 */
	static void makeComb(int depth, int start) {
		// 조합이 완성된 경우(궁수는 3명 고정)
		if(depth == 3) {
			killCount = 0;	// 조합 별 죽이는 적을 세기위해 0으로 초기화
			bfs();
			
			
			result = Math.max(result, killCount);	// 해당 조합으로 죽인 적의 수와 현재까지 가장 많이 죽인 적의 수 비교
			return;
		}
		
		// 조합 만들기
		for(int i=start; i<M; i++) {
			comb[depth] = i;
			makeComb(depth+1, i+1);
		}
	}

	static void bfs() {
		int day = 0;
		
		// 임시 2차원 배열 생성
		tempmap = new int[N+1][M];
		for(int i=0; i<=N; i++) {
			tempmap[i] = map[i].clone();
		}
		
		
		while(true) {
			int enemyCount = 0;
			
			// 적의 수 세기
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(tempmap[i][j] == 1) enemyCount++;
				}
			}
			
			// 지도에 적이 없는 경우 종료
			if(enemyCount == 0) {
				return;
			}
			
			kill();
			move();
			
			day++;
		}
	}
	
	/** 적 죽이기 메서드 */
	static void kill() {
		ArrayList<Node> dead = new ArrayList<>();
		for(int bowIndex : comb) {	// 조합 내 존재하는 궁수 별로 적 죽이기
			Queue<Node> queue = new LinkedList<>();
			queue.add(new Node(N, bowIndex, 0));	// 궁수의 행 위치는 맨 아래(N), 거리 0 고정
			
			int distance = 0;
			exit :
			while(!queue.isEmpty()) {
				Node out = queue.poll();
				for(int d=0; d<3; d++) {
					int nr = out.r + dr[d];
					int nc = out.c + dc[d];
					// 지도 내 죽일 수 있는 적이 존재하는 경우
					if(nr >= 0 && nr < N && nc >= 0 && nc < M && tempmap[nr][nc] == 1) {
						dead.add(new Node(nr, nc, out.dis));	// 바로 죽이지 않고 임시 배열에 담아두기 -> 같은 적을 동시에 죽일 수 있기 때문
						break exit;
					}
					// 성을 제외한 범위 내, 빈 공간, 화살 거리 제한 이내인 경우
					if(nr >= 0 && nr < N && nc >= 0 && nc < M && tempmap[nr][nc] == 0 && out.dis+1 < D) {	
						queue.add(new Node(nr, nc, out.dis+1));
						distance = out.dis+1;	// 화살 거리 1 증가
					}
				}
			}
			
		}
		// 죽일 수 있는 적 동시에 죽이기
		for(Node node : dead) {
			if(tempmap[node.r][node.c] == 1) {	// 적인 경우, dead 리스트에 같은 좌표의 적이 존재하는 경우 앞에 궁수가 죽였다면 해당 조건을 만족못해 죽인 수 증가 X
				tempmap[node.r][node.c] = 0;	// 죽이기
				killCount++;	// 죽인 적의 수 +1 증가
			}
		}
	}
	
	/** 적 이동 메서드 */
	static void move() {
		// 한 행씩 내리기
		for(int i=N-1; i>0; i--) {
			tempmap[i] = tempmap[i-1].clone();
		}
		
		// 가장 윗행은 0으로 초기화
		for(int i=0; i<M; i++) {
			tempmap[0][i] = 0;
		}
	}
}
