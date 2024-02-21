package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_14940_쉬운최단거리_고민호 {
	/**
	 * @author 고민호
	 * 백준_14940_쉬운최단거리
	 * 난이도 S1
	 * 결과 : 통과		메모리 : 75,564KB		시간 : 2,416ms
	 * 
	 * 풀이방법
	 * 1. 시작위치 2에서 BFS를 통해 갈 수 있는 땅를 찾는다
	 * 2. Node class는 좌표 변수 r,c 그리고 시작위치로부터의 거리 dis 변수를 가지도록 설계
	 * 3. dis 변수는 자신에게 영향을 준 Node class의 dis + 1
	 * 4. BFS가 종료되면 출력 조건, 땅이었지만 도달할 수 없는 위치 -1을 위해 결과 배열과 초기 배열 비교 
	 */

	static class Node{
		int r;
		int c;
		int dis;
		
		public Node(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}
	}
	
	static int N, M;
	static int[][] map;	// 지도 배열
	static int[][] visit;	// 방문 배열
	static int[][] result;	// 결과(목표지점까지의 거리) 배열
	static Queue<Node> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우 4방탐색
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new int[N][M];
		result = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parseInt(st.nextToken());
				if(map[i][j] == 2) {	// 목표지점인 경우
					queue.add(new Node(i, j, 0));	// Queue에 담기
					visit[i][j] = 1;	// 방문 표시하기
				}
			}
		}
		
		bfs();
		
		// 지도에서는 방문가능(1)이지만 목표지점에서 도달할 수 없는 땅인 곳(result 배열에서 0) 찾기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1 && result[i][j] == 0) {
					result[i][j] = -1;
				}
			}
		}
		
		// 출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
	}

	static void bfs() {
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			result[out.r][out.c] = out.dis;
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 탐색 좌표가 범위 내, 땅(1)인 경우
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1 && visit[nr][nc] == 0) {
					queue.add(new Node(nr, nc, out.dis+1));	// Queue에 담기, 이때 dis변수는 이전 좌표 Node의 dis +1
					visit[nr][nc] = 1;	// 방문 표시
				}
			}
			
		}
	}
}
