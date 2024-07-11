package DataStructure;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_3190_뱀 {
    /**
     * @author 고민호
     * 백준_3190_뱀
     * 난이도 G4
     * 결과 : 통과      메모리 : 19,692KB      시간 : 212ms
     */

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Turn {
        int time;
        char dir;

        public Turn(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    static int N, K;    // 보드 크기, 사과 개수
    static int[][] map;
    static List<Node> apples = new ArrayList<>();
    static List<Turn> turns = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0};   // 상 우 하 좌
    static int[] dc = {0, 1, 0, -1};
    static int nowDir = 1;  // 현재 바라보는 방향
    static Deque<Node> snake = new ArrayDeque<>();
    static int time;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());
        K = parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = parseInt(st.nextToken()) - 1;
            int c = parseInt(st.nextToken()) - 1;
            apples.add(new Node(r, c));
        }

        int turnCount = parseInt(br.readLine());

        for (int i = 0; i < turnCount; i++) {
            st = new StringTokenizer(br.readLine());
            int t = parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            turns.add(new Turn(t, d));
        }

        snake.add(new Node(0, 0));

        while (true) {
            time++;

            Node first = snake.peekFirst(); // 뱀위 머리

            int nr = first.r + dr[nowDir];
            int nc = first.c + dc[nowDir];

            // 머리가 범위를 벗아난 경우
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                System.out.println(time);
                return;
            }

            // 머리가 몸통에 부딪힌 경우
            for (Node body : snake) {
                if (nr == body.r && nc == body.c) {
                    System.out.println(time);
                    return;
                }
            }

            // 머리가 도착한 좌표에 사과가 있는지 확인하기
            boolean hasApple = false;
            for (Node apple : apples) {
                if (apple.r == nr && apple.c == nc) {
                    apples.remove(apple);   // 먹은 사과는 사라짐
                    hasApple = true;
                    break;
                }
            }

            snake.addFirst(new Node(nr, nc));

            if (!hasApple) {
                snake.removeLast();
            }

            checkTurn(time);
        }
    }

    static void checkTurn(int time) {
        for (Turn turn : turns) {
            if (turn.time == time) {
                // 오른쪽 방향전환
                if (turn.dir == 'D') {
                    nowDir += 1;
                    if (nowDir > 3) nowDir = 0;
                    // 왼쪽 방향전환
                } else if (turn.dir == 'L') {
                    nowDir -= 1;
                    if (nowDir < 0) nowDir = 3;
                }
            }
        }
    }
}
