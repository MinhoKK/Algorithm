package ReTry;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_15686_치킨배달 {
    /**
     * @author 고민호
     * 백준_15686_치킨배달
     * 난이도 G5
     * 결과 : 통과      메모리 : 15,832KB      시간 : 220ms
     */

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] comb;
    static List<Node> chicken = new ArrayList<>();
    static List<Node> house = new ArrayList<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        map = new int[N][N];
        comb = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    house.add(new Node(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Node(i, j));
                }
            }
        }

        makeComb(0, 0);

        System.out.println(result);
    }

    static void makeComb(int count, int depth) {
        // 종료 조건 = M개 치킨 집 선택
        if (depth == M) {
            getDistance();
            return;
        }

        for (int i = count; i < chicken.size(); i++) {
            comb[depth] = i;
            makeComb(i+1, depth+1);
        }
    }

    static void getDistance() {
        int totalDis = 0;

        for (int i = 0; i < house.size(); i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < comb.length; j++) {
                // 각각의 집에서 가장 가까운 치킨집까지의 거리 구하기
                int dis = Math.abs(house.get(i).r - chicken.get(comb[j]).r) + Math.abs(house.get(i).c - chicken.get(comb[j]).c);
                min = Math.min(min, dis);
            }
            totalDis += min;
        }

        result = Math.min(result, totalDis);
    }
}
