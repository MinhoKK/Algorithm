package Temp;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_15961_회전초밥_고민호 {
	/**
	 * @author 고민호
	 * 백준_15961_회전초밥
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 170,900KB		시간 : 544ms
	 * 
	 * 풀이방법
	 * 1. 완전탐색과 set을 이용해 문제에 접근했으나 완전탐색 시 N*K의 최악의 경우 시간초과 발생
	 * 2. 슬라이딩 윈도우 방식을 통해 초기 K개의 초밥에 대한 정보를 가지고 맨 앞 초밥을 빼고, 다음 초밥을 더해가며 중복되지 않는 초밥의 개수 세기
	 * 3. 이때 처음 초밥 K개 정보와 함께 쿠폰 초밥에 대한 정보도 함께 저장하기
	 * 4. 방문배열이 0인 경우 전체 초밥 개수 -1 또는 +1 진행, 단 단순히 방문이 아닌 개수를 반영하기에 0이 아닌 경우 전체 초밥 개수 변동 없 이 방문배열 값만 증가 또는 감소
	 */

	static int N, D, K, C;
	static int[] arr;	// 회전 초밥 정보
	static int[] visit;	// 먹을 수 있는 초밥
	static int result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());	// 접시 수
		D = parseInt(st.nextToken());	// 초밥 가짓수
		K = parseInt(st.nextToken());	// 연속해서 먹는 접시 수
		C = parseInt(st.nextToken());	// 쿠폰 번호
		
		arr = new int[N];
		visit = new int[D+1];
		
		for(int i=0; i<N; i++) {
			arr[i] = parseInt(br.readLine());
		}
		
		result = 1;	// 쿠폰에 적힌 초밥 1개
		visit[C] = 1;	// 쿠폰에 적힌 초밥 1개
		// 처음 K개
		for(int i=0; i<K; i++) {
			// 방문 X = 먹은적 없는 경우
			if(visit[arr[i]] == 0) {	
				result++;	// 초밥 가짓수는 먹은적 없는 경우에만 증가
			}
			visit[arr[i]]++;	// 해당 초밥을 먹은 개수는 항상 증가
		}
		
		int count = result;	// 슬라이딩 윈도우를 통해 한칸씩 밀릴때마다 바뀌는 초밥 가짓수와 이전의 최대값을 비교하기 위해 변수 선언
		for(int i=1; i<N; i++) {
			int out = arr[i-1];	// 가장 앞에 있는 초밥
			visit[out]--;	// 먹은 개수 감소
			if(visit[out] == 0) {	// 0이되는 경우 가짓수 감소
				count--;
			}
			
			int in = arr[(i+K-1) % N];	// 추가할 초밥
			if(visit[in] == 0) {	// 방문한적 없는 경우 가짓수 증가
				count++;
			}
			visit[in]++;	// 먹은 개수 증가
			
			result = Math.max(result, count);	// 가짓수 최대값 갱신
		}
		
		System.out.println(result);
	}

}
