ShopnStop Readme

izhao3 -- echung17

_______________________________________________________________________________________
Instructions for use:

A user would interact with the program by running the portal.java file 
in the repl which would create a print line that asks them to either login as a buyer, 
realtor, or quit.

If they're a buyer, they can

1) View the listing which they can sort by price, size, or year
2) not be able to add a listing
3) not be able to remove a listing
4) Select a specific listing where they can purchase it if its the corresponding 
purchase or rent a renting or a hotel if its a rent/hotel
5) logout
6) quits the program entirely

If they're a realtor, they can

1) View the listing which they can sort by price, size, year, rating
2) Add a listing with each element in a listing
3) Removes the listing from the list of listings
4) Select a specific listing where they can update a corresponding field in the element.
5) logout
6) quits the program entirely 

________________________________________________________________________________________
Source code: Used to print all the various messages in the terminal

User Interface that is implemented by Buyer and Realtor.
This interface as addListing, removeListing, SelectListing, sort Listings, and sorting Options
which are all doubleDispatches to check if the corresponding USer is a Realtor or Buyer. 
If it's a buyer, it should do all functions that a buyer should do such as not being able to add listings
and sort as a buyer. The realtor should do the same but as a realtor.

We then create a set for listings that is composed an interface IListings that has a couple
of methods that allow us to select for a buyer and print by buyer or a realtor. This interface 
is extended by an absract class called Listing that essentially instantiates a type listing 
and also has the method for select listing on realtor which allows a realtor to change the field.

There are three types of listings, Hotel, Purchase, and Rent where Hotel and Rent have date fields
and Purchase has a buyer field which stores the buyer when bought. Each listing also has a method
where it prints if its called by a Realtor or a Buyer. 

We have a last field where its called TheListings which is a List of Listings. As such, it also
has the comparators for all the various type.

Lastly, we have a Portal which essentially calls all the functions. It first checks for the User,
then takes in an input and then keeps taking inputs depending on if it correctly corresponds to one of
the cases or not.

_______________________________________________________________________________________
Hopefully no bugs and no extra features

________________________________________________________________________________________
Tests

Unit Testing: We tested all unit tests that could possibly change and did not require a 
user input to do so.

System testing: We have two main testing blocks

input -> output

The first case follows the realtor where it sets up 3 listings with three names and different 
fields to insure that we're chekcing all the types. We then test viewing listings and sort by all 
possible types. We then remove one of the listings to prove that its possible. We then select listings and 
change each possible field once starting with name and going all the way down. We then log out once, run a couple
of errors such as nonvalid index, pressing enter without an input, and sorting a couple times without an actual call.
We then exit.

input1 -> output1

This case follows the buyer where we start by initializing three fields as a realtor, then viewing listing and
sorting them by all possible listing. We then call the add and remove functions where it shows an error message
From there, we try to select a listing, purchase it, doule book a hotel, then book a rent listing. We then
throw a couple more errors and then quit the portal to show that it is possible.


_______________________________________________________________________________________
People collaborated with:

Timmy Wang
Isabel Lai
