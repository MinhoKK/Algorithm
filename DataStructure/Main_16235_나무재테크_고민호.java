package DataStructure;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_16235_나무재테크_고민호 {
	/**
	 * @author 고민호
	 * 백준_16234_나무재테크
	 * 난이도 G3
	 * 결과 : 통과		메모리 : 295,264KB		시간 : 724ms
	 * 
	 * 풀이과정
	 * 1. ArrayList 자료구조를 사용, Collections.sort을 통한 정렬로 어린 나무 순을 만족
	 * 2. 테스트케이스는 통과하나 시간초과 발생
	 * 3. PriorityQueue을 고려했으나 마찬가지로 정렬을 하는 과정에서 시간초과 발생이 고려됌
	 * 4. 양방향에서 add, offer가 가능한 Deque 자료구조 사용
	 * 5. 나이 오름차순 정렬 -> 초기 정렬 및 번식된 나무는 addFirst(), 기존 나무는 poll()과 add()
	 * 
	 * 기억할 내용
	 * 1. 잦은 정렬 -> 시간 복잡도 상승!!!
	 * 2. 양방향 삽입, 삭제가 가능한 자료구조 Deque 기억하기!!
	 */
	
	static class Tree implements Comparable<Tree>{
		int r;
		int c;
		int age;
		
		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
		
		// 나이 오름차순 정렬
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	
	
	static int N, M, K;
	static int[][] yangbun;
	static int[][] add;
	static Deque<Tree> tree = new ArrayDeque<>();
	static ArrayList<Tree> deadTree = new ArrayList<>();
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());	// 지도 크기
		M = parseInt(st.nextToken());	// 초기 나무 수
		K = parseInt(st.nextToken());	// K년 후
		
		add = new int[N][N];
		yangbun = new int[N][N];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				add[i][j] = parseInt(st.nextToken());
				yangbun[i][j] = 5;
			}
		}
		
		ArrayList<Tree> temp = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = parseInt(st.nextToken()) - 1;
			int c = parseInt(st.nextToken()) - 1;
			int age = parseInt(st.nextToken());
			
			temp.add(new Tree(r, c, age));
		}
		
		// 초기에 입력되는 나무 나이 오름차순 정렬 필요
		Collections.sort(temp);
		for(Tree t : temp) {
			tree.add(t);
		}
		
		// K년 동안
		while(K > 0) {
			doSpring();
			doSummer();
			doAutumn();
			doWinter();
			K--;
		}
		
		System.out.println(tree.size());
	}
	
	static void doSpring() {
		// while 종료조건 만족을 위해 임시 Deque 생성
		Deque<Tree> tempTree = new ArrayDeque<Tree>();
		
		while(!tree.isEmpty()) {
			Tree nowTree = tree.poll();
			int r = nowTree.r;
			int c = nowTree.c;
			int age = nowTree.age;
			// 양분이 나이 이상인 경우
			if(yangbun[r][c] >= age) {
				yangbun[r][c] -= age;	// 나이만큼 양분 감소
				nowTree.age++;	// 나이 증가
				tempTree.add(nowTree);
			// 양분이 나이 미만인 경우
			} else {
				deadTree.add(nowTree);	// 죽은 나무 추가
			}
		}
		
		tree = tempTree;
	}
	
	static void doSummer() {
		for(int i=0; i<deadTree.size(); i++) {
			Tree dead = deadTree.get(i);
			yangbun[dead.r][dead.c] += dead.age / 2;
		}
		
		// 죽은 나무 초기화
		deadTree.clear();
	}
	
	static void doAutumn() {
		// while 종료조건 만족을 위해 임시 Deque 생성
		Deque<Tree> tempTree = new ArrayDeque<Tree>();
		
		while(!tree.isEmpty()) {
			Tree nowTree = tree.poll();
			if(nowTree.age % 5 == 0) {
				for(int d=0; d<8; d++) {
					int nr = nowTree.r + dr[d];
					int nc = nowTree.c + dc[d];
					if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
						// 번식(새로운) 나무의 나이는 1고정
						// 나이가 1보다 작은 나무 존재 X -> queue의 가장 맨 앞에 추가 -> 나이순 오름차순 정렬 만족
						tempTree.addFirst(new Tree(nr, nc, 1));
					}
				}
			}
			// 기존에 존재하던 나무는 맨뒤로
			// 초기에 나이순으로 정렬된 상태 -> poll(), add()를 하여도 정렬된 상태
			tempTree.add(nowTree);
		}
		
		tree = tempTree;
	}
	
	static void doWinter() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				yangbun[i][j] += add[i][j];
			}
		}
	}
}
