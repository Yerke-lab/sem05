import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {

    private String name;
    private ReentrantLock leftFork;
    private ReentrantLock rightFork;

    public Philosopher(String name, ReentrantLock leftFork, ReentrantLock rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            think();
            eat();
        }
    }

    private void think() {
        System.out.println(name + " размышляет");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        leftFork.lock();
        rightFork.lock();
        try {
            System.out.println(name + " ест спагетти");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            leftFork.unlock();
            rightFork.unlock();
        }
    }
}
