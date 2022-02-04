public class Seller {
    private CarShop carShop;
    private static final Car NEW_CAR = new Car("Toyota");

    public Seller(CarShop carShop) {
        this.carShop = carShop;
    }

    public synchronized void receiveCar() {
        while (carShop.store.size() == 0) {
            System.out.println("Производитель выпустил 1 авто");
            carShop.store.add(NEW_CAR);
            notifyAll();
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