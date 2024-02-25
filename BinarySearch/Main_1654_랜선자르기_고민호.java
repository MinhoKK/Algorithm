package BinarySearch;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_1654_랜선자르기_고민호 {

	static int K, N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = parseInt(st.nextToken());
		N = parseInt(st.nextToken());
		
		arr = new int[K];
		for(int i=0; i<K; i++) {
			arr[i] = parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		long start = 1;
		long end = arr[K-1];
		
		while(start <= end) {
			long half = (start+end)/2;
			long count = 0;
			
			for(int i=0; i<K; i++) {
				count += arr[i]/half;
			}
			
			if(count < N) {
				end = half - 1;
			} else if (count >= N) {
				start = half + 1;
			}
		}
		
		System.out.println(end);
	}

}
