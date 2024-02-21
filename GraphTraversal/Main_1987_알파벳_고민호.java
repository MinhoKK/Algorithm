package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1987_알파벳_고민호 {
	/**
	 * (개선 후)
	 * @author 고민호
	 * 백준_1987_알파벳
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 15,148KB		시간 : 828ms
	 * 
	 * 풀이방법
	 * 1. 개선전에는 DFS함수에서 파라미터로 지나온 알파벳을 가지는 리스트를 사용
	 * 2. if 조건으로 contains 함수를 사용해 리스트를 탐색 -> 시간이 오래걸린다는 단점 발생
	 * 3. 알파벳 26개와 같은 크기를 가지는 static 배열을 만들어 알파벳 사용여부를 확인하도록 개선
	 */
	
	static int R, C;
	static char[][] map;
	static int[] visit = new int[26];	// 알파벳 사용 여부, 지나기 전 = 0, 지난 후 = 1
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = parseInt(st.nextToken());
		C = parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String temp = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		
		visit[map[0][0] - 'A'] = 1;	// (0, 0) 사용 포시
		dfs(0, 0, 1);	// 첫 시작점 (0,0)과 지나온 칸의 수는 1부터 시작
		System.out.println(result);
	}

	static void dfs(int startR, int startC, int depth) {
		result = Math.max(result, depth);
		
		for(int d=0; d<4; d++) {
			int nr = startR + dr[d];
			int nc = startC + dc[d];
			// 탐색 좌표가 범위 내, 방문한적 없는 알파벳인 경우
			if(nr >= 0 && nr < R && nc >= 0 && nc < C && visit[map[nr][nc] - 'A'] == 0) {
				visit[map[nr][nc] - 'A'] = 1;	// 알파벳 방문 표시
				dfs(nr, nc, depth+1);	// 재귀
				visit[map[nr][nc] - 'A'] = 0;	// 알파벳 방문 표시 해제
			}
		}
	}
	
	/* 개선 전
	 * @author 고민호
	 * 백준_1987_알파벳
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 30,648KB		시간 : 4,476ms
	 * 
	 * 풀이방법
	 * 1. DFS를 통한 탐색, 이때 이전에 지난 알파벳이라면 return
	 * 2. return되지 않고 DFS가 종료된다면, 마지막으로 지난 알파벳 리스트에서 제거 -> 다른 방향으로 DFS시 영향을 주지 않기위해
	
	static int R, C;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = parseInt(st.nextToken());
		C = parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String temp = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		
		ArrayList<Character> list = new ArrayList<>();	// 지나온 알파벳 저장 리스트
		
		dfs(0, 0, list); 
		System.out.println(result);
	}

	static void dfs(int startR, int startC,  ArrayList<Character> list) {
		// 지나온 알파벳 확인
		for(char c : list) {
			if(c == map[startR][startC]) {	// 현재 좌표의 알파벳과 지나온 알파벳 중 하나가 일치하면 return
				return;
			}
		}
		
		// 위 조건 만족 X -> 새로운 알파벳
		list.add(map[startR][startC]);	// 리스트에 추가
		result = Math.max(result, list.size());	// 지나온 알파벳 갯수 갱신(최대값)
		
		// 4방탐색
		for(int d=0; d<4; d++) {
			int nr = startR + dr[d];
			int nc = startC + dc[d];
			// 탐색 좌표가 범위내인 경우 재귀
			if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
				dfs(nr, nc, list);
			}
		}
		
		// return되지 않고 종료 -> 이전 depth의 DFS로 돌아가 다른 방향으로 재귀
		// 이때 마지막에 지난 알파벳을 제거해줘야함!
		list.remove(list.size()-1);
	}
	*/
}
