public class Seller {
    private CarShop carShop;
    private static final int CAR_SELLING_TIME = 1000;
    private static final int CAR_MANUFACTORING_TIME = 1000;
    private static final Car NEW_CAR = new Car("Toyota");

    public Seller(CarShop carShop) {
        this.carShop = carShop;
    }

    public synchronized void receiveCar() {
        try {
            System.out.println("Продавец ожидает поставку");
            Thread.sleep(CAR_MANUFACTORING_TIME);
            System.out.println("Производитель Toyota выпустил 1 авто");
            carShop.store.add(NEW_CAR);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {
        System.out.printf("%s зашел в автосалон\n", Thread.currentThread().getName());
        try {
            while (carShop.store.size() == 0) {
                Thread.sleep(CAR_SELLING_TIME);
                System.out.println("Продавец: Машин нет в наличии в вашей комплектации");
                wait();
            }
            Thread.sleep(CAR_SELLING_TIME);
            System.out.println("Продавец: На складе появился автомобиль");
            Thread.sleep(CAR_SELLING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s уехал на новеньком авто\n", Thread.currentThread().getName());
        return carShop.store.remove(0);
    }
}