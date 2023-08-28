package service;

import dao.Product;
import java.util.Scanner;

public class Runner {
	public static void main(String[] args) {
		
		int numProd=10; //Number of products to auto-generate with RNG
		Product.create(numProd); //Create and populate Database
		
		Scanner s = new Scanner(System.in); 
		int choice = 9;
		
		//While loop for Menu
		while(choice!=0){	
			switch(choice){	
			
			case 1 : //Display all Products
				for(int num=0;num<numProd;num++)
					Product.prodDB[num].display();
				Product.menu();
				choice = s.nextInt();
				break;
				
			case 2 : //Add a new Product
				Product.add(numProd);					
				numProd++;
				Product.menu();
				choice = s.nextInt();
				break;
				
			case 3 : //Delete a Product
				System.out.println("Enter Product Id of what you want to delete");
				int pid =s.nextInt();
				Product.delete(pid);
				System.out.println("Deletion Success");
				numProd--;
				Product.menu();
				choice = s.nextInt();
				break;
				
			case 4 : //Update a product of specified Product ID
				System.out.print("Enter the Product ID of the product you want to update: ");
				pid =s.nextInt();
				System.out.println("\nEnter the field of the detail of the product to update:");
				System.out.println("1: Product Name");
				System.out.println("2: Price");
				System.out.println("3: Quantity");
				System.out.println("4: Discount\n");
				int choiceInternal=s.nextInt();
				System.out.print("Enter the value: ");
				if(choiceInternal==1)
					Product.update(pid,s.nextLine());
				else if(choiceInternal==2 || choiceInternal==4)
					Product.update(pid,s.nextDouble(),choiceInternal);
				else if(choiceInternal==3)
					Product.update(pid,s.nextInt());
				else
					{System.out.println("Invalid Choice!");}
				Product.menu();
				choice = s.nextInt();
				break;
				
			case 5 : //Search a product by Product ID
				System.out.println("Enter the Produt ID");
				Product.search(s.nextInt());
				Product.menu();
				choice = s.nextInt();
				break;
				
			case 6 : //Search a product by Product Name
				System.out.println("Enter the Produt Name");
				Product.search(s.next());
				Product.menu();
				choice = s.nextInt();
				break;
				
			case 7 : //Display all products in specified order of their Price
				System.out.println("Enter the order to sort the products:\nA: Low to High\nB: High to Low");
				String orderSort=s.next();
				Product.displayInOrder(orderSort,0);
				Product.menu();
				choice = s.nextInt();
				break;
				
			case 8 : //Display all products in specified order of their Discount
				System.out.println("Enter the order to sort the products:\\nA: Low to High\\nB: High to Low");
				orderSort=s.next();
				Product.displayInOrder(orderSort,1);
				Product.menu();
				choice = s.nextInt();
				break;
				
			case 9 : //Reprint the Menu
				Product.menu();
				choice = s.nextInt();
				break;	
				
			default : 
				choice = s.nextInt();
				break;	 
			}
		}
		System.out.println("Program Complete!");
		s.close();
	}
}
