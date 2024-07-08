package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_16928_뱀과사다리게임 {

    /**
     * @author 고민호
     * 백준_16928_뱀과 사다리 게임
     * 난이도 G5
     * 결과 : 통과      메모리 : 14,948KB      시간 : 148ms
     *
     * 풀이방법
     * 1. 현재 위치에서 주사위 굴리기 -> 현재 위치 +1 ~ +6, 이때 도착한적이 없는 위치라면 queue에 추가
     * 2. 도착 지점은 3가지 경우의 수 존재: 1.빈칸 2.사다리 3.뱀
     *    처음에 입력받은 사다리, 뱀의 값과 현재 위치를 비교하여 도착지점 queue에 저장하기
     * 3. 가장 먼저 게임칸 100 이상에 도착한 방법이 BFS를 사용했기에 가장 빠른 방법
     */

    static class Ladder{
        int x;
        int y;

        public Ladder(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Snake{
        int x;
        int y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Player{
        int x;
        int count;  // 주사위 횟수

        public Player(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    static int N, M; // 사다리 수, 뱀의 수
    static List<Ladder> ladders = new ArrayList<>();
    static List<Snake> snakes = new ArrayList<>();
    static int[] map = new int[101];
    static int result; // 주사위 횟수

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());   // 사다리 수
        M = parseInt(st.nextToken());   // 뱀의 수


        // 사다리 x,y 입력
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());

            ladders.add(new Ladder(x, y));
        }

        // 뱀 x,y 입력
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());

            snakes.add(new Snake(x, y));
        }

        bfs(1);

        System.out.println(result);

    }

    static void bfs(int start){
        Queue<Player> queue = new LinkedList<>();
        queue.add(new Player(start, 0));
        map[start] = 1;

        while(!queue.isEmpty()){
            Player out = queue.poll();
            
            // 종료 조건 = 게임판 100칸 도착 or 100칸 초과
            if(out.x >= 100){   
                result = out.count;
                return;
            }
            
            here:
            for(int d=1; d<=6; d++){
                int now = out.x + d;
                if(now > 0 && now < 101 && map[now] == 0){  // 범위 내, 방문한적 없는 곳이라면 이동
                    // 사다리 도착한 경우
                    for(int i=0; i<ladders.size(); i++){
                        if(ladders.get(i).x == now){
                            map[ladders.get(i).y] = 1;
                            queue.add(new Player(ladders.get(i).y, out.count+1));   // (사다리의 도착지점 y, 현재 주사위 횟수 +1) queue 추가
                            continue here;
                        }
                    }
                    
                    // 뱀 도착한 경우
                    for(int i=0; i<snakes.size(); i++){
                        if(snakes.get(i).x == now){
                            map[snakes.get(i).y] = 1;
                            queue.add(new Player(snakes.get(i).y, out.count+1)); // (뱀의 도착지점 y, 현재 주사위 횟수 +1) queue 추가
                            continue here;
                        }
                    }
                    
                    // 도착한 곳에 아무것도 없는 경우
                    map[now] = 1;
                    queue.add(new Player(now, out.count+1));    // (도착지점 y, 현재 주사위 횟수 +1) queue 추가
                }
            }
        }

    }
}
