# Primaseller Java Assignment

Assignment is totally based on BookSales problem. In which we have to import two different CSV file and take args from command line and display data according to query args.

- Prerequisite
  
  Install JDK 8 or later version to compile and run the programme.
  
  ### Compilation Steps
    * javac  /directory/path/to/.java/files/BookSales.java
   
   After compile .class file will be generated
   
   ### Execution Command
   Change director to where .class file is generated
   * java BookSales --books=/path/to/books/csv/file/location/books.list --sales=/path/to/sales/csv/file/location/sales.list --top_selling_books=2 --top_customers=2 --sales_on_date=2018-08-01
   
    #### Note:
- To execute programme first two args are mandatory and always pass first args as --books=/path/to/books/csv/file/location/books.list and second args as --sales=/path/to/sales/csv/file/location/sales.list 
   
   (args order can't be changed)
- Rest three args are optional for result query below are the example
    ###### --top_selling_books=2 
    ###### --top_customers=2 
    ###### --sales_on_date=2018-08-01
- Below are book.list data details (No header is required in .csv file)

books.list is a list of books that the store has in its catalog. The books.list file is a CSV file
with each row representing a book. Each row has the following fields -
1. book_id - a unique alphanumeric string that identifies the book.
2. book_title - title of the book.
3. book_author - name of author of book.
4. book_price - a float representing the price of the book.
E.g

123A,A brief history of time, Stephen Hawking, 23.22

1S45, Hitchhiker's guide to the galaxy, Douglas Adams, 34.30
  
- Below are sales.list data details (No header is required in .csv file) 
  
sales.list is a list of sales that have been made for a book. The file is a CSV file which is
  structured as follows:
  Every row in the file represents a sale made.
  1. sale_date - a date in the form of YYYY-MM-DD indicating the date of sale.
  2. sale_email - email of the customer who purchased.
  3. sale_payment_method - method of payment - must be one of CASH or CARD
  4. sale_item_count - count of items (i.e books) purchased. This field determines how many
  more columns are to be read.
  
  5 onwards - each column contains a string that indicates the book_id and the quantity
  purchased.
  E.g
  
  2018-08-01,foo@bar.com,CASH,2,123A;3,1S45;1
  
  2018-08-02,john@doe.com,CARD,1,1S45;1
  
  2018-08-02,john@doe.com,CARD,1,123A;2
  
