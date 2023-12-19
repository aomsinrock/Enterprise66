import java.io.*;
import java.net.*;
import java.util.Scanner;
//64050037

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        try (Scanner inFromUser = new Scanner(System.in)) {
            Socket clientSocket = null;
            DataOutputStream outToServer = null;
            BufferedReader inFromServer = null;
            try {
                clientSocket = new Socket("localhost", 1667);
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
                inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                while (true) {
                    System.out.print("enter number 1 (to end just press enter): ");
                    sentence = inFromUser.nextLine();

                    if (sentence.isEmpty()) {
                        outToServer.writeBytes("END\n");
                        System.out.println("Client: Connection closed.");
                        break;
                    }

                    outToServer.writeBytes(sentence + ' ');

                    System.out.print("enter number 2 (to end just press enter): ");
                    sentence = inFromUser.nextLine();

                    if (sentence.isEmpty()) {
                        outToServer.writeBytes("END\n");
                        System.out.println("Client: Connection closed.");
                        break;
                    }

                    outToServer.writeBytes(sentence + '\n');

                    modifiedSentence = inFromServer.readLine();
                    System.out.println("Server: " + modifiedSentence);
                }
            } catch (IOException e) {
                System.out.println("Error occurred: Closing the connection");
            } finally {
                try {
                    if (inFromServer != null)
                        inFromServer.close();
                    if (outToServer != null)
                        outToServer.close();
                    if (clientSocket != null)
                        clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
