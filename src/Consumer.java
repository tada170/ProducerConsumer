import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class Consumer implements Runnable {
    private final Buffer buffer;
    private final String outputFile = "consumer_output.txt"; // Název souboru

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
public void run() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
        while (true) {
            String url = (String) buffer.take(); // Odebrání URL z bufferu

            String response = fetchDataFromUrl(url);
            // Zapsání odpovědi do souboru
            writer.write("Zpracovaná URL: " + url);
            writer.newLine();
            writer.write("Odpověď ze serveru: " + response);
            writer.newLine();
            writer.newLine();
            writer.flush(); // Uloží změny okamžitě do souboru

            // Simulace zpracování
            Thread.sleep(1000);
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.err.println("Konzument byl přerušen.");
    } catch (IOException e) {
        System.err.println("Chyba při zapisování do souboru: " + e.getMessage());
    }
}

    // Funkce pro načítání dat z URL
    private String fetchDataFromUrl(String urlString) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString); // Replace deprecated URL constructor with URL(String)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner scanner = new Scanner(connection.getInputStream())) {
                    while (scanner.hasNextLine()) {
                        response.append(scanner.nextLine());
                    }
                }
            } else {
                response.append("Chyba: HTTP kód ").append(responseCode);
            }
        } catch (IOException e) {
            response.append("Chyba při načítání dat: ").append(e.getMessage());
        }
        return response.toString();
    }
}