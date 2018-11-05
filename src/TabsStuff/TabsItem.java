package TabsStuff;

public class TabsItem extends Item {
	private int quantity;
	private float pricePerItem;

	public TabsItem(String name, int quantity, float price){
		super(name);
		this.quantity = quantity;
		this.pricePerItem = price;
	}


	public int getQuantity(){
		return this.quantity;
	}

	public float getPricePerItem(){
		return this.pricePerItem;
	}
}
