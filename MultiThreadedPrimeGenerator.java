public class MultiThreadedPrimeGenerator extends PrimeChecker implements Runnable {

    public int start;
    public int end;

    public MultiThreadedPrimeGenerator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for(int i = start; i <= end; i++){
            if(isPrime(i)){
                System.out.println(i);
            }
        }
    }

}
