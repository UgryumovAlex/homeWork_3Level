/**
 * Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз
 * (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 * */

public class homeWork {

    static final Object monitor = new Object();
    static volatile char letter = 'A';
    
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (letter != 'A') {
                            monitor.wait();
                        }
                        System.out.print(letter);
                        letter = 'B';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (letter != 'B') {
                            monitor.wait();
                        }
                        System.out.print(letter);
                        letter = 'C';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (letter != 'C') {
                            monitor.wait();
                        }
                        System.out.print(letter);
                        letter = 'A';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
