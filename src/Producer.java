import java.util.Scanner;

class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
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

                if (isValidUrl(url)) {
                    buffer.put(url);
                    System.out.println("Producent přidal URL: " + url);
                } else {
                    System.out.println("Neplatná URL! Ujistěte se, že URL začíná na 'http://' nebo 'https://'.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Producent byl přerušen.");
        }
    }

    private boolean isValidUrl(String url) {
        return url != null && (url.startsWith("http://") || url.startsWith("https://"));
    }
}
