class Buffer {
    private final Queue queue;
    private final int capacity;

    public Buffer(int capacity) {
        this.queue = new Queue();
        this.capacity = capacity;
    }

    public synchronized void put(String url) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Buffer je plný! Producent čeká na místo v bufferu.");
            wait();
        }
        queue.enqueue(url);
        System.out.println("Producent vložil URL: " + url);
        notify();
    }


    public synchronized String take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        String url = queue.dequeue();
        notify();
        return url;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
