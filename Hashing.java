/*
 NAME:           SHARVARI SONKUSARE


 CNUM:           C22019221458
 ASSIGNMENT:     6
 ROLL NO.:       2459
 
 PROBLEM STATEMENT:Implement a hash table for storing bank account information. Handle the collision using
 linear probing without replacement. Perform operations on it.
 
 */


package hashing;
import java.util.*;

class Customer {
	long id;
	String name;
	double balance;

	public Customer(long id, String name, Double balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}
}
class HashTable {
	int size;
	Customer[] hashArray;
	HashTable() {
		hashArray= new Customer[80];
	}
	Scanner sc = new Scanner(System.in);
	
	int calculate_hash(long key){
		int hashVal = (int)key%size;
		return hashVal;
	}
	
	boolean isFull() {     //TO CHECK IF HASHTABLE IS FULL OR NOT
		for(int i=0;i<size;i++) {
			if(hashArray[i]==null) {
				return false;
			}
		}
		return true;
	}
	
	boolean isEmpty() {    //TO CHECK IF HASHTABLE IS EMPTY OR NOT
		for(int i=0;i<size;i++) {
			if(hashArray[i]!=null) {
				return false;
			}
		}
		return true;
	}
	
	void create(){
		System.out.println("Enter the number of customers:");
		size = sc.nextInt();
		for(int i = 0;i<size;i++) {
			hashArray[i] =null;
		}
		
		int option = 1;    //KEEP INSERING TILL OPTION=0
		while(option!=0) {
			insert();       //CALLING INSERT METHOD HERE
			System.out.println("Do you want to continue?(1/0):");
			option = sc.nextInt();
		}
	}
	
	void insert() {
		if(isFull()) {
			System.out.println("Cannot insert more customers. Hashtable is full!");
			return;
		}
		long id;
		String name;
		double balance;
		System.out.println("Enter Customer details:-\nEnter ID:");
		id = sc.nextLong();
		System.out.println("NAME:");
		name= sc.next();
		System.out.println("BALANCE:");
		balance = sc.nextDouble();
		Customer newCustomer = new Customer(id,name,balance);
		int hashVal = calculate_hash(id);   //CALCULATING HASH VALUE USING ID AS KEY
		while(hashArray[hashVal]!=null) {   //IF DATA AT HASHVALUE INDEX NOT EMPTY
			hashVal = (hashVal+1)%size;     //WRAP AROUND THE HASHTABLE
		}
		hashArray[hashVal] =newCustomer;
	}
	void display(){
		System.out.println("HASH_VALUE\tID\tNAME\tBALANCE");
		for(int i=0;i<size;i++) {
			if(hashArray[i]==null) {
				continue;
			}
			System.out.println(i+"\t\t"+hashArray[i].id+"\t"+hashArray[i].name+"\t"+hashArray[i].balance);
		}
	}
	Customer search(){
		System.out.println("Enter ID of the customer to be searched:");
		long keyID = sc.nextLong();
		int hashVal = calculate_hash(keyID);
		int count =0;
		while(hashArray[hashVal]!=null) {    //END OF THE HASHTABLE
			
			if(hashArray[hashVal].id==keyID) {  //IF ID FOUND
				return hashArray[hashVal];
			}
			
			hashVal = (hashVal+1)%size;  //WRAP AROUND THE HASHTABLE
			count++;
			if(count==size) {    //IF REACHED THE END
				break; 
			}
		}
		return null;
	}
	void delete(){
		if(isEmpty()) {  //IF HASHTABLE IS EMPTY
			System.out.println("Hashtable is empty therfore cannot delete any data");
		}
		else {
		System.out.println("Enter ID of the customer to be deleted:");
		long ID = sc.nextLong();
		int hashVal = calculate_hash(ID);
		while(hashArray[hashVal]!=null) {
			if(hashArray[hashVal].id==ID) {
				hashArray[hashVal] = null;
				return;
			}
			
			hashVal = (hashVal+1)%size;
		}
		
		System.out.println("Customer not present in the database!");
		
		
	}
	}
}

public class Hashing {
	public static void main(String args[]) {
		HashTable h = new HashTable();
		Scanner sc=new Scanner(System.in);
		int choice;
		do
		{
		System.out.println("------MENU------\n1.CREATE\n2.DISPLAY\n3.SEARCH\n4.DELETE\n5.INSERT\n0.EXIT");
		System.out.println("Enter your choice");
		choice=sc.nextInt();
		switch(choice)
		{
		case 1: 
			h.create();
			break;
		case 2:
			h.display();
			break;
		case 3:
			Customer present = h.search();
			if(present==null) {
				System.out.println("CUSTOMER NOT FOUND!");
			}
			else {
				System.out.println("CUSTOMER FOUND!\nID:"+present.id+"\nNAME:"+present.name+"\nBALANCE:"+present.balance);
			}
			break;
		case 4:
			h.delete();
			break;
		case 5:
			h.insert();
			break;
		case 0:
			
			break;
		default:
			System.out.println("INVALID CHOICE. ENTER OTHER CHOICE.");
		}
		}while(choice!=0);
	}

}

/*
 
 TIME COMPLEXITIES:
 1. Insert - O(N)
 2. Display - O(N)
 3. Search - O(N)
 4. Delete - O(N)
 
OUTPUT:
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
4
Hashtable is empty therefore cannot delete any data
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
1
Enter the number of customers:
10
Enter Customer details:-
Enter ID:
19
NAME:
KAI
BALANCE:
100
Do you want to continue?(1/0):
1
Enter Customer details:-
Enter ID:
32
NAME:
V
BALANCE:
200
Do you want to continue?(1/0):
1
Enter Customer details:-
Enter ID:
34
NAME:
IU
BALANCE:
300
Do you want to continue?(1/0):
1
Enter Customer details:-
Enter ID:
41
NAME:
JENNIE
BALANCE:
400
Do you want to continue?(1/0):
1
Enter Customer details:-
Enter ID:
63
NAME:
LISA
BALANCE:
500
Do you want to continue?(1/0):
1
Enter Customer details:-
Enter ID:
85
NAME:
JK
BALANCE:
600
Do you want to continue?(1/0):
1
Enter Customer details:-
Enter ID:
88
NAME:
JACKSON
BALANCE:
700
Do you want to continue?(1/0):
0
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
2
HASH_VALUE	ID	NAME	  BALANCE
1		    41	JENNIE	  400.0
2		    32	V	      200.0
3		    63	LISA	  500.0
4		    34	IU	      300.0
5		    85	JK	      600.0
8		    88	JACKSON   700.0
9		    19	KAI	      100.0
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
5
Enter Customer details:-
Enter ID:
67
NAME:
JCW
BALANCE:
800
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
5
Enter Customer details:-
Enter ID:
55
NAME:
JIN
BALANCE:
900
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
2
HASH_VALUE	ID	NAME	  BALANCE
1		    41	JENNIE	  400.0
2		    32	V	      200.0
3		    63	LISA	  500.0
4		    34	IU	      300.0
5		    85	JK	      600.0
6		    55	JIN	      900.0
7		    67	JCW	      800.0
8		    88	JACKSON   700.0
9		    19	KAI	      100.0
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
5
Enter Customer details:-
Enter ID:
20
NAME:
HYUNA
BALANCE:
1000
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
2
HASH_VALUE	ID	NAME	  BALANCE
0		    20	HYUNA	  1000.0
1		    41	JENNIE	  400.0
2		    32	V	      200.0
3		    63	LISA	  500.0
4		    34	IU	      300.0
5		    85	JK	      600.0
6		    55	JIN	      900.0
7		    67	JCW	      800.0
8		    88	JACKSON   700.0
9		    19	KAI	      100.0
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
5
Cannot insert more customers. Hashtable is full!
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
3
Enter ID of the customer to be searched:
55
CUSTOMER FOUND!
ID:55
NAME:JIN
BALANCE:900.0
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
3
Enter ID of the customer to be searched:
45
CUSTOMER NOT FOUND!
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
4
Enter ID of the customer to be deleted:
63
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
Enter your choice
2
HASH_VALUE	ID	NAME	  BALANCE
0		    20	HYUNA	  1000.0
1		    41	JENNIE	  400.0
2		    32	V	      200.0
4		    34	IU	      300.0
5		    85	JK	      600.0
6		    55	JIN	      900.0
7		    67	JCW	      800.0
8		    88	JACKSON   700.0
9		    19	KAI	      100.0
------MENU------
1.CREATE
2.DISPLAY
3.SEARCH
4.DELETE
5.INSERT
0.EXIT
0


*/



