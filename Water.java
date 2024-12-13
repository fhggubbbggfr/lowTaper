public class Water{
    boolean canFlowOff(int M, int N, int[][] map, int row, int col){
        if(row == 0 || col == 0 || row == M-1 || col == N-1){
            return true;
        }
        boolean up=down=left=right=false;
        if(map[row][col-1] < map[row][col]){
            left = canFlowOff(M, N, map, row, col-1);
        }
        if(map[row][col+1] < map[row][col]){
            right = canFlowOff(M, N, map, row, col+1);
        }
        if(map[row+1][col] < map[row][col]){
            down = canFlowOff(M, N, map, row+1, col);
        }
        if(map[row-1][col] < map[row][col]){
            up = canFlowOff(M, N, map, row-1, col);
        }
        if(up || down || left || right){
            return true;
        }
        return false;
    }

}
