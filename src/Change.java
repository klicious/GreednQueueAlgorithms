import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Change {

	public static void main(String arg) {
		//parse input
		String[] lines = arg.split(" ");
		
		int n = Integer.parseInt(lines[0].trim());
		
		int[] coins = new int[n];
		int[] coinCount = new int[n];
		
		for(int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(lines[i+1]);
			coinCount[i] = 0;
		}
		
		int price = Integer.parseInt(lines[n + 1]);
		
		int paid = Integer.parseInt(lines[n + 2]);
		
		//Check Input
		StringBuilder sb = new StringBuilder();
		System.out.println("===== Examine input =====");
		System.out.println("Coin types = " + n);
		for(int i = 0; i < coins.length; i++) {
			System.out.println("coins["+ i +"] = " + coins[i] + " :: coinCount[" + i +"] = " + coinCount[i]);
		}
		System.out.println("price = " + price);
		System.out.println("paid = " + paid);
		System.out.println("=========================");
		
		/*
		 * Greed Algorithm is in three distinct process
		 * 1. Select
		 * 	Choose the biggest change possible to reduce the total count of changes.
		 * 2. Execute Examination
		 * 	If the net-value exceeds what we should give back to the customer, revert back one step and choose a coin with smaller value
		 * 3. Select Examination
		 * 	
		 */
		int biggestCoinIndex = 0;
		int sum = 0;
		int lastVallue = Integer.MAX_VALUE;
		int change = paid - price;
		
		if (change < 0) {
			System.out.println("The customer is trying to scam you. Please call 911 for the robbery");
		} else if (change == 0) {
			System.out.println("Your customer is nice enough to give you the exact price of the product. All Heil THE Customer!");
		}
		
		System.out.println("Change = " + change + " :: Sum = " + sum);
		System.out.println("===== Beginning of the Proces ======");
		while(sum != change && biggestCoinIndex < coins.length) {
			if(sum < change) {
				sum += coins[biggestCoinIndex];
				coinCount[biggestCoinIndex]++;
			} else if (sum > change) {
				sum -= coins[biggestCoinIndex];
				coinCount[biggestCoinIndex]--;
				biggestCoinIndex++;
			}
			System.out.println("Sum = " + sum + " :: Change = " + change);
		}
		System.out.println("===== End of the Proces ======");
		
		// parse result
		String result;
		StringBuilder r = new StringBuilder();
		for(int i = 0; i < coinCount.length; i++) {
			r.append(coinCount[i]);
			r.append(" ");
		}
		result = r.toString().trim();
		
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

}
