package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2146_다리만들기_고민호 {
	/**
	 * @author 고민호
	 * 백준_2146_다리만들기
	 * 난이도 G3
	 * 결과 : 통과		메모리 : 296,640KB		시간 : 384ms
	 * 
	 * 풀이방법
	 * 1. 2차원 배열을 탐색하며 1(섬)인경우 BFS를 통해 visit 배열에 같은 섬을 1로 표시
	 * 2. 시작 좌표에서 BFS를 하며 visit 배열이 0인곳을 탐색
	 * 3. 탐색 좌표가 0(바다)가 아닌 1(섬)이 나오면 다른 섬으로 생각하고 dis 변수가 섬에서 섬까지의 거리
	 * 4. 섬과 섬사이의 거리와 지금까지의 최소 거리를 비교하여 갱신
	 * 
	 * 개선한 방법
	 * 기존에는 1(섬)일때마다 visit 배열을 초기화하고 BFS를 진행해 같은 섬을 표시 -> 너무 많은 BFS 연산
	 * 따라서 static 변수로 섬의 번호를 가지고 2차원 배열을 한번 순회하면서 1인경우 BFS를 통해 섬의 변호로 바꾸기
	 * 이때 BFS가 종료되면 하나의 섬을 모두 확인했다는 의미로 섬의 번호 +1
	 * 
	 * 문제 포인트
	 * 최소 길이 다리를 구하기 전 2차원 배열을 탐색하며 BFS를 통해 하나의 덩어리(섬)들에게 덩어리를 구분할 수 있는 번호를 부여!
	 */
	
	static class Node {
		int r;
		int c;
		int dis;
		
		public Node(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}
	}

	static int N;
	static int[][] map;
	static int[][] visit;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result = Integer.MAX_VALUE;
	static int landNum = 2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					checkSame(i, j);
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] >= 2) {
					visit = new int[N][N];
					findOther(i, j);
				}
			}
		}
		
		System.out.println(result);
	}

	
	static void checkSame(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startR, startC, 0));
		
		map[startR][startC] = landNum;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 1) {
					map[nr][nc] = landNum;
					queue.add(new Node(nr, nc, 0));
				}
			}
		}
		
		landNum++;
	}
	
	static void findOther(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startR, startC, 0));
		int nowNum = map[startR][startC];
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != nowNum && visit[nr][nc] == 0) {
					if(map[nr][nc] >= 2) {
						result = Math.min(result, out.dis);
						return;
					}
					visit[nr][nc] = 1;
					queue.add(new Node(nr, nc, out.dis+1));
				}
			}
		}
	}
}
