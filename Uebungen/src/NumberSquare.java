/**
 * Created by Georg Rolof on 28.06.2017.
 */
public class NumberSquare {
    public static void main(String[] args) {
        new NumberSquare().start(3);
    }

    private void start(int x) {
        int xMax = x * x;
        int[][] square = new int[x][x];
        for (int i = 0; i < x; i++) {

            for (int j = 0; j < x; j++) {
                for (int k = 1; k < xMax+1; k++) {
                    square[i][j] = k;
                }

            }
        }   
        printSquare(square);
    }


    private void printSquare(int[][] x){
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {

                System.out.println(x[i][j]);
            }
        }

}


}
