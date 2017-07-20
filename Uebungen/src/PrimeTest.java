/**
 * Created by Georg Rolof on 23.06.2017.
 */
public class PrimeTest {

    public static void main(String[] args) {
        new PrimeTest().start(11234457);
    }

    private void start(int primzahl) {
        boolean prime = false;
        int x = (primzahl / 2);
        System.out.println(x);
        for (int i = 2; i <= x; i++) {
            if((primzahl % i) == 0){
                prime = false;
                System.out.println(i);
                break;
            }else{
                prime = true;
            }
            System.out.println(i);
        }
        System.out.println(prime);
    }
}
