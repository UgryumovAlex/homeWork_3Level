import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {  CARS_COUNT = 0; }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier step;
    private ArrayBlockingQueue<Car> finishPositions;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier step, ArrayBlockingQueue<Car> finishPositions) {
        this.race  = race;
        this.speed = speed;
        this.step  = step;
        this.finishPositions = finishPositions;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            step.await(); //ждём когда все будут готовы к гонке
            step.await(); //стартуем после стартового свистка

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            System.out.println(this.name + " доехал до финиша");
            finishPositions.put(this); //доехал до финиша

            step.await(); //ждём когда все остальные доедут

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
