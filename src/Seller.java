public class Seller {
    private CarShop carShop;
    private static final Car NEW_CAR = new Car("Toyota");
    private static final int CAR_MANUFACTURING_TIME = 1000;

    public Seller(CarShop carShop) {
        this.carShop = carShop;
    }

    public synchronized void receiveCar() {
        try {
            while (true) {
                System.out.println("Производитель выпустил 1 авто");
                carShop.store.add(NEW_CAR);
                notifyAll();
                Thread.sleep(CAR_MANUFACTURING_TIME);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sellCar() {
        try {
            while (carShop.store.size() == 0) {
                System.out.println("Продавец: Машин нет в наличии в вашей комплектации");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s уехал на новеньком авто\n", Thread.currentThread().getName());
        carShop.store.remove(0);
    }
}