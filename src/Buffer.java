import java.util.LinkedList;

class Buffer<T> { // Generická třída pro práci s různými datovými typy
    private final LinkedList<T> list;
    private final int capacity;

    public Buffer(int capacity) {
        this.list = new LinkedList<>();
        this.capacity = capacity;
    }

    // Producent: Přidání prvku do bufferu
    public synchronized void put(T data) throws InterruptedException {
        while (list.size() == capacity) {
            wait(); // Pokud je buffer plný, čeká na konzumenta
        }
        list.add(data);
        System.out.println("Producent vložil: " + data);
        notify(); // Oznámí čekajícího konzumenta, že jsou dostupná data
    }

    // Konzument: Odebrání prvku z bufferu
    public synchronized T take() throws InterruptedException {
        while (list.isEmpty()) {
            wait(); // Pokud je buffer prázdný, čeká na producenta
        }
        T data = list.removeFirst();
        notify(); // Oznámí čekajícího producenta, že je místo pro nová data
        return data;
    }

    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }
}