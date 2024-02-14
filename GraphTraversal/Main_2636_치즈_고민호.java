package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2636_치즈_고민호 {
	/**
	 * @author 고민호
	 * 백준_2636_치즈
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 16,156KB		시간 : 164ms
	 * 소요시간 : 70분
	 *
	 * 풀이방법
	 * 1. 치즈가 놓이지 않는 공간(가장자리)에서 BFS를 시작해 외부 공기는 2로 표시
	 * 2. 치즈(1)인 경우 4방탐색을 통해 외부공기(2)와 접촉한 경우 외부공기(2)로 변환
	 * 3. 외부공기(2)를 다시 0으로 바꾸고 1, 2을 반복해 치즈가 사라져 치즈속 공기가 외부로 나온것을 확인!
	 */
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;	// 세로, 가로
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};	//상하좌우 4방탐색
	static int[] dc = {0, 0, -1, 1};
	static int cheeseCount;	// 남아있는 치즈 개수
	static int time;	// 시간
	
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
			if(checkEnd()) break;
			time++;
			checkCheese();
			checkOutAir(0, 0);	// 치즈가 놓이지 않는 (0,0)은 반드시 외부공기
			removeCheese();
			changeZero();
		}
		
		
		System.out.println(time);
		System.out.println(cheeseCount);
	}
	
	/** 외부 공기 확인 메서드 
	 *  BFS를 통해 외부공기 2로 변환
	 */
	static void checkOutAir(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startR, startC));
		// 외부공기와 치즈 속 공기를 구분하기 위해 외부공기는 2
		map[startR][startC] = 2;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 외부 공기(0)와 내부 공기(0)을 구분하기 위해 외부 공기를 2로 바꾸기
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
					map[nr][nc] = 2;
					queue.add(new Node(nr, nc));
				}
			}
		}
	}
	
	/** 외부 공기와 접촉하는 치즈 제거 메서드*/
	static void removeCheese() {
		int[][] tempMap = new int[N][M];	// 치즈가 외부공기로 바뀌며 다음 탐색 좌표에 영향을 주지 않기 위해 임시 배열 생성
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tempMap[i][j] = map[i][j];	// 2차원배열 깊은 복사
				// 치즈인 경우
				if(map[i][j] == 1) {
					// 4방탐색으로 외부 공기(2)와 접촉하는지 확인
					for(int d=0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						// 외부공기와 접촉하는 경우 외부 공기로 바뀜!!
						if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 2) {
							tempMap[i][j] = 2;
							continue;
						}
					}
				}
			}
		}
		map = tempMap;
	}
	
	/** 외부 공기 0으로 변환 메서드
	 *  치즈가 사라져 치즈속 공기가 외부로 나온것을 확인하기 위해 CheckOutAir 메서드 재수행 필요
	 *	CheckOutAir 메서드를 다시 수행하기 위한 작업
	 */
	static void changeZero() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 2) {
					map[i][j] = 0;
				}
			}
		}
	}
	
	/** 치즈 개수 확인 메서드*/
	static void checkCheese() {
		cheeseCount = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					cheeseCount++;
				}
			}
		}
	}
	
	/** 종료 확인 메서드 */
	static boolean checkEnd() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
}
