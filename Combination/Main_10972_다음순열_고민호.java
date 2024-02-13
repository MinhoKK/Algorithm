package Combination;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;


public class Main_10972_다음순열_고민호 {
	/**
	 * @author 고민호
	 * 백준_10972_다음순열
	 * 난이도 S3
	 * 결과 : 통과		메모리 : 19,592KB		시간 : 396ms
	 * 
	 * 
	 * 접근방법
	 * List에 조합 가능한 순열을 모두 담는다. 이때 주어진 순열이 오면 해당 index를 기억하고 출력 시 index+1에 위치한 순열을 출력
	 * -> 메모리 초과 발생, 조합 가능한 모든 순열을 만든다에서 메모리 초과가 발생했다고 생각됌.
	 * 
	 * 풀이방법
	 * 1. next permutation을 사용해 다음 순열 찾기!!
	 */
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = parseInt(st.nextToken());
		}
		
		boolean result = np();
		
		if(result) {
			for(int value : arr) {
				System.out.print(value + " ");
			}
		} else if(!result) {
			System.out.println(-1);
		}
		
	}
	
	public static boolean np() {
		final int N = arr.length;
		
		// 교환 위치 찾기
		// i ~ N-1 까지 사전순으로 마지막까지 순열을 만들었다는 의미  
		int i = N-1;
		while(i>0 && arr[i-1]>=arr[i]) --i;
		
		// 사전 순 마지막 순열이 주어진 경우
		if(i==0) return false;	
		
		// 이제 i-1 자리에 i-1 보다 큰 수가 와서 0 ~ i-1 까지 새로운 조합을 만들어야 함
		int j = N-1;
		while(arr[i-1] >= arr[j]) --j;
		
		
		swap(i-1, j);
		
		// 0 ~ i-1 까지 조합이 새로 만들어졌으니 i 부터는 오름차순(사전순)으로 정렬
		int k = N-1;
		while(i<k) swap(i++, k--);
		
		return true;
	}
	
	public static void swap (int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
		
}
