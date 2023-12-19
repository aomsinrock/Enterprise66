import java.io.*;
import java.net.*;
//64050037

class TCPServer {
    /**
     * @param argv
     */
    public static void main(String argv[]) {
        String clientInput;
        String[] numbers;
        String capitalizedSentence;
        ServerSocket welcomeSocket = null;
        Socket connectionSocket = null;
        BufferedReader inFromClient = null;
        DataOutputStream outToClient = null;
        try {
            welcomeSocket = new ServerSocket(1667);
            System.out.println("Server: Waiting for client connection at port number 1667");
        } catch (IOException e) {
            System.out.println("Cannot create a welcome socket");
            System.exit(1);
        }
        while (true) {
            try {
                connectionSocket = welcomeSocket.accept();
                inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                System.out.print("Client: enter two numbers separated by space (to end just press enter): ");
                
                while (true) {
                    clientInput = inFromClient.readLine();

                    if (clientInput == null || clientInput.equals("END")) {
                        System.out.println("Server: Connection closed by the client.");
                        break;
                    }

                    numbers = clientInput.split(" ");
                    if (numbers.length != 2) {
                        outToClient.writeBytes("Invalid input. Please enter two numbers separated by space.\n");
                        continue;
                    }

                    int num1 = Integer.parseInt(numbers[0]);
                    int num2 = Integer.parseInt(numbers[1]);

                    int result = num1 + num2;

                    capitalizedSentence = "The result is " + result + "\n";
                    outToClient.writeBytes(capitalizedSentence);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error processing the connection: " + e.getMessage());
            } finally {
                try {
                    if (inFromClient != null)
                        inFromClient.close();
                    if (outToClient != null)
                        outToClient.close();
                    if (connectionSocket != null)
                        connectionSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
