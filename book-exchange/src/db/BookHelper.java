package db;

import model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**                                                                                                             
 * This class is a Data Access Object.  It's purpose is to handle                                               
 * querying/updating the application database for anything User                                                 
 * related.                                                                                                     
 * @author Team 2 Software, LLC                                                                                 
 */
public class BookHelper {
	private String JDBC_URL;
	private String bookID;
	
	protected Statement getBookByIdStatement;
	protected PreparedStatment getBooksBySellers0tatement;
	public Statement getAllBooksStatement;
	public Statement getBooksByClassStatement;
	public PreparedStatement getBooksFors0aleStatement;
	public PreparedStatement getBooksByDepartmentStatement;
	public PreparedStatement updateBookStatusStatement;
	public PreparedStatement deleteBookStatement;
	public PreparedStatement addBookStatement;
	
	private ResultSet rs0;
	private ResultSet rs1;
	
	private Connection conn;
	
	/**
	 * Constructor creates a driver manager and opens connection to database
	 */
	 public BookHelper(){
	 	try{

			JDBC_URL = "jdbc:mysql://172.17.152.110:3306/book-exchange";
			user     = "group2";
			password = "poopdeck";
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(JDBC_URL, user, password);
 			System.out.println("Successfully connected to database.");
    	}
    	catch(Exception e){
    		System.out.println(e.getClass().getName()+" opening database connection: "+e.getMessage());
    	}
	 
	 }//constructor
	 
	 
	 /**
	  * Retrieves a book listing from the database based on the ID
	  */
	 public Book getBookById(int bookId){
	 	int id;
	 	Book bookObj;	
		String query = "select bid, bookName, isbn, description, author, edition,"
						+ " department, status, bookCondition, price, classTitle"
						+ " from book-exchange.book"; //Is this the correct database name?
		
		try{
			/**
			 * Create MySQL prepared statement and execute
			 */
			getBookByIdStatement = conn.createStatement();
			rs0 = getBookByIdStatement.executeQuery(query);
			
			while(rs0.next()){
				/**
				 * Check if the bid field in table matches bookId variable
				 */
			    id = rs0.getInt("bid");
			    if(id.equals(bookId)){
				    /**
				     * create Book object with data from book table
				     */ 
				     String title = rs0.getString("bookName");
				     String isbn = rs0.getString("isbn");
				     String desc = rs0.getString("description");
				     String author = rs0.getString("author");
				     String ed = rs0.getString("edition");
				     String stat = rs0.getString("status");
				     String condition = rs0.getString("bookCondition");
				     String department = rs0.getString("department");
				     String classTitle = rs0.getString("classTitle");
				     double price = rs0.getDouble("price");
				     
				     //double check that this is the correct order of params
				     bookObj = new Book(bookId, title, isbn, desc, author, ed, stat, condition, price, department, classTitle);
				    break;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	 	
	 	return bookObj; //Return null if the book is not found
	 }//getBookById()
	 
	 
	 /**
	  * Retrieves all books sold by a specified seller
	  */
	 public ArrayList<Book> getBooksBySeller(String username){
	 	
	 
	 }//getBooksBySeller()
	 
	 
	 
	 /**
	  * Retrieves all books stored in database
	  */
	 public ArrayList<Book> getAllBooks(){
	 	ArrayList<Book> allBooks = new ArrayList<Book>();
	 	
		String query = "select bid, bookName, isbn, description, author, edition,"
						+ " department, status, bookCondition, price, classTitle"
						+ " from book-exchange.book"; //Is this the correct database name?
		
		try{
			/**
			 * Create MySQL prepared statement and execute
			 */
			getAllBooksStatement = conn.createStatement();
			rs0 = getAllBooksStatement.executeQuery(query);
			
			while(rs0.next()){
				/**
			     * Create Book object with data from one row in book table.
			     * Then add the Book object to an ArrayList.
			     */ 
			     String title = rs0.getString("bookName");
				 String isbn = rs0.getString("isbn");
			     String desc = rs0.getString("description");
			     String author = rs0.getString("author");
			     String ed = rs0.getString("edition");
			     String stat = rs0.getString("status");
			     String condition = rs0.getString("bookCondition");
			     String department = rs0.getString("department");
			     String classTitle = rs0.getString("classTitle");
			     double price = rs0.getDouble("price");
			     int id = rs0.getInt("bid");
				     
			     //double check that this is the correct order of params
			     Book bookObj = new Book(id, title, isbn, desc, author, ed, stat, condition, price, department, classTitle);
			     //Add bookObj to ArrayList
			     allBooks.add(bookObj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	 	
	 	return allBooks;
	 }//getAllBooks()
	 
	 
	 /**
	  * Retrieves all book currently listed for sale
	  */	 
	 public ArrayList<Book> getBooksFors0ale(){
	 	
	 }//getBooksFors0ale()



	 /**
	  * Retrieves all books for a certain department
	  */
	  public ArrayList<Book> getBooksByDepartment(String department){
	  	
	  }//getBooksByDepartment()
	  
	  
	  
	  
	  /**
	   * Retrieves all books for a certain class
	   */
	  public ArrayList<Book> getBooksByClass(String className){
	  	ArrayList<Book> booksList = new ArrayList<Book>();
	  	
	  	String query = "select bid, uid, bookName, isbn, description, author, edition,"
						+ " department, status, bookCondition, price, className"
						+ " from book-exchange.book"; 
	  	
	  	
	  	try{
			/**
			 * Create MySQL prepared statement and execute
			 */
			getBooksByClassStatement = conn.createStatement();
			rs1 = getBooksByClassStatement.executeQuery(query);
			
			while(rs1.next()){
				String classTitle = rs1.getString("className");
				if(className.equals(classTitle)){
				   /**
			     	* Create Book object with data from one row in book table
			     	* that contains the specified className.
			     	* Then add the Book object to an ArrayList.
			     	*/ 
			     	String userID = rs1.getString("uid");
			     	String title = rs1.getString("bookName");
				 	String isbn = rs1.getString("isbn");
				    String desc = rs1.getString("description");
				    String author = rs1.getString("author");
			   		String ed = rs1.getString("edition");
			    	String stat = rs1.getString("status");
				    String condition = rs1.getString("bookCondition");
				    String department = rs1.getString("department");
			    	double price = rs1.getDouble("price");
			     	int bookID = rs1.getInt("bid");

				     Book bookObj = new Book(uid, bookID, title, isbn, desc, author, ed, stat, condition, price, classTitle, department);
				     //Add bookObj to ArrayList
			    	 booksList.add(bookObj);
			
				}
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	  	
	  	return booksList;
	  }//getBooksByClass()


	 /**
	  * Takes in Book object and places data in the book table.
	  * @return int is the id of newly added book
	  */
	public int addBook(Book newBook){
		/**
		 * Get values from newBook object
		 */
		int bookID = newBook.getID();
       	double price = newBook.getPrice();
		String isbn = newBook.getISBN();
       	String title = newBook.getTitle();
       	String desc = newBook.getDescription();
       	String author = newBook.getAuthor();
       	String ed = newBook.getEdition();
       	String status = newBook.getStatus(); //Does this specify that book is for sale, sold, or traded?
       	String condition = newBook.getCondition();
       	//TODO Still need to add the following methods to Book.java; also add "classTitle" field to database
       	//TODO in Book.java, change "price" from int to double; also in BookHelperTest 
       	String department = newBook.getDepartment();
       	String classTitle = newBook.getClassTitle();
		  
		 try{
		  /**
		   * Create a MySQL insert prepared statement and execute
		   */
		String query = " insert into book (bid, bookName, isbn, description, author, edition",
					+ " department, status, bookCondition, price, classTitle)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		addBookStatement = conn.prepareStatement(query);
			addBookStatement.setInt(1, bookID);
    		addBookStatement.setString(2, title);
    		addBookStatement.setString(3, isbn); 
    		addBookStatement.setString(4, desc);
    		addBookStatement.setString(5, author);
    		addBookStatement.setString(6, ed);
    		addBookStatement.setString(7, department);
    		addBookStatement.setString(8, status);
    		addBookStatement.setString(9, condition);
    		addBookStatement.setDouble(10, price);
    		addBookStatement.setString(11, classTitle);
    					
    		addBookStatement.execute();
    					
		}catch (Exception e) {
	         e.printStackTrace();
	    }		
	}//addBook()
	   
	
	
	/**
	 * Updates a book's status in the database
	 */
	 public void updateBookStatus(int bookId, String newStatus){
	 
		try{
			/**
		   	 * Create a MySQL insert prepared statement and execute
		     */
			String query = "UPDATE book "
        					+ "SET status=\"" + newStatus + "\" "
        					+ "WHERE bid=" + bookId;
					
			updateBookStatusStatement = conn.prepareStatement(query);
    		updateBookStatusStatement.execute();
    					
		}catch (Exception e) {
	         e.printStackTrace();
	    }
	 }//updateBookStatus()
	   
	   
	   
	 /**
	  * Deletes a book from the database
	  */	  
	public void deleteBook(int bookId){
		String query = "DELETE FROM book WHERE bid = '"+ bookId +"' ";
		
		try{
			deleteBookStatement = conn.prepareStatement(query);
			deleteBookStatement.execute();
			//System.out.println("book " + bookId + " removed.");
		}catch(Exception e){
			e.printStackTrace();
		}	
	}//deleteBook()
	   
}