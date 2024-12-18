public class Main {
    public static void main(String[] args) {
        Buffer<String> buffer = new Buffer<>(5);

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}