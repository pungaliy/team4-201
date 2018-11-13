import Methods.Magic;
import TabsStuff.TabsItem;
import TabsStuff.TabsLedger;
import temp.tempRoom;
import temp.tempUser;

import java.util.Vector;

public class CommandLineTest {
	private Magic magic = new Magic();

 	public void GroceryTests(){
		magic.addGrocery("fish room1", "1");
		magic.addGrocery("beef room1", "1");
		magic.addGrocery("milk room2", "2");
		System.out.println("Room1 (fish and beef):");
		magic.getGroceryList("1");
		System.out.println("Room2 (milk):");
		magic.getGroceryList("2");

		magic.removeGrocery("fish room1", "1");
		System.out.println("Room1 removed fish (only have beef)");
		magic.getGroceryList("1");
		magic.removeGrocery("beef room2", "1");
		System.out.println("Room1 remove beef fail (should still keep beef)");
		magic.getGroceryList("1");
	}

	/*public void TransactionTests(){
 		db.Room room = new db.Room("999");
		db.User user1 = new db.User("User1", "email1", "999");
		db.User user2 = new db.User("User2", "email2", "999");
		db.User user3 = new db.User("User3", "email3", "999");
		db.User user4 = new db.User("User4", "email4", "999");

		//Fish: bought by user1, split with user2, user3, not himself
		TabsItem fish = new TabsItem("Fish", 3, 16.5f);
		Vector<db.User> fishSplit = new Vector<db.User>();
		fishSplit.add(user2);
		fishSplit.add(user3);
		TabsLedger fishLedger = new TabsLedger(fish, user1, fishSplit);
		magic.addTransactionToAllSplitters(fishLedger);

		//Beef: bought by user2, split with user2 and user3
		TabsItem beef = new TabsItem("Beef", 3, 30.0f);
		Vector<db.User> beefSplit = new Vector<db.User>();
		beefSplit.add(user2);
		beefSplit.add(user3);
		TabsLedger beefLedger = new TabsLedger(beef, user2, beefSplit);
		magic.addTransactionToAllSplitters(beefLedger);

		System.out.println("Between user2 and user3 (should be -45)");
		System.out.println(magic.getTabsTotal(user2, user3));

		System.out.println("Between user3 and user2 (should be 45)");
		System.out.println(magic.getTabsTotal(user3, user2));

		System.out.println("Between user1 and user3 (should be -24.75)");
		System.out.println(magic.getTabsTotal(user1, user3));

		System.out.println("Between user2 and user4 (should be 0)");
		System.out.println(magic.getTabsTotal(user2, user4));


		//magic.printAllTransactions();

	}*/

	public static void main(String [] args){
 		CommandLineTest test = new CommandLineTest();
		//test.GroceryTests();
		//test.TransactionTests();

		//TRY TO MERGE WITH html
		//Merge sucessful I guess
	}
}
