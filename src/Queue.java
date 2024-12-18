class Queue<I extends Number> {
    private static LinkedList list = new LinkedList();

    public Queue() {
        list = new LinkedList();
    }

    // Přidání prvku na konec fronty (enqueue)
    public void enqueue(int data) {
        list.add(data);
    }

    // Odebrání prvku z fronty (dequeue)
    public int dequeue() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Fronta je prázdná");
        }
        return list.remove();
    }

    // Získání velikosti fronty
    public int size() {
        return list.size();
    }

    public static boolean isEmpty() {
        return list.isEmpty();
    }

    // Zobrazení obsahu fronty (pro testování)
    public void display() {
        list.display();
    }

}