package dao;

import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;

public class Product {
	int prodId;
	String prodName;
	double price;
	int quantity;
	double discount;
	public static int prodIndex=0;
	public static Product prodDB[] = new Product[10]; // Array of Product Objects
	
	public Product(){
		Random R = new Random();
		prodId = R.nextInt(1, 1000); 
		price =  Math.round(R.nextDouble(1000)*100.0)/100.0;
		discount = Math.round(R.nextDouble(50)*100.0)/100.0;
		quantity = R.nextInt(1,50);
		prodName = generateString();
		prodIndex++;
	}
	
	//Method to generate Product Name
	private String generateString() { 
		char nameArr [] = new char[10];
		Random R = new Random();
		nameArr[0] = (char)R.nextInt(65, 91); // Capitalize first letter
		for(int i=1;i<nameArr.length;i++)
			nameArr[i] = (char) R.nextInt(97, 123);
		String Name = new String(nameArr);
		return Name;
	}
	
	//Method to display Choice Menu in Runner Class
	public static void menu(){
		System.out.println();
		System.out.println("Enter the choice corresponding to the operation to perform : ");
		System.out.println();
		System.out.println("1: Display all products");
		System.out.println("2: Add a new product");
		System.out.println("3: Delete a product of a specified Product ID");
		System.out.println("4: Update a product of a specified Product ID");
		System.out.println("5: Search a product by specified Product ID");
		System.out.println("6: Search a product by Name");
		System.out.println("7: Display all products in order of price");
		System.out.println("8: Display all products in order of discount");
		System.out.println("9: Restart the Application");
		System.out.println("0: Exit");
		System.out.println();
		return;
	}

	//Method to populate Database
	public static void create(int numProd){ 
		for(int i=0;i<numProd;i++)
			prodDB[i] = new Product();
		return;
	}
	
	//Method to display a Product
	public void display(){
		System.out.println("Product ID : " + prodId + "\nProduct Name : " + prodName + "\nPrice : " + price + "\nQuantity : " + quantity + "\nDiscount : " + discount);
	}
	
	//Method to add a new Product
	public static void add(int numProd){	
		if(numProd>=prodDB.length)
			expandDB();
			
		prodDB[numProd] = new Product();
		System.out.println();
		System.out.println("The new Product has been added successfully!");
		prodDB[numProd].display();
	}
	
	//Method to expand the Database to add new Product		
	public static void expandDB(){ 
		Product tempDB[] = new Product [prodDB.length];
		for(int i=0; i<prodDB.length;i++)
			tempDB[i]=prodDB[i];
		
		prodDB = new Product[tempDB.length+1];
		for(int i=0; i<tempDB.length;i++)
			prodDB[i]=tempDB[i];
	}
	
	//Method to delete an object of specified Product ID
	public static void delete(int Id){
		try {
			int i;
			for( i=0;i<prodIndex;i++){
				if(prodDB[i].prodId==Id){	
					prodDB[i]=null;
					return;
				}
			}
			if(i==prodIndex)
				throw new ProductNotFoundException();
		}
		catch(ProductNotFoundException e)
			{System.out.println("No such Product.");}
		return;
	}

	//Method to update a Product's quantity  of given Product ID
	public static void update(int id, int value){
		try {
			int i;
			for( i=0;i<prodIndex;i++)
				if(prodDB[i].prodId==id){
					prodDB[i].quantity=value;
					return;
				}
			
			if(i==prodIndex)
				throw new ProductNotFoundException();
		}
		catch(ProductNotFoundException e)
			{System.out.println("No such Product.");}
		
		return;
	}
	
	//Method to update Product Name
	public static void update(int id,String str){
		try {
			int i;
			for( i=0;i<prodIndex;i++)
				if(prodDB[i].prodId==id){
					prodDB[i].prodName=str;
					return;
				}
	
			if(i==prodIndex)
				throw new ProductNotFoundException();
		}
		catch(ProductNotFoundException e)
			{System.out.println("No such Product.");}
		
		return;
	}
	
	//Method to update Product's Price or Discount based on choice
	public static void update(int id,double value,int choice){
		try {
			int i;
			for( i=0;i<prodIndex;i++){
				if(prodDB[i].prodId==id){
					if(choice==2)
						prodDB[i].price=value;
				else if(choice==4)
					prodDB[i].discount=value;
				
				return;
				}
			}
		if(i==prodIndex)
			throw new ProductNotFoundException();
		}
		catch(ProductNotFoundException e)
			{System.out.println("No such Product.");}
		
		return;
	}
	
	//Method to Search by Id
	public static void search(int Id){
		try{
			int i;
			for( i=0;i<prodIndex;i++){
				if(prodDB[i].prodId==Id){
					prodDB[i].display();
					return;
				}
			}
		if(i==prodIndex)
			throw new ProductNotFoundException();
		}
		catch(ProductNotFoundException e)
			{System.out.println("No such Product.");}
		
		return;
	}
	
	//Method to search by Name (overloaded method)
	public static void search(String name){
		try {
			int i;
			for( i=0;i<prodIndex;i++){
				if(prodDB[i].prodName.equals(name)){
					prodDB[i].display();
					return;
				}
			
			}
			if(i==prodIndex)
				throw new ProductNotFoundException();
		}
		catch(ProductNotFoundException e)
			{System.out.println("No such Product.");}
		
		return;
	}

	//Method to display Products in a specified order by a specified field
	public static void displayInOrder(String ch,int choice){
		if(choice==0) {
			Arrays.sort(prodDB, new Comparator<Product>() {
	            @Override
	            public int compare(Product first, Product second){
	                return first.prodId - second.prodId;
	            }
	        });
		}else if(choice==1) {
			Arrays.sort(prodDB, new Comparator<Product>() {
	            @Override
	            public int compare(Product first, Product second){
	                return (int)(first.discount - second.discount);
	            }
	        });
		}
		
		if(ch.equals("A"))
			for(int t=0;t<prodIndex;t++)
				prodDB[t].display();
		else if(ch.equals("D"))
			for(int t=prodIndex-1;t>=0;t--)
				prodDB[t].display();
	}
}
