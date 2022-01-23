class Customer implements Runnable {
    private final int CAR_WAITING_TIME = 1000;
    public CarShop shop;
    private Car buyedCar;

    public Customer(CarShop shop) {
        this.shop = shop;
    }

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(CAR_WAITING_TIME);
            System.out.printf("%s зашел в автосалон\n", getName());
            buyedCar = shop.sellCar();
            System.out.printf("%s уехал на новеньком авто\n", getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getName() {
        return Thread.currentThread().getName();
    }
}