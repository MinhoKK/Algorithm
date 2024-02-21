package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_13023_ABCDE_고민호  {
	/**
	 * @author 고민호
	 * 백준_13023_ABCDE
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 21,448KB		시간 : 316ms
	 * 
	 * 풀이방법
	 * 1. 정수 2개가 주어지면 각 리스트에 add하여 양뱡향 설정
	 * 2. dfs를 통해 depth = 4 인경우 종료
	 * 3. 고려해야할 점은 모든 노드에서 시작해 depth가 4가 되는지 확인하는데 어떤 노드에서 depth=4가 되어도 이후 노드에서
	 *    또 dfs를 한다면 시간초과 발생하기에 depth=4가 되면 이후 노드에서의 dfs는 실행하지 않고 종료해야됌!!!
	 */
	
	static int N, M;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static int[] visit;
	static boolean result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		visit = new int[N];
		
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = parseInt(st.nextToken());
			int b = parseInt(st.nextToken());
			list.get(a).add(b);	// 노드 간 양방향 연결
			list.get(b).add(a);	// 노드 간 양방향 연결
		}
		
		// 모든 노드에서 시작해보기
		for(int i=0; i<N; i++) {
			visit[i] = 1;
			dfs(0, i);
			visit[i] = 0;		// depth=4이면 result=true
			if(result) break;	// 단 어떤 노드에서 dfs를 시작해 depth=4이면 이후 노드는 dfs 실행 X
			
		}
		if(result)
			System.out.println(1);
		else
			System.out.println(0);
	}

	static void dfs(int depth, int start) {
		// 종료조건은 depth = 4
		if(depth == 4) {
			result = true;	// 이후 노드들의 dfs를 실행하지 않기 위해 true로 바꾸고 dfs 종료
			return;
		}
		
		// 해당 노드와 연결된 노드 전부 확인
		for(int value : list.get(start)) {
			if(visit[value] == 0) {	// 연결된 노드가 방문하지 않은 노드라면
				visit[value] = 1; // 방문 표시
				dfs(depth+1, value);	// depth를 높여 해당 노드와 연결된 노드를 탐색하기 위해 dfs 재귀
				visit[value] = 0; // 방문 표시 해제
			}
		}

	}
}
