
package excelReading;

public class CustomerNode {
	private int x;
	private int y;
	private String shape;
	
	public void addCustomer(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public CustomerNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public CustomerNode(int x, int y, String shape) {
		this.x = x;
		this.y = y;
		this.shape = shape;
	}
}
