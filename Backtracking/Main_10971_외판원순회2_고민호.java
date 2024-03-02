package Backtracking;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_10971_외판원순회2_고민호 {
	/**
	 * @author 고민호
	 * 백준_10971_외판원순회2
	 * 난이도 S2
	 * 결과 : 통과		메모리 : 14644KB		시간 : 140ms
	 * 
	 * 풀이방법
	 * 1. 출발점을 바꿔가며 dfs하기
	 * 2. 현재도시와 다음도시의 비용이 0이 아닌 경우에만 dfs
	 * 3. 지금까지의 비용의 합이 최소비용보다 크면 return
	 */
	static int N;
	static int[][] arr;
	static int[] visit;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		arr = new int[N][N];
		visit = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = parseInt(st.nextToken());
			}
		}
		
		// 출발 도시를 바꿔가며 dfs
		for(int i=0; i<N; i++) {
			visit[i] = 1;
			dfs(0, i, i, 0);
			visit[i] = 0;
		}
		System.out.println(result);
		
	}
	
	static void dfs(int depth, int from, int start, int cost) {	// depth는 기저조건을 위해, from은 현재 도시, start는 첫 도시, cost는 비용의 합
		if(cost > result) return;	// 비용의 합이 최소 비용보다 커지는 경우 종료
		
		if(depth == N-1) {
			if(arr[from][start] != 0) {	// 마지막 도시에서 첫 도시로 돌아갈 수 있는 경우
				result = Math.min(result, cost + arr[from][start]);
			}
			return;
		}
		
		
		
		for(int i=0; i<N; i++) {
			// 방문하지 않고 현재 도시와의 비용이 0이 아닌 경우
			if(visit[i] == 0 && arr[from][i] != 0) {
				visit[i] = 1;	// 방문표시
				dfs(depth+1, i, start, cost + arr[from][i]);
				visit[i] = 0;	// 방문표시 해제
			}
		}
	}

}
