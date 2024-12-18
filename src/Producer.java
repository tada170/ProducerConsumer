import java.util.Scanner;

class Producer implements Runnable {
    private final Buffer<String> buffer;

    public Producer(Buffer<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Zadejte URL adresy, které chcete přidat do bufferu (zadejte 'exit' pro ukončení):");
            while (true) {
                System.out.print("Zadejte URL: ");
                String url = scanner.nextLine();

                if ("exit".equalsIgnoreCase(url)) {
                    System.out.println("Producent ukončil zadávání.");
                    break;
                }

                buffer.put(url); // Přidání URL do bufferu
                System.out.println("Producent přidal URL: " + url);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Producent byl přerušen.");
        }
    }
}