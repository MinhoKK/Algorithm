package Implementation;

import java.io.*;
import java.util.*;

public class Main_2563_색종이_고민호 {
	/**
     * @author 고민호
     * 백준_2563_색종이
     * 난이도 S5
     * 결과
     *
     * 문제풀이
     * 1. 도화지 2차원 배열 생성
     * 2. 색종이의 좌표가 주어지면 (x,y) (x+10,y+10) 사이 범위의 도화지를 1로 변경
     * 3. 2차원 배열의 1의 수 = 색종이의 범위
     */
	
	static int T ;
    static int[][] paper = new int[101][101];
    static int result;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for(int i=r; i<r+10; i++){
                for(int j=c; j<c+10; j++){
                	// 색이 칠해지지 않은 경우
                    if(paper[i][j] != 1){
                    	// 색을 칠하고 카운트 + 1
                        paper[i][j] = 1;
                        result++; 
                    }
                       
                }
            }
        }
        System.out.println(result);

	}

}
