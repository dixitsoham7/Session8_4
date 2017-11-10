/* Threading is the concept to be implemented here
 * Tickets are reserved using concept of synchronization
 * if requested number of tickets are available then booking is done otherwise it fails
 * no of seats available are printed in the report at the end
 * report displays the name requested seats and available seats
 * program is implemented as follows :
 */

public class TicketBook //TicketBook class
{

	public static void main(String[] args) //main method
	{
		Reservation reserve = new Reservation(); //object of Reservation class
		System.out.println("----------------------------------");
		System.out.println("Bus Reservation System !");
		System.out.println("----------------------------------");
		Person thread1 = new Person(reserve, 3,"Soham"); 
		thread1.start(); //starting newly created thread
		Person thread2 = new Person(reserve, 13,"Malhar");
		thread2.start();  //starting newly created thread
		Person thread3 = new Person(reserve, 5,"Dixit");
		thread3.start();  //starting newly created thread
	}

}
class Reservation //Reservation class
{

    static int availableSeats = 10;   //static integer variable set to 9

    synchronized void reserveSeat(int requestedSeats,String pname)    //using synchronization
    {
        System.out.println(Thread.currentThread().getName() + " starts"); //status of thread
        
        System.out.println("Available Seats : " + availableSeats + " \nRequested Seats : " + requestedSeats+"\nName : " +pname);  //printing details
        if (availableSeats >= requestedSeats)  //if requested number of seats are available
        {
            System.out.println("Seat are Available, Reserve Now !");  //printing
            
            //exception handling
            try
            {
                Thread.sleep(100);     //currently executing thread sleeps for 100 milliseconds
            }
            catch (InterruptedException e)
            {
                System.out.println("Interruption occured in Thread"); 
            }
            System.out.println(requestedSeats + " seats reserved successfully !");
            availableSeats = availableSeats - requestedSeats;  //deducting the booked seats
        }
        else
        {
            System.out.println("Sorry,Requested number of seats are not available !");  //if available seats are less than requested seats
        }
        System.out.println(Thread.currentThread().getName() + " ends");  //thread ended is printed
        System.out.println("---------------------------------------");
    }
}

//Person class is inherited from thread interface

class Person extends Thread
{
	//required variables
    Reservation reserve;
    int requestedSeats;
    String strname;

    //parameterized constructor 
    public Person(Reservation reserve, int requestedSeats,String strn)  
    {
    	//initializing variables
        this.reserve = reserve;
        this.requestedSeats = requestedSeats;
        this.strname=strn;
    }

    @Override
    public void run() // run method for performing action on thread
    {
        reserve.reserveSeat(requestedSeats,strname);
    }
}