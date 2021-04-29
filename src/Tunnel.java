import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final int tunnelWidth = homeWork.CARS_COUNT / 2;
    private Semaphore tunnelAccess;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        tunnelAccess = new Semaphore(tunnelWidth);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                if (!tunnelAccess.tryAcquire()) {
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    tunnelAccess.acquire();
                }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                tunnelAccess.release();
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
