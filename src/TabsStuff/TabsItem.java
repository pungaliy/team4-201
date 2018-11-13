package TabsStuff;

public class TabsItem {
	private int quantity;
	private float pricePerItem;
	private String itemName;

	public TabsItem(String name, int quantity, float price){
		this.itemName = name;
		this.quantity = quantity;
		this.pricePerItem = price;
	}

	public int getQuantity(){
		return this.quantity;
	}

	public float getPricePerItem(){
		return this.pricePerItem;
	}

	public String getItemName(){
		return this.itemName;
	}
}
