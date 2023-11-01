import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        ReentrantLock[] forks = new ReentrantLock[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher("Философ " + (i + 1), forks[i], forks[(i + 1) % numPhilosophers]);
        }

        Thread[] threads = new Thread[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }

        for (int i = 0; i < numPhilosophers; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
