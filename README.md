# Interview-IntegraDev

## About The System


This is a library cataloguing system that is designed for librarians to manage and categorise their collections. The system can perform the following actions.

- Create a book item
- Add an author to a book item
- Register a new book type for a given book item
- Register a new book copy for a given book type

## System Design

The system follows the MVC pattern and uses JSP frontend, Java backend and PostgreSQL as the database.

The system was designed with a mind of extensibility, manageability, low coupling and high cohesion.
- Extensibility: 
  - With future extensions, new item types could be added to the system, e.g., movie, research paper, computer.
- Manageability: 
  - The domain model and database were structured in a way such that updating a record could be performed with minimum effort.
- Low coupling and high cohesion: 
  - Data Mapper layer separates the internal domain logic from the database and has a design of one database table per mapper class in the system.
  - Similarly with the controller layer.
  
The following figures are the domain class diagram and ER diagrams for the system (note: These are not proper domain classes or ER diagrams and are for illustration purposes only.)










## Design Decision

From the figures above, the system could be separated into three layers, the item layer, item type layer (book) and copy layer. See the following paragraph for the explanation of each layer.

I'll take the title "Harry potter series 5" as an example.

Item layer was designed to store the root attributes of a book, the book title.

E.g.,
| Item | Title  | Type  |
| ---- | ------ | ----- | 
| Item 1 | Harry potter series 5 - Harry Potter and the Order of the Phoenix | book |


Under item layer, a book title could be published by different publishers or have different versions of the same book, and each will be assigned to a unique ISBN.

E.g.,
| Harry potter series 5 | ISBN  | Publisher  | Version |
| --------------------- | ----- | ---------- | --------|
| Book 1 | 9780807220283 | Random House Audio Publishing Group | 1|
| Book 2 | 1408855933 |  Bloomsbury |1|
| Book 3 | 1808853533 |  Bloomsbury |2|


The library may have multiple book copies for each book ISBN 

E.g.,
| Copies | ISBN  |
| ------ | ----- |
| Copy 1 | 9780807220283 |
| Copy 2 | 9780807220283 |
| Copy 3 | 9780807220283 |



Pros and cons of the design

Pros
- Attributes are distributed into different tables, with the lower layer containing the information from the upper layer(s). In this way, it will minimise the effort of updating a record in which only one update SQL query is needed.
- With a hierarchy database design, the system is open for future extensions, such as adding a new item type.
- The relationship between the domain model and the tables is straightforward: one table per class in the hierarchy (less confusion).

Cons
- Multiple queries are required to load an object into the memory. Such as, loading a book copy will also require loading book and item objects into the memory.  


By purely focusing on performance, a better way of design is to have a book table that stores the entire book attribute in a single book record. However, since the system aims to use by librarians to manage and categorise their collections. 1. it is expected that there won't be too many users using the system at the same time (concurrency, lock map, not implement). 2. manageability is a key requirement for the system. 3. The ability to add new item types in the future. Hence, the system should prioritise manageability over performance. Nonetheless, the performance issue could be mitigated with the implementation of lazy load and identity map (both not implemented).
