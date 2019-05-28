Instructions for use:

Run in the terminal by compiling the project, navigating to 
bin and typing: scala sparkzilla.sol.Browser
Or you can run it in Eclipse by navigating to the Browser.scala
file and pressing Shift, Alt, X simultaneously. Then release them 
all at once and quickly press S.

Then once you have it running just follow the on-screen 
instructions. Try going to a url such as http://stilgar/Index !!!


Overview of the #PiecesOfThePuzzle

Browser.scala class: contains all methods and fields that we need 
					 for user I/O and networking and caching

Browser.scala companion object: This bad boy here has the best
								while loop repl you've ever seen.
								It gets user input, calls functions,
								and takes no prisoners. It is your key
								to the servers. Essentially all user 
								interaction happens in the main method
								while loop. #legit

Page.scala class : we decided to make our page class just as a tool for
				   organizing data. The lack of methods is #intentional 
				   because we made a style decision to keep it clean and tidy,
				   and leave the methods to Browser. 
				   The page class stores the host for requests and an 
				   HTMLElement List for the page, which is used for rendering 
				   and etc.

Browser class has the methods.
Browser object is for the repl.
Page is a way to format and hold Page data.
The rest of the things that extend HTMLElement exist for clean structuring
and were given to us.
We use the clickable trait as an augmentation of that clean structuring and
it fits into that framework of structuring.



EXTRAS/NOTEXTRAS

No features omitted. No known bugs. No extra features.

Collaborators:

Alex Rothberg & Ivan Zhao

Testing:
Ok tbh I don't think that we have any non-trivial methods that could be 
unit tested.


System Testing:

************Test Back button to home page and beyond*********

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:
1
No pages to go back to
Rendering page...
-----------------------

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:
2
Enter URL:http://stilgar/Index

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
1
No pages to go back to
Rendering page...
-----------------------

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:

***********TEST GO TO PAGE GO TO NEW PAGE THEN GO BACK THROUGH THEM******



Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:
2
Enter URL:http://stilgar/Index

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
6

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to Nim!
There are currently 21 matches.  How many would you like to remove?
<One>(4)
<Two>(5)
<Three>(6)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 6) Page Elements:
2
Enter URL:http://thufir/Index

Connecting to thufir: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the Server on thufir!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
1
Rendering Page...
-------------

Welcome to Nim!
There are currently 21 matches.  How many would you like to remove?
<One>(4)
<Two>(5)
<Three>(6)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 6) Page Elements:
1
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
1
No pages to go back to
Rendering page...
-----------------------

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:



*******TEST BACK BUTTON IS ROBUST THROUGH JUNK INPUTS*********
elcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
4

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the forums!
17 g: h
16 g: h
15 g: h
14 g: h
13 b: c
12 BOBO: HIIIIII
11 cats and bitches: ah
8 bcdo: 
7 name: message
6 these forums are nicer: thank you ta's
5 test: 
4 test: 
 10 namy1: massage
 9 namy: parenty
3 name: message
2 CS18: Hello!
1 CS18: Hello!
Post a reply:
Parent post (0 for new topic):
______(4)
Your name:
______(5)
Your message:
______(6)
[Submit](7)
<Go Home>(8)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 8) Page Elements:
2
Enter URL:fdrsgvdfvgdfv
Invalid URL
Rendering Page...
-------------

Welcome to the forums!
17 g: h
16 g: h
15 g: h
14 g: h
13 b: c
12 BOBO: HIIIIII
11 cats and bitches: ah
8 bcdo: 
7 name: message
6 these forums are nicer: thank you ta's
5 test: 
4 test: 
 10 namy1: massage
 9 namy: parenty
3 name: message
2 CS18: Hello!
1 CS18: Hello!
Post a reply:
Parent post (0 for new topic):
______(4)
Your name:
______(5)
Your message:
______(6)
[Submit](7)
<Go Home>(8)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 8) Page Elements:
2
Enter URL:fdgsfgv
Invalid URL
Rendering Page...
-------------

Welcome to the forums!
17 g: h
16 g: h
15 g: h
14 g: h
13 b: c
12 BOBO: HIIIIII
11 cats and bitches: ah
8 bcdo: 
7 name: message
6 these forums are nicer: thank you ta's
5 test: 
4 test: 
 10 namy1: massage
 9 namy: parenty
3 name: message
2 CS18: Hello!
1 CS18: Hello!
Post a reply:
Parent post (0 for new topic):
______(4)
Your name:
______(5)
Your message:
______(6)
[Submit](7)
<Go Home>(8)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 8) Page Elements:
1
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:


*******TEST BACK BUTTON PRESERVES GAME STATE*********



Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:
2
Enter URL:http://stilgar/Index

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
6

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to Nim!
There are currently 21 matches.  How many would you like to remove?
<One>(4)
<Two>(5)
<Three>(6)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 6) Page Elements:
2
Enter URL:http://thufir/Index

Connecting to thufir: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the Server on thufir!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
1
Rendering Page...
-------------

Welcome to Nim!
There are currently 21 matches.  How many would you like to remove?
<One>(4)
<Two>(5)
<Three>(6)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 6) Page Elements:
1
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
1
No pages to go back to
Rendering page...
-----------------------

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:


********TEST ALL THREE ABOVE IN SEQUENCE**************

-----------------------

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:
2
Enter URL:gfs
Invalid URL
Rendering page...
-----------------------

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:
2
Enter URL:http://stilgar/Index

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
2
Enter URL:hgfsdg
Invalid URL
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
2
Enter URL:http://thufir/Index

Connecting to thufir: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the Server on thufir!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
1
Rendering Page...
-------------

Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
1
No pages to go back to
Rendering page...
-----------------------

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:
1
No pages to go back to
Rendering page...
-----------------------

Welcome to Sparkzilla!
Action (1) Back, (2) New URL, (3) Quit:


***********ORDERING PIZZA (FORMS) WORK*********


Welcome to the Server on stilgar!
<The Forums!>(4)
<The Pizza Parlor!>(5)
<Nim>(6)
<Fight in the Coliseum!>(7)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 7) Page Elements:
5

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Welcome to the pizza parlor!
 What size pizza do you want? (Small, Medium, Large)
______(4)
[Submit](5)
<Go Home >(6)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 6) Page Elements:
4
Enter value for field size
Medium
Rendering Page...
-------------

Welcome to the pizza parlor!
 What size pizza do you want? (Small, Medium, Large)
___Medium___(4)
[Submit](5)
<Go Home >(6)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 6) Page Elements:
5

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

This is your order.
 Size: Medium
 Change size (Small, Medium, Large)
______(4)
 Add topping
______(5)
 Remove topping
______(6)
[Submit](7)
<Order the pizza. >(8)
<Go Home >(9)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 9) Page Elements:
7

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

This is your order.
 Size: Medium
 Change size (Small, Medium, Large)
______(4)
 Add topping
______(5)
 Remove topping
______(6)
[Submit](7)
<Order the pizza. >(8)
<Go Home >(9)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 9) Page Elements:
8

Connecting to stilgar: 8080
Connected
Server: SparkServer/1.0
Parsing page...
Rendering Page...
-------------

Sorry we could not process your order, we are out of the following:
Dough
<Place a new order. >(4)
<Go Home >(5)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 5) Page Elements:



**************EDITTING FORMS WORKS*****************
This is your order.
 Size: Medium
 Change size (Small, Medium, Large)
______(4)
 Add topping
______(5)
 Remove topping
______(6)
[Submit](7)
<Order the pizza. >(8)
<Go Home >(9)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 9) Page Elements:
5
Enter value for field addTopping
dgfgd
Rendering Page...
-------------

This is your order.
 Size: Medium
 Change size (Small, Medium, Large)
______(4)
 Add topping
___dgfgd___(5)
 Remove topping
______(6)
[Submit](7)
<Order the pizza. >(8)
<Go Home >(9)
-------------
Action (1) Back, (2) New URL, (3) Quit: (4 - 9) Page Elements:




**************

