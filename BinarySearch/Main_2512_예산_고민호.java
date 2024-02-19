package BinarySearch;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2512_예산_고민호 {
	/**
	 * @author 고민호
	 * 백준_2512_예산
	 * 난이도 S2
	 * 결과 : 통과		메모리 : 150,96KB		시간 : 164ms
	 * 
	 * 풀이방법
	 * 1. 문제를 보고 랜선자르기와 비슷한 유형이라고 생각이 들었습니다.
	 * 2. 이분탐색을 통해 최대의 총예산이 가능한 정수의 최대값 구하기 -> M은 N이상 1,000,000,000 이하라는 조건을 보고 완탐 X, 이분탐색 떠올리기!
	 * 3. 추가로 정수 Type 잘 생각하기 (int, long 둘 중 무엇을 사용할지)
	 */

	static int N, M;
	static int[] arr;
	static int start, end;	// 왼쪽 포인터, 오른쪽 포인터
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		
		arr= new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = parseInt(st.nextToken());
			end = Math.max(end, arr[i]);	// 값을 입력 받을때 최대 값 갱신하기, 이전에 sort를 통해 마지막 인덱스로 구했으나 시간초과 발생
		}
		
		M = parseInt(br.readLine());
		bs();
	}

	
	static void bs() {
		
		// 왼쪽 포인터가 오른쪽 포인터보다 왼쪽에 있는동안(탐색 조건)
		while(start <= end) {
			int mid = (start+end)/2; // 탐색 위치
			
			long sum = 0;
			for(int value : arr) {
				// 요청 예산이 탐색 값보다 작은 경우
				if(value < mid) {
					sum += value;	// 총 예산에 요청 예산 더하기
				// 요청 예산이 탐색 값보다 크거나 같은 경우
				} else {
					sum += mid;	// 총 예산에 탐색 값 더하기
				}
			}
			
			if(sum <= M) {
				start = mid + 1;	// 현재 탐색값보다 더 커야함!!
			} else if(sum > M) {
				end = mid - 1;	// 현재 탐색값보다 더 작아야함!!
			}
			
		}
		System.out.println(end);
	}
}
