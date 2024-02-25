package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2206_벽부수고이동하기_고민호 {
	/**
	 * @author 고민호
	 * 백준_2206_벽부수고이동하기
	 * 난이도 G3
	 * 결과 : 통과		메모리 : 150,492KB		시간 : 808ms
	 * 
	 * 풀이방법 (지금까지 풀어본 그래프탐색 문제 중 가장 어려웠던 문제같음..)
	 * 1. 일반적인 BFS 문제와 비슷하게 4방탐색 및 visit 배열을 사용한다는 점이 동일하지만, 벽을 부수지 않은 상태에서의 방문배열 그리고 벽은 부순 상태에서의 방문배열 두가지를 사용
	 * 2. 노드 객체는 좌표 변수, 거리 변수, 벽을 부신 횟수 변수를 가진다.
	 * 3. 탐색 가능 조건은 탐색 좌표가 범위내이고, 0인지 1인지에 따라 달라진다.
	 * 3-1. 현재 Node 객체의 벽을 부신 횟수와 동일한 탐색 좌표의 방문 배열을 확인해야한다.
	 * 3-2. 0인경우 새로운 Node 객체는 이전 Node 객체의 거리+1, 벽을 부신 횟수는 동일하다
	 * 3-3. 1인경우 이전 Node객체의 벽을 부신 횟수가 0인 경우에만 탐색이 가능하며, 새로운 Node객체의 거리는 이전 객체의 거리+1, 벽을 부신 횟수+1이다.
	 */

	static class Node{
		int r;
		int c;
		int dis;
		int breakCount;
		
		public Node(int r, int c, int dis, int breakCount) {
			this.r = r;
			this.c = c;
			this.dis = dis;
			this.breakCount = breakCount;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[][][] visit;	// [][][0] = 벽을 부시지 않은 상태의 방문 배열,  [][][1] = 벽을 부순 상태의 방문 배열
	static int result = -1;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new int[N][M][2];
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		
		bfs(0, 0);
		
		System.out.println(result);
		
	}
	
	static void bfs(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(startR, startC, 1, 0));	// 시작 좌표, 시작 거리는 1, 벽을 부순 횟수 0
		visit[startR][startC][0] = 0;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			// 목적지에 도착한 경우
			if(out.r == N-1 && out.c == M-1) {
				result = out.dis;	// 출력값 = 현재까지의 거리
				return;
			}
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 탐색 좌표가 범위 내
				if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
					// 탐색 좌표가 빈공간(0)이며, 자신의 벽을 부신 횟수에 대응되는 방문배열을 확인하여 방문하지 않은 경우
					if(map[nr][nc] == 0 && visit[nr][nc][out.breakCount] == 0){
						visit[nr][nc][out.breakCount] = 1; // 방문 표시
						queue.offer(new Node(nr, nc, out.dis+1, out.breakCount));
					// 탐색 좌표가 빈공간(1)이면 현재 객체의 벽을 부순횟수는 0회여야 하고, 자신의 벽을 부신 횟수+1에 대응되는 (반드시 벽을 부신 상태의)방문배열을 확인하여 방문하지 않은 경우	
					} else if(map[nr][nc] == 1 && out.breakCount == 0 && visit[nr][nc][out.breakCount+1] == 0) {
						visit[nr][nc][out.breakCount+1] = 1;
						queue.offer(new Node(nr, nc, out.dis+1, out.breakCount+1));	// 거리와 벽을 부순횟수 +1
					}
				}
			}
		}
	}

}
