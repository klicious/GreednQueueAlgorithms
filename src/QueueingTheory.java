import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class QueueingTheory {

	public static void main(String arg) {
		// Queuing Theory ..
		/*
		 * Single Queueing nodes
		 * 	Usually described using 'Kendall's notation' in the form A/S/C
		 * 		A - time between arrivals in queue
		 * 		S - size of jobs
		 * 		C - number of server at the node
		 * 	M/D/k queueing model
		 * 		M - Markov, or memoryless - arrivals occur according to a Poisson process
		 * 		D - deterministic - jobs arriving at the queue require a fixed amount of service
		 * 		k - number of servers at the queueing node (k = 1, 2, ...). If there are more jobs at the node than there are servers then jobs will queue and wait for service.
		 * 	M/M/1 queue is a simple model where a single server serves jobs that arrive according to Poisson process and have exponentially distributed service requirements.
		 * 	M/G/1 queue, the G stands for general and indicates an arbitrary probability distribution. 
		 * 
		 * Queueing networks - Networks of queues are system in which a number of queues are connected by customer routing.
		 * 	For network of 'm' the state of the system can be described by an m-dimensional vector(x1 ~ xm) where xi represents the number of customers at each node.
		 * 	If the total number of customers in the network remains constant the network is called a closed network and has also been shown to have a product-form stationary distribution in the "Gordon-Newell theorem"
		 * 	
		 * 	Example of M/M/1 (Birth and Death process)
		 * 		A/B/C
		 * 			A: distribution of arrival time
		 * 			B: distribution of service time
		 * 			C: the number of parallel servers
		 * 		A system of inter-arrival time and service time showed exponential distribution, we denoted M.
		 * 			λ： the average arrival rate
		 * 			µ： the average service rate of a single service
		 * 			P: the probability of n customers in system
		 * 			n: the number of people in system
		 * 
		 * Customers Queueing for the Service
		 * 	Arrival process
		 * 		- how customers arrive e.g. singly or in groups (batch or bulk arrivals)
		 * 		- how the arrivals are distributed in time
		 * 		- whether there is a finite population of customers of (effectively) and infinite number
		 * 		
		 * 		The simplest arrival process is one where we have completely regular arrivals (i.e. the same constant time interval between successive arrivals).
		 * 		A Poisson stream of arrivals corresponds to arrivals at random. In Poisson stream successive customers arrive after intervals which independently are exponentially distributed. The Poisson stream is important as it is a convenient mathematical model of many real life queueing systems and is described by a single parameter - the average arrival rate.
		 * 		Other important arrival processes are schedule arrivals; batch arrivals; and time dependent arrival rate (i.e. the arrival rate varies according to the time of day).
		 * 	Service Mechanism
		 * 		- a description of the resources needed for service to begin
		 * 		- how long the service will take (the service time distribution)
		 *  	- the number of servers available
		 *  	- whether the servers are in series or in parallel.
		 *  	- whether preemption is allowed (a server can stop processing a customer to deal with another "emergency" customer)
		 *  
		 *  	Assuming that the service times for customers are independent and do not depend upon the arrival process is common.
		 *  	Another common assumption about service times is that they are exponentially distributed.
		 *  Queue Characteristics
		 *  	- how, from the set of customers waiting for service, do we choose the one to be served next (e.g. FIFO; LIFO; randomly) (this is often called queue discipline)
		 *  	- do we have:
		 *  		- balking (customers deciding not to join the queue if it is too long)
		 *  		- reneging (customers leave the queue if they have waited too long for service)
		 *  		- jockeying (customers switch between queues if they think they will get served faster by so doing)
		 *  		- a queue of finite capacity or (effectively) of infinite capacity.
		 *  
		 *  	Changing the queue discipline (the rule by which we select the next customer to be served) can often reduce congestion.
		 *  	Often the queue discipline "choose the customer with the lowest service time" results in the smallest value for the time(on average) a customer spends queueing..
		 * 
		 * Note here that integral to queueing situations is he idea of uncertainty in, for example, interarrival times and service times. This mens that probability and statistics are needed to analyze queueing situations.
		 * 
		 * In terms of the analysis of queueing situations the types of questions in which we are interested are typically concerned with measures of system performance and might include:
		 * 	- How long does a customer expect to wait in the queue before they are served, and how long will they have to wait before the service is complete?
		 * 	- What is the probability of a customer having to wait longer than a given time interval before they are served?
		 *  - What is the average length of the queue?
		 *  - What is the probability that the queue will exceed a certain length?
		 *  - What is the expected utilization of the server and the expected time period during which he will be fully occupied (remember servers cost us money so we need to keep them busy). In fact, if we can assign costs to factors such as customer waiting time and server idle time then we can investigate how to design a system at minimum total cost.
		 *  
		 * These are questions that need to be answered so that management can evaluate alternatives in an attempt to control/improve the situation. Some of the problems that are often investigated in practice are:
		 * 	- Is it worthwhile to invest effort in reducing the service time?
		 * 	- How many servers should be employed?
		 * 	- Should priorities for certain type of customers be introduced?
		 *  - Is the waiting area for customers adequate?
		 *  
		 * Queueing notation and a simple example
		 * 	It is common to use the symbols:
		 * 		- λ: mean(or average) number of arrivals per time period, i.e. the mean arrival rate
		 *  	- µ: mean(or average) number of customers served per time period, i.e. the mean service rate
		 *  There is a standard notation system to classify queueing systems as A/B/C/D/E, where:
		 *  	- A: the probability distribution for the arrival process
		 *  	- B: the probability distribution for the service process
		 *  	- C: the number of channels (servers)
		 *  	- D: the maximum number of customers allowed in the queueing system (either being served or waiting for service)
		 *  	- E: the maximum number of customers in total.
		 *  Common options for A and B are:
		 *  	- M: Poisson arrival distribution (exponential inter-arrival distribution) or a exponential service time distribution
		 *  	- D: deterministic or constant value
		 *  	- G: general distribution (but with a known mean and variance)
		 *  if D and E are not specified, then it is assumed that they are infinite.
		 *  
		 *  For simple queueing system there are exact formulae that give the statistics under assumption that the system has reached a steady state.
		 *  	steady state - that is that the system has been running long enough so as to settle down into some kind of equilibrium position.
		 *  
		 *  One factor that is of note is traffic intensity = (arrival rate)/(departure rate) where arrival rate = number of arrivals per unit time and departure rate = number of departures per unit time. 
		 *  	Traffic intensity is a measure of the congestion of the system. If it is near to zero there is very little queuing and in general as the traffic intensity increases (to near 1 or even greater than 1) the amount of queuing increases.
		 *  
		 *  Fast server vs Multiple servers
		 *  	It can be seen that with one server working twice as fast customers spend less time in the system on average, but have to wait longer for service and also have a higher probability of having to wait for service.
		 *			                       One server twice as fast   Two servers, original rate
		 *	Average time in the system                  0.1333                     0.2510
		 *	(waiting and being served) 
		 *	Average time in the queue                   0.0083                     0.0010
		 *	Probability of having to wait for service   6.25%                      0.7353%
		 *	
		 *	
		 */
		
		// INPUT variables e.g) 3 4 30
		String line = arg.replaceAll(System.lineSeparator(), " ").trim();
		
		String[] lines = line.split(" ");
		
		int timeCustomerArrival = Integer.parseInt(lines[0].trim());
		int timeService = Integer.parseInt(lines[1].trim());
		int timeExperiment = Integer.parseInt(lines[2].trim());
		
		// OUTPUT variables e.g) 12 5 5
		int customerServiceComplete = 0;
		int remainingQueue = 0;
		int maxQueue = 0;
			
		Queue<Integer> q = new ArrayDeque<>();
		
		int customerInService = Integer.MIN_VALUE;
		int customerTotal = 0;
		int time = 0;
		
		while(time <= timeExperiment) {
			System.out.println("===== time = " + time + " =====");
			System.out.println("time = " + time);
			// Customer arrives periodically
			if(time%timeCustomerArrival == 0) {
				q.add(time);
				customerTotal++;
				if(q.size() > maxQueue) maxQueue = q.size();
				System.out.println("adding customer to queue...");
				System.out.println("Max in Queue = " + maxQueue);
				System.out.println("Total customers = " + customerTotal);
			}
			// service available, no-one in queue.
			if(customerInService < 0) {
				System.out.println("service available!!!");
				if(!q.isEmpty()) {
					customerInService = q.remove();
					System.out.println("new customer to the service :: customer = " + customerInService);
				}
			}
			// service unavailable. need to go in queue.
			else {
				System.out.println("service unavailable... :: Currently occupied by [" + customerInService + "]");
				if(customerInService + timeService == time) {
					System.out.println("curent service on customer complete!");
					if(q.isEmpty()) customerInService = Integer.MIN_VALUE;
					else {
						q.remove();
						customerInService = time;
					}
					customerServiceComplete++;
					System.out.println("currently in service = " + customerInService);
					System.out.println("# of post-service customers thus far = " + customerServiceComplete);
				}
			}
			System.out.println("Currently in Queue = " + q.size());
			time++;
		}
		remainingQueue = q.size();
		
		String result = customerServiceComplete + " " + remainingQueue + " " + maxQueue;
		
		// write to output file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			writer.println(result);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			writer.close();
		}
		
	}
	
	private int rng(int min, int max) {
		if(max < min) {
			return -1;
		}
		int range = max - min;
		Random r = new Random();
		return min + r.nextInt(range);
	}

}
