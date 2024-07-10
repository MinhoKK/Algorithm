package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_14719_빗물 {
    /**
     * @author 고민호
     * 백준_14719_빗물
     * 난이도 G5
     * 결과 : 통과      메모리 : 14,300KB      시간 : 120ms
     *
     * 풀이방법
     * 1. 현재 기준으로 나보다 높은 왼쪽/오른쪽 기둥 찾기(투포인터)
     * 2. 왼쪽/오른쪽 기둥 중 높이가 낮은 기둥 - 현재 위치의 기둥 높이 = 모이는 빗물
     *    -> 현재 위치의 기둥 높이 < 왼쪽/오른쪽 기둥 중 낮은 기둥 높이
     * 3. 가장 왼쪽, 오른쪽 기둥에는 물이 모일 수 없음
     */

    static int H, W;
    static int[] map;   // 기둥 높이 배열
    static int result;  // 총 빗물 양

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = parseInt(st.nextToken());
        W = parseInt(st.nextToken());

        map = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++){
            map[i] = parseInt(st.nextToken());
        }

        for(int i=1; i<W-1; i++){
            int left = 0;   // 왼쪽 기둥 높이
            int right = 0;  // 오른쪽 기둥 높이

            // 현재 지점 기준 왼쪽에서 가장 높은 벽 찾기
            for(int j=i-1; j>=0; j--){
                if(map[j] > left){  // j번째 기둥의 높이가 찾은 왼쪽 기둥 높이보다 높으면
                    left = map[j];
                }
            }

            // 현재 지점 기준 오른쪽에서 가장 높은 벽 찾기
            for(int j=i+1; j<=W-1; j++){
                if(map[j] > right){ // j번째 기둥의 높이가 찾은 오른쪽 기둥 높이보다 높으면
                    right = map[j];
                }
            }

            int lowHeight = Math.min(left, right); // 왼쪽 높은 기둥, 오른쪽 높은 기둥 중 낮은 기둥 찾기
            if(map[i]<lowHeight){
                result += lowHeight - map[i]; // 낮은 기둥 높이 - 현재 기둥 높이 = 빗물
            }
        }

        System.out.println(result);
    }
}
