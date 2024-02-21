package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2573_빙산_고민호 {
	/**
	 * @author 고민호
	 * 백준_2573_빙산
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 298,320KB		시간 : 644ms
	 * 
	 * 풀이방법
	 * 1. 구현과 BFS 문제로 접근했습니다.
	 * 2. 2차원 배열을 탐색하며 빙산인 경우(값>0)인 경우 4방탐색을 통해 근처에 바다(값=0)의 개수를 카운트합니다.
	 * 3. 2번에서 카운트 한 수만큼 빙산의 값을 줄여줍니다.
	 * 4. 2차원 배열을 탐색하며 BFS를 통해 값>0 그리고 방문한적이 없다면 BFS를 실행해주며, BFS를 실행한 횟수가 빙산 덩어리 개수입니다.

	 */

	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[][] visit;
	static int[] dr = {-1, 1, 0 ,0};
	static int[] dc = {0, 0, -1, 1};
	static int time;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		
		while(true) {
			
			meltIce();
			
			visit = new int[N][M];
			int iceCount = 0;	// 빙상 덩어리 수
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					// 빙산 높이가 0보다 크고, 방문한적 없는 경우
					if(map[i][j] > 0 && visit[i][j] == 0) {
						countIce(i, j);	// BFS 메서드, 빙산덩어리 확인 메서드
						iceCount++;	// BFS 실행 횟수 = 빙상덩어리 수
					}
				}
			}
			
			time++; // 빙산이 녹으면 1초 지남
			
			// 빙산이 다 녹았지만 분리되지 않은 경우 0 출력 및 종료
			if(checkRemain() == 0) {
				System.out.println(0);
				return;
			}
			
			// 빙상 덩어리가 2개 이상인 경우
			if(iceCount >= 2) break;
		}
		System.out.println(time);
	}
	
	/** 얼음 녹이기 메서드 */
	static void meltIce() {
		// 임시 2차원 배열 깊은 복사
		// 실시간으로 녹은 빙산의 높이가 0이된다면 인접한 빙산에 영향을 주기에 임시 배열을 생성합니다.
		int[][] tempArr = new int[N][M];
		for(int i=0; i<N; i++) {
			tempArr[i] = map[i].clone();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] > 0) { // 빙산인 경우
					int count = 0;	// 근접한 바다 개수
					for(int d=0; d<4; d++) {	// 4방탐색
						int nr = i + dr[d];
						int nc = j + dc[d];
						// 탐색 좌표가 범위 내, 바다인 경우
						if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
							count++;	// 근접한 바다 개수 증가 
						}
					}
					tempArr[i][j] = tempArr[i][j] - count >= 0 ? tempArr[i][j] - count : 0;	// 빙산의 높이는 인접한 바다 수만큼 감소, 단 높이가 0보다 작아지면 0으로 고정
				}
			}
		}
		// 얼음 녹은 배열 적용
		map = tempArr;
	}
	
	/** BFS, 빙산 덩어리 탐색 */
	static void countIce(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startR, startC));
		visit[startR][startC] = 1;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			// 상하좌우 4방탐색
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 탐색좌표가 범위 내, 빙산이며 방문한적 없는 경우
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] > 0 && visit[nr][nc] == 0) {
					visit[nr][nc] = 1;
					queue.add(new Node(nr, nc));
				}
			}
		}
	}
	
	/** 남은 빙산 개수 확인 메서드 */
	static int checkRemain() {
		int remain = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 빙산인 경우
				if(map[i][j] > 0) {
					remain++;	// 빙산 개수 증가
				}
			}
		}
		return remain;
	}

}
