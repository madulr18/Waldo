import java.awt.*;

public class Shape {
    private static Color[][] triangle;

    public static Color[][] getTriangle(){
        triangle = new Color[3][7];
        for(int i = 0; i<7; i++){
            triangle[2][i] = Color.BLACK;
        }
        for(int i = 2; i<7; i++){
            triangle[1][i] = Color.BLACK;
        }
        for(int i = 4; i<7; i++){
            triangle[0][i] = Color.BLACK;
        }
        return triangle;
    }

    public static void main(String[] args) {
        Color[][] test = getTriangle();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 7; col++) {
                if (triangle[row][col] != null){
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println("");
        }
    }
}
