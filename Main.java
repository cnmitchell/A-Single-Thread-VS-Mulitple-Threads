//The main benefit of implementing the Runnable interface over extending the Thread class is that it allows room to extend another class if preferred.
//When you extend the Thread class, you no longer have the option to extend another, as Java does not support multiple inheritances for a class.
//You are able to implement as many classes as you want, so the Runnable interface is the more favorable option.

import java.util.Scanner;

public class Main {
    public static int num;

    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number that is greater than 1 to see all of the prime numbers between"+
                " them inclusive: ");
        num = scanner.nextInt();
        while (num < 1) {
            System.out.println("The number must be greater than 1! Enter again:");
            num = scanner.nextInt();
        }
        System.out.println("The prime numbers between 1 and " + num + " inclusive are:");


        System.out.println("Single-Threaded Prime Generator: ");
        long singleStartTime = System.currentTimeMillis();

        Thread singleThread = new Thread(new SingleThreadedPrimeGenerator(num));
        singleThread.start();
        try {
            singleThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long singleEndTime = System.currentTimeMillis();
        long singleTotalTime = singleEndTime -singleStartTime;

        System.out.println("\nMulti-Threaded Prime Generator: ");
        long multiStartTime = System.currentTimeMillis();

        //perfected my multithreading approach because you said my first one
        //was insufficient and I agree. This approach is so much faster when
        //entering higher numbers!
        Thread[] myThreads = new Thread[10];
        int rangeSize;
        rangeSize = num/myThreads.length;
        int start = 1;
        int end = rangeSize;

        for(int i=0; i < myThreads.length; i++){
            myThreads[i] = new Thread(new MultiThreadedPrimeGenerator(start, end));
            myThreads[i].start();
            start += rangeSize;
            end += rangeSize;
        }

        for (Thread myThread : myThreads) {
            try {
                myThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        long multiEndTime = System.currentTimeMillis();
        long multiTotalTime = multiEndTime - multiStartTime;

        long difference = Math.abs(multiTotalTime - singleTotalTime);

        //fixed code, so you will not have to scroll to the right :)
        //even added a text block to my paragraph at the end
        System.out.println("\nThe final report is:\nSingle-Threaded Random Number Generator took " + singleTotalTime +
                " ms." + "\nMulti-Threaded Random Number Generator took " + multiTotalTime + " ms.\n");
        if(singleTotalTime < multiTotalTime)
            System.out.println("The single-threaded prime number generator was faster than the multi-threaded prime\n"
                    + "number generator by " + difference + " ms!");
        if(singleTotalTime > multiTotalTime)
            System.out.println("The multi-threaded prime number generator was faster than the single-threaded prime\n"
                    + "number generator by " + difference + " ms!");
        if(singleTotalTime == multiTotalTime)
            System.out.println("The multi-threaded and single-threaded prime generator both took an equal amount of\n"
                    + "time, which was " + difference + " ms!");
        System.out.println("""
                \nWhen entering small numbers, you will notice that the single-threaded generator tends to
                be faster than, if not equal to, the multi-threaded generator BUT, as you enter up to 50000 and
                higher, the multi-threaded generator will always be faster than the single-threaded generator.
                When dealing with bigger data, having multiple threads cuts down on overall execution time!""");

    }
}

