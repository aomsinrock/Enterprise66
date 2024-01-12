/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package livescore;

import java.util.Scanner;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author sarun
 */
public class Main {
    @Resource(mappedName = "jms/SimpleJMSTopic")
    private static Topic topic;
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/SimpleJMSQueue")
    private static Queue queue;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection connection = null;


        Destination dest = (Destination) topic;



        
        try {
            connection = connectionFactory.createConnection();

            Session session = connection.createSession(
                        false,
                        Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(dest);
            TextMessage message = session.createTextMessage();
            //producer.setTimeToLive(10000);  //message live is set to 10 seconds
            //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            Scanner in = new Scanner(System.in);
            System.out.println("Enter \'q\' or \'Q\' to exit");
            while(true){
                System.out.print("Enter Live Score ");
                String input = in.nextLine();
                if(input.equals("q") || input.equals("Q")){
                    break;
                }
                message.setText(input);
                producer.send(message);
            }
            
//            for (int i = 0; i < NUM_MSGS; i++) {
//                message.setText("This is message " + (i + 1));
//                System.out.println("Sending message: " + message.getText());
//                producer.send(message);
//                  /*if (i == 2) {
//                    producer.send(message, DeliveryMode.NON_PERSISTENT, 4, 5000);
//                }
//                  else {
//                      producer.send(message);
//                  }*/
//            }
//
//            /*
//             * Send a non-text control message indicating end of
//             * messages.
//             */
            producer.send(session.createMessage());
        } catch (JMSException e) {
            System.err.println("Exception occurred: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }

    
    
}
