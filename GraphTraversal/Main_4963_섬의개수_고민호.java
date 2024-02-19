package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_4963_섬의개수_고민호 {
	/**
	 * @author 고민호
	 * 백준_4963_섬의개수
	 * 난이도 S2
	 * 결과 : 통과		메모리 : 16,136KB		시간 : 176ms
	 * 
	 * 풀이방법
	 * 1. 모든 좌표를 탐색하며 섬(1)이면서 방문하지 않은 경우 BFS 시작
	 * 2. BFS를 진행한 횟수 출력하기
	 */
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int W, H;	// 세로, 가로 길이
	static int[][] map;	// 지도 배열
	static int[][] visit;	// 방문 여무 배열
	static int count;	// BFS 실행 횟수
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};	// 8방 탐색
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			W = parseInt(st.nextToken());
			H = parseInt(st.nextToken());
			
			if(W == 0 && H == 0) return;	// 0 0이 입력되면 종료
			
			map = new int[H][W];
			visit = new int[H][W];
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = parseInt(st.nextToken());
				}
			}
			
			count = 0;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(visit[i][j] == 0 && map[i][j] == 1) {	// 섬이면서 방문하지 않은 경우
						bfs(i, j);	// 해당 좌표에서 BFS 실행
						count++;	// BFS 실행 횟수 +1
					}
				}
			}
			System.out.println(count);	// 총 BFS 실행 횟수(섬의 개수) 출력
		}

	}
	
	static void bfs(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startR, startC));
		visit[startR][startC] = 1;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<8; d++) {	// 8방 탐색
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 탐색한 좌표가 범위 내 방문하지 않고 섬(1)인 경우
				if(nr >= 0 && nr < H && nc >= 0 && nc < W && visit[nr][nc] == 0 && map[nr][nc] == 1) {
					queue.add(new Node(nr, nc));	// queue에 추가
					visit[nr][nc] = 1;	// 방문 표시하기
				}
			}
		}
	}

}
