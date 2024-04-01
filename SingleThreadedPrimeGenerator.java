public class SingleThreadedPrimeGenerator extends PrimeChecker implements Runnable{

    private final int currentNum;

    SingleThreadedPrimeGenerator(int currentNum){
        this.currentNum = currentNum;
    }

    public void run() {
        for(int i=2; i<=currentNum; i++){
            if(isPrime(i))
                System.out.println(i);
        }
    }
}
