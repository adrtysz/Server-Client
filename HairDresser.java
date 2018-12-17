import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HairDresser {
	
	 HashMap<Integer, String> schedule = new HashMap<>();
	    Lock lock = new ReentrantLock();
	    
	    HairDresser(){

	    }

	    public boolean add(Integer hour, String day){
	        try{
	            lock.lock();
	            if (!(schedule.containsKey(hour))&&(hour>=10)&&(hour<=18)&&(!(schedule.containsValue(day)))){
	            	schedule.put(hour, day);
	            	System.out.println("Dodano godzina: "+ hour +" dzien: " +day);
	                return true;
	                }
	            else {
	            	System.out.println("Termin zajêty");
	            }
	            } finally{
	            lock.unlock();
	            }
	        return false;
	    }

	    public boolean remove(Integer hour, String day){
	        try{
	            lock.lock();
	            if (schedule.containsKey(hour)){
	            	schedule.remove(hour);
	                System.out.println("Zwolniono termin godzina: "+ hour + " dzien: " + day);
	                return true;
	                }
	            else {
	            	System.out.println("Nie ma takiej wizyty");
	            }
	            } finally{
	            lock.unlock();
	            }
	        return false;
	    }

	   

}
