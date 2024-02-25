package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_7569_토마토_고민호 {
	/**
	 * @author 고민호
	 * 백준_7569_토마토
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 128,928KB		시간 : 704ms
	 * 
	 * 풀이방법
	 * 1. 기본적인 BFS 문제로 접근, 그러나 3차원 배열에서의 BFS로 6방탐색(위 아래 상 하 좌 우) 실행
	 * 2. 창고 정보를 입력받을때 1(토마토)가 주어지면 Queue에 offer
	 */

	static class Node{
		int h;
		int r;
		int c;
		int day;
		
		public Node(int h, int r, int c, int day) {
			this.h = h;
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
	
	static int M, N, H;
	static int[][][] map;
	static Queue<Node> queue = new LinkedList<>();
	static int[] dr = {0, 0, -1, 1, 0, 0};	// 위 아래 상 하 좌 우
	static int[] dc = {0, 0, 0, 0, -1, 1};
	static int[] dh = {-1, 1, 0, 0, 0, 0};
	static int result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = parseInt(st.nextToken());
		N = parseInt(st.nextToken());
		H = parseInt(st.nextToken());
		
		map = new int[H][N][M];
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<M; k++) {
					map[i][j][k] = parseInt(st.nextToken());
					if(map[i][j][k] == 1) {	// 1인경우 Queue에 offer
						queue.offer(new Node(i, j, k, 0));
					}
				}
			}
		}
		
		bfs();
	
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(map[i][j][k] == 0) {	// BFS 종료 후 0이 있는 경우 -> 토마토가 모두 익지 못하는 상황
						System.out.println(-1);	// -1을 출력하고 종료
						return;
					}
				}
			}
		}
		
		System.out.println(result);	// 토마토가 모두 익은 경우, 모두 익는데 걸린 최소일 출력
	}	
	
	static void bfs() {
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<6; d++) {
				int nh = out.h + dh[d];
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				if(nh >= 0 && nh < H && nr >= 0 && nr < N && nc >= 0 && nc < M && map[nh][nr][nc] == 0) {	// 범위내, 익지않은 토마토인 경우
					map[nh][nr][nc] = 1;	// 익은 토마토로 바꿔주기
					queue.offer(new Node(nh, nr, nc, out.day+1));	// 새로 익은 토마토 Queue에 offer, 이때 새로 익은 토마토는 자신에게 영향을 준 토마토의 익은 날짜 +1
					result = out.day+1;	// 결과 갱신
				}
			}
		}
	}

}
