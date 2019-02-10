import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    private static final int PORT = 9001;




    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", PORT);
             /*InputStreamReader input = new InputStreamReader(socket.getInputStream());
             BufferedReader bf = new BufferedReader(input);*/
             Scanner scanner = new Scanner(socket.getInputStream())) {

            String name = "Hello world!";

            /*
            OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
               */
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(name);

            new Thread(new Runnable() {
                @Override
                public void run() {
                   while(true) {
                       try {
                            if(scanner.hasNext()){
                                System.out.println(scanner.nextLine());
                            }
                       } catch (Exception ex) {
                           ex.printStackTrace();
                       } finally {

                       }
                   }
                }
            }).start();



            while(true){

                Scanner sc = new Scanner(System.in);
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeBytes(sc.next());
                //printWriter.write(sc.nextLine());
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}
