public class PixelMarker {

    private boolean[][] markedGrid;

    public PixelMarker(int height, int width){
        markedGrid = new boolean[height][width];
        for(int row = 0; row < height; row ++){
            for(int col = 0; col <width; col ++){
                markedGrid[row][col] = false;
            }
        }
    }

    public void mark(int row, int col){
        markedGrid[row][col] = true;
    }

    public void markArea(int row, int col, int radius){
        int rowTop = row - radius;
        int rowBottom = row + radius;
        int colLeft = col - radius;
        int colRight = col + radius;

        for(int r = rowTop; r <= rowBottom; r++){
            for(int c = colLeft; c <= colRight; c++){
                if (inBounds(r,c)) {
                    mark(r, c);
                }
            }
        }
    }

    private boolean inBounds(int row, int col){
        return row < markedGrid.length && col < markedGrid[0].length && row >= 0 && col >=0;
    }

    public boolean isMarked(int row, int col){
        if (inBounds(row,col)) {
            return markedGrid[row][col];
        } else {
            return true;
        }
    }
}
