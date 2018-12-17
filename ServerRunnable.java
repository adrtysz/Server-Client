
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerRunnable implements Runnable {
	
	Socket clientSocket = null;
	HairDresser hairDresser = new HairDresser();
	HashMap<Integer, String> schedule = new HashMap<>();
	Lock lock = new ReentrantLock();
	
	ServerRunnable(Socket clientSocket){
		
		this.clientSocket=clientSocket;
		
	}
	
	public void run() {
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			
	        
	        if((in.readLine().equals("add")) || (in.readLine().equals("ADD"))) {
	        	out.println("Trwa umawianie wizyty \n");
	        	out.flush();
	        	String hour,day;
	        	hour = in.readLine();
	        	day = in. readLine();
	        	
	        	Integer Hour = Integer.parseInt(hour);
	        	
	        	if(hairDresser.add(Hour, day)==true) {
	        	
	        	out.println("Wizyta umowiona/n");
	        	out.flush();
	        	} else {
	        		out.println("Termin zajety/n");
	        		out.flush();
	        	}
	        	
	        	
	        } else if ((in.readLine().equals("remove")) || (in.readLine().equals("REMOVE"))) {
	        	
	        	out.println("Trwa odwolywanie wizyty \n");
	        	out.flush();
	        	String hour,day;
	        	hour = in.readLine();
	        	day = in. readLine();
	        	
	        	Integer Hour = Integer.parseInt(hour);
	        	
	        	if (hairDresser.remove(Hour, day)==true) {
	        	 out.println("Wizyta odwolana/n");
	        	 out.flush();
	        	} else {
	        		out.println("Nie ma takiej wizyty/n");
	        		out.flush();
	        	}
	        }
	        
	        out.println("Dziekujemy/n");
	        out.flush();
	        
	        in.close();
	        out.close();
	        try{
	            clientSocket.close();
	            } catch (IOException e){
	            	System.out.println(e.getMessage());
	            }
	        
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
		
		
	}
	

}
