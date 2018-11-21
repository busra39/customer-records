# customer-records

File directory should be provided.

If there is more than txt files in the directory, App reads all of them and stores into a HashMap of TreeSet.

Run Main to start the application.

---
Maven is used.

Log4j, Json and Jackson dependencies are added.

Java version is 8.

---
Description;
Customer Records
We have some customer records in a text file (customers.txt) -- one customer per line, JSON lines formatted. We want to invite any customer within 100km of our Dublin office for some food and drinks on us. Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).

You can use the first formula from this Wikipedia article to calculate distance. Don't forget, you'll need to convert degrees to radians.
The GPS coordinates for our Dublin office are 53.339428, -6.257664.

