class Queue {
    private final LinkedList list;

    public Queue() {
        this.list = new LinkedList();
    }

    public void enqueue(String url) {
        list.add(url);
    }

    public String dequeue() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.remove();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void display() {
        list.display();
    }
}
