import java.net.*;
import java.io.*;

public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Server()).start();
    }

    static class Server implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;

            try {
                serverSocket = new ServerSocket(1200);

                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();

                        BufferedReader inputReader = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                        System.out.println("Message from client: " + inputReader.readLine());
                    } catch (SocketTimeoutException ste) {
                        ste.printStackTrace();
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    if (serverSocket != null) {
                        serverSocket.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}