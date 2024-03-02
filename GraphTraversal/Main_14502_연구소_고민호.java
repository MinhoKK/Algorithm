package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_14502_연구소_고민호 {
	/**
	 * @author 고민호
	 * 백준_14502_연구소
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 298,852KB		시간 : 864ms
	 * 
	 * 풀이방법
	 * 1. 재귀를 통해 0인 부분을 1로 바꾼다. 이때 재귀 종료조건은 1로 바꾼 횟수가 3회인 경우
	 * 2. 1번을 만족한 상태에서 bfs를 통해 2(바이러스)를 퍼트리기
	 * 3. 2차원 배열을 탐색하며 0의 개수 세기 그리고 현재까지의 최대 0의 개수와 비교하여 갱신하기
	 *
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
	static ArrayList<Node> viruses = new ArrayList<>();	// 바이러스 리스트
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result = Integer.MIN_VALUE;	// 최대 안전 영영 크기
	
	
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
				if(map[i][j] == 2) {	// 바이러스인 경우
					viruses.add(new Node(i, j));	// 바이러스 리스트에 저장
				}
			}
		}
		
		makeWall(0);
		System.out.println(result);
		
	}

	
	static void makeWall(int depth) {
		// 벽을 3번 세운 경우
		if(depth == 3) {
			expand();	// 바이러스 퍼트리기, 안전영역 세기
			return;	// 종료
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;	// 벽세우기
					makeWall(depth+1);	// 재귀(벽세우기 횟수 +1)
					map[i][j] = 0;	// 벽없애기
				}
			}
		}
	}
	
	static void expand() {
		int[][] tempMap = copyMap();
		int safeZone = 0;
		Queue<Node> queue = new LinkedList<>();
		// queue에 바이러스 add하기
		for(Node virus : viruses) {
			queue.add(virus);
		}
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 범위 내, 빈칸인 경우
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && tempMap[nr][nc] == 0) {
					tempMap[nr][nc] = 2;	// 바이러스로 바꾸기
					queue.add(new Node(nr, nc));	// queue에 새로운 바이러스 add
				}
			}
		}

		// 바이러스 퍼트리기가 끝난뒤 안전영역 세기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tempMap[i][j] == 0) {
					safeZone++;
				}
			}
		}
		
		result = Math.max(result, safeZone);	// 안정역역 최대값 갱신
	}
	
	static int[][] copyMap(){
		int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			temp[i] = map[i].clone();
		}
		
		return temp;
	}
}
