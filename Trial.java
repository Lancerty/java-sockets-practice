import java.net.*;
import java.io.*;

public class Trial {
    public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(1200);
    Socket clientSocket = serverSocket.accept();

    
    out = new PrintWriter(clientSocket.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
}
