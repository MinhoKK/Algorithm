package Simulation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_14891_톱니바퀴_고민호 {
	/**
	 * @author 고민호
	 * 백준_14891_톱니바퀴
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 14,768KB		시간 : 128ms
	 * 
	 * 풀이방법
	 * 1. 회전시킬 톱니 바퀴 번호에 따라 현재 톱니바퀴의 왼쪽, 오른쪽 확인 여부를 다르게 구현
	 * 2. 1번 톱니바퀴인 경우 오른쪽 톱니바퀴 확인, 2 3번 톱니바퀴인 경우 양쪽 톱니바퀴 확인, 4번 톱니바퀴인 경우 왼쪽 톱니바퀴 확인
	 * 3. 이때 맞닿아 있는 부분의 값이 다르고, 회전시킨적 없는 톱니바퀴라면 회전시키기
	 * 
	 * <핵심 포인트>
	 * 맞닿아 있는 톱니바퀴를 회전 시킬지에 대한 조건!!
	 * 문제에서 주어진 극이 다른 경우를 포함해, 회전을 한 톱니바퀴인지 아니지를 확인 해줘야한다. -> visit 배열 사용
	 */

	static int K;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static int[] visit;	// 톱니바퀴 회전 여부
	static int result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<4; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<4; i++) {
			String temp = br.readLine();
			for(int j=0; j<temp.length(); j++) {
				list.get(i).add(temp.charAt(j) - '0');
			}
		}
		
		K = parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = parseInt(st.nextToken()) - 1;
			int dir = parseInt(st.nextToken());
			
			visit = new int[4];
			visit[num] = 1;	// 현재 톱니바퀴 회전 표시
			turn(num, dir);
		}
		
		for(int i=0; i<4; i++) {
			if(list.get(i).get(0) == 1) {
				result += Math.pow(2, i);
			}
		}
		System.out.println(result);
	}

	static void turn(int num, int dir) {
		
		if(num == 0) {
			int MyRight = list.get(num).get(2);
			int AfterLeft = list.get(num+1).get(6);
			
			turnReal(num, dir);	// 회전시키기
			
			// 맞닿아 있는 곳이 다른 극, 회전한 적 없는 톱니바퀴인 경우
			if(MyRight != AfterLeft && visit[num+1] == 0) {
				visit[num+1] = 1;	// 오른쪽 톱니바퀴 회전 표시
				turn(num+1, -dir);	// 현재 톱니바퀴 번호+1, 회전 방향 반대
			}
			
		} else if(num == 1 || num == 2) {
			int MyRight = list.get(num).get(2);
			int MyLeft = list.get(num).get(6);
			int BeforeRight = list.get(num-1).get(2);
			int AfterLeft = list.get(num+1).get(6);
			
			turnReal(num, dir);	// 회전시키기
			
			// 맞닿아 있는 곳이 다른 극, 회전한 적 없는 톱니바퀴인 경우
			if(MyRight != AfterLeft && visit[num+1] == 0) {
				visit[num+1] = 1;	// 오른쪽 톱니바퀴 회전 표시
				turn(num+1, -dir);	// 현재 톱니바퀴 번호+1, 회전 방향 반대
			}
			
			// 맞닿아 있는 곳이 다른 극, 회전한 적 없는 톱니바퀴인 경우
			if(MyLeft != BeforeRight && visit[num-1] == 0) {
				visit[num-1] = 1;	// 왼쪽 톱니바퀴 회전 표시
				turn(num-1, -dir);	// 현재 톱니바퀴 번호-1, 회전 방향 반대
			}
			
		} else if(num == 3) {
			int MyLeft = list.get(num).get(6);
			int BeforeRight = list.get(num-1).get(2);
			
			turnReal(num, dir);	// 회전시키기
			
			// 맞닿아 있는 곳이 다른 극, 회전한 적 없는 톱니바퀴인 경우
			if(MyLeft != BeforeRight && visit[num-1] == 0) {
				visit[num-1] = 1;	// 왼쪽 톱니바퀴 회전 표시
				turn(num-1, -dir);	// 현재 톱니바퀴 번호-1, 회전 방향 반대
			}
		}
	}
	
	static void turnReal(int num, int dir) {
		// 시계방향 회전
		if(dir == 1) {
			list.get(num).add(0, list.get(num).get(list.get(num).size()-1));	// 마지막 값을 index 0에 추가
			list.get(num).remove(list.get(num).size()-1);	// 마지막 값 삭제
		// 반시계방향 회전
		} else if(dir == -1) {
			list.get(num).add(list.get(num).get(0));	// index 0의 값을 마지막에 추가
			list.get(num).remove(0);	// index 0 삭제
		}
	}
}
