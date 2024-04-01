//created a class for checking for prime numbers
//this allows me to extend it in my single/multithreaded class and use multiple times
//without repetition of code (Respecting the rules of DRY!!)
public class PrimeChecker {
    //fixed isPrime logic thanks to your help
    //followed your suggestion to use a boolean instead of divisors
    public boolean isPrime(int num){
        if(num <= 1)
            return false;
        for(int i = 2; i < num; i++){
            if(num % i == 0)
                return false;
        }
        return true;
    }
}
