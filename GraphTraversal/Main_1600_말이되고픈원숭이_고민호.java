package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1600_말이되고픈원숭이_고민호 {
	/**
	 * @author 고민호
	 * 백준_1600_말이되고픈원숭이
	 * 결과 : 통과		메모리 : 96,756KB		시간 : 640ms
	 * 난이도 G3
	 * 
	 * 풀이방법
	 * (설명에서의 점프는 말처럼 이동을 말합니다.)
	 * 1. 문제를 보고 지난번에 풀었던 벽 부수고 이동하기(백준2206)와 매우 비슷한 문제라고 생각이 들었음
	 * 2. 방문 배열을 3차원으로 만들고 점프 안한 상태, 점프 1번한 상태, 점프 2번한 상태 ... 점프 K번한 상태와 같이 [H][W][K+1]만큼 선언
	 * 3. BFS를 할때 현재 객체의 점프한 횟수에 따라 이동 가능한 경우의 수가 나뉘어진다.
	 * 3-1. 점프횟수가 < K인 경우 = 상하좌우 이동, 말처럼 이동
	 * 3-2. 점프횟수가 K인 경우 = 상하좌우 이동
	 * 4. 말처럼 이동할 경우 현재 점프 횟수+1의 방문배열을 확인하여 0이라면 점프해서 이동, 상하좌우 이동할 경우 현재 점프횟수의 방문배열을 확인하여 이동
	 * 5. 가장 먼저 도착지점에 도착하는 객체의 time 변수 출력
	 */
	
	static class Node{
		int r;
		int c;
		int jump;	// 점프 횟수 = 말처럼 이동한 횟수
		int time;	// 시간
		
		public Node(int r, int c, int jump, int time) {
			this.r = r;
			this.c = c;
			this.jump = jump;
			this.time = time;
		}
	}

	static int K;
	static int W, H;
	static int[][] map;
	static int[][][] visit;	// 방문배열
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우 4방탐색
	static int[] dc = {0, 0, -1, 1};
	static int[] jr = {-1, -2, -2, -1, 1, 2, 2, 1};	// 말처럼 이동하는 8방탐색
	static int[] jc = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int result = -1;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = parseInt(st.nextToken());
		H = parseInt(st.nextToken());
		
		map = new int[H][W];
		visit = new int[H][W][K+1];	//[0] = 점프 안한 방문배열, [1] = 점프 1번한 방문배열... [K} = 점프 K번한 방문배열
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		
		bfs();
		System.out.println(result);
		
	}

	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		visit[0][0][0] = 1;	// 0,0에서는 점프 안한 상태로 [0][0][0] = 1 초기화
		queue.add(new Node(0, 0, 0, 0));
		
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			// 종료조건 = 도착지점 도착
			if(out.r == H-1 && out.c == W-1) {
				result = out.time;
				return;
			}
			// 점프횟수가 K보다 적은 경우 = 점프 가능
			if(out.jump < K) {
				// 4방 탐색
				for(int d=0; d<4; d++) {
					int nr = out.r + dr[d];
					int nc = out.c + dc[d];
					// 좌표 내, 현재 점프 횟수 방문 배열 확인 시 방문 X, 탐색 좌표가 평지인 경우
					if(nr >=0 && nr < H && nc >= 0 && nc < W && visit[nr][nc][out.jump] == 0 && map[nr][nc] == 0) {
						visit[nr][nc][out.jump] = 1;	// 방문 표시
						queue.add(new Node(nr, nc, out.jump, out.time+1));	// 시간 +1
					}
				}
				// 점프
				for(int d=0; d<8; d++) {
					int nr = out.r + jr[d];
					int nc = out.c + jc[d];
					// 좌표 내, 현재 점프 횟수+1 방문 배열 확인 시 방문 X, 탐색 좌표가 평지인 경우
					if(nr >=0 && nr < H && nc >= 0 && nc < W && visit[nr][nc][out.jump+1] == 0 && map[nr][nc] == 0) {
						visit[nr][nc][out.jump+1] = 1;	// 점프 횟수+1 방문 배열에 방문 표시 -> 점프를 했기때문에
						queue.add(new Node(nr, nc, out.jump+1, out.time+1));	// 점프횟수 +1, 시간+1
					}
				}
			} else {
				// 점프횟수가 K인 경우 = 점프 불가능, 상하좌우 이동만 가능
				for(int d=0; d<4; d++) {
					int nr = out.r + dr[d];
					int nc = out.c + dc[d];
					// 좌표 내, 현재 점프 횟수 방문 배열 확인 시 방문 X, 탐색 좌표가 평지인 경우
					if(nr >=0 && nr < H && nc >= 0 && nc < W && visit[nr][nc][out.jump] == 0 && map[nr][nc] == 0) {
						visit[nr][nc][out.jump] = 1;
						queue.add(new Node(nr, nc, out.jump, out.time+1));	// 시간 +1
					}
				}
			}
		}
	}
	
}
