import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client{
	
	 
	public static void main(String[] args) {
		
		String hostName = "127.0.0.1";
		Lock lock = new ReentrantLock();
		Socket clientSocket;
		int portNumber = 4444;
		BufferedReader stdIn;
		
		try {
			lock.lock();
			clientSocket = new Socket(hostName,portNumber);
			BufferedReader read = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter write = new PrintWriter(clientSocket.getOutputStream(),true);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Wpisz odpowiednia wartosc ADD lub REMOVE \n");
            write.println(stdIn.readLine());
            
            
            System.out.println("Server: " + read.readLine());
            System.out.println("Podaj godzine:  \n");
            write.println(stdIn.readLine());
            
            
            System.out.println("Podaj dzien: \n");
            write.println(stdIn.readLine());
           
            
            
            System.out.println("Server: " + read.readLine());
            
            write.close();
	        read.close();
	        clientSocket.close();
			
		}catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage()); 	
		}finally{
			lock.unlock();
         }
		
		
		
		
	}
	

}
