import java.net.*;
import java.io.*;

public class MyClient {
    public static void main(String[] args) {
        new Thread(new Client()).start();
    }

    static class Client implements Runnable {
        @Override
        public void run() {
            Socket socket = null;

            try {
                socket = new Socket("localhost", 1200);

                PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);
                outWriter.println("Hello");

                outWriter.close();
                socket.close();

                try {
                    outWriter = new PrintWriter(socket.getOutputStream(), true);
                } catch (SocketException se) {
                    if (socket.isClosed()) {
                        socket = new Socket("localhost", 4444);
                        outWriter = new PrintWriter(socket.getOutputStream(), true);
                    }
                }

                outWriter.println("Hello again");
            } catch (UnknownHostException uhe) {
                uhe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}