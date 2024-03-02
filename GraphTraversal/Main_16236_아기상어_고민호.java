package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_16236_아기상어_고민호 {
	/**
	 * @author 고민호
	 * 백준_16236_고민호
	 * 난이도 G3
	 * 결과 : 통과		메모리 : 26,272KB		시간 : 228ms
	 * 
	 * 풀이방법
	 * 1. bfs를 통해 조건에 만족하는 먹을 수 있는 물고기 찾기
	 * 2. 이때 가장먼저 만난 물고기를 먹게되면, 가까운 물고기 중 가장 위 그리고 왼쪽 물고기 조건을 만족하지 못한다
	 * 3. 따라서 먹을 수 있는 물고기 리스트를 저장하고 이중에 가장 가까운 물고기, 가장 위, 가장 왼쪽 순으로 정렬하여 index 0인 물고기 먹기
	 * 4. 물고기 리스트의 크기가 0이면 먹을 수 있는 물고기가 없는 경우로 종료
	 * -> bfs를 진행하여 만나는 물고기를 바로 먹는 것이 아닌 리스트를 만들어 나중에 조건에 맞는것을 제거하는것에서 캐슬디펜스와 비슷하다고 느꼈음
	 */
	static class Node{
		int r;
		int c;
		int time;	// 해당 좌표까지 오는데 걸리는 시간
		int size;	// 상어 크기
		int count;	// 현재 크기에서 물고기를 먹은 횟수
		int dis;	// 상어와 물고기와의 거리
		
		public Node(int r, int c, int time, int size, int count, int dis) {
			this.r = r;
			this.c = c;
			this.time = time;
			this.size = size;
			this.count = count;
			this.dis = dis;
		}
	}
	
	static int N;
	static int[][] map;
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	static Node shark;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Node(i, j, 0, 2, 0, 0);	// 상어 객체
					map[i][j] = 0;	// 현재 상어의 위치도 돌아다닐 수 있도록 0으로 변경
				}
			}
		}
		
		while(true) {
			if(!bfs()) break;
		}
		System.out.println(shark.time);
	}
	
	static boolean bfs() {
		Queue<Node> queue = new LinkedList<>();
		int[][] visit = new int[N][N];
		
		queue.add(shark);
		visit[shark.r][shark.c] = 1;
		ArrayList<Node> canEat = new ArrayList<>();	// 먹을 수 있는 물고기 리스트
		
		shark.dis = 0;	// 현재 상어에서 물고기까지의 거리를 구하기 위해 0으로 초기화
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			// 상어의 크기보다 작고, 물고기인 경우
			if(map[out.r][out.c] < out.size && map[out.r][out.c] > 0) {
				canEat.add(out);	// 먹을 수 있는 물고기 리스트 추가
			}
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 범위내, 자신의 크기가 같거나 작고, 방문하지 않은 경우
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] <= out.size && visit[nr][nc] == 0) {
					visit[nr][nc] = 1;
					queue.add(new Node(nr, nc, out.time+1, out.size, out.count, out.dis+1));
				}
			}
		}
		
		if(canEat.size() > 0) {
			// 우선순위 정렬 -> 거리, 행, 열 순
			Collections.sort(canEat, (o1, o2) -> o1.dis != o2.dis ? o1.dis - o2.dis : (o1.r != o2.r ? o1.r - o2.r : o1.c - o2.c));
			Node eat = canEat.get(0);	// 리스트의 index 0이 조건에 맞는 물고기
			map[eat.r][eat.c] = 0;
			shark = eat;
			if(++shark.count == shark.size) {	// 현재 크기에서 물고기를 먹은 횟수가 크기와 동일해지는 경우
				shark.size++;	// 사이즈 증가
				shark.count = 0;	// 현재 크기에서 먹은 횟수 0 초기화
			}
			return true;
		}
		
		return false;	
	}
}
