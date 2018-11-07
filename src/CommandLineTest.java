import Methods.Magic;

public class CommandLineTest {
	private Magic magic = new Magic();

 	public void GroceryTests(){
		magic.addGrocery("fish room1", 1);
		magic.addGrocery("beef room1", 1);
		magic.addGrocery("milk room2", 2);
		System.out.println("Room1 (fish and beef):");
		magic.getGroceryList(1);
		System.out.println("Room2 (milk):");
		magic.getGroceryList(2);

		magic.removeGrocery("fish room1", 1);
		System.out.println("Room1 removed fish (only have beef)");
		magic.getGroceryList(1);
		magic.removeGrocery("beef room2", 1);
		System.out.println("Room1 remove beef fail (should still keep beef)");
		magic.getGroceryList(1);
	}

	public static void main(String [] args){
 		CommandLineTest test = new CommandLineTest();
		test.GroceryTests();
	}
}
