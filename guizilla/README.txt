Project Creators: izhao3 & arothber

For the GUI, the user simply runs the application, which loads up the GUIBrowser
From there, the user can input their url link (probably http://stilgar/Index). Then,
the user has the capacity to either hit the back button, switch links, click on links,
input text, submit it, and navigate around the browser.

For the server, the user needs to start the server up so that its up and running. Once 
thats completed, they then can connect to it with the port 8080. From there, they can access
it by using localhost/Index or any of the corresponding pages. It basically functions similar to
the design in the sparkzilla server where each button corresponds to a input that the user can do 
and the page is parsed by the client side.

--------------------------------------------------------------------------------------
For the GUI:

-There is a GUIBrowser which extends Browser. This GUIBrowser handles a good chunk of the
methods that have been instantiated. It handles the FXML files, the formats, and has
the methods for the various buttons on the page.

-HTMLElements: All the HTMLElements have a method called render which renders them to the page
by adding them into the VBox. Some of the elements have a function called click that handles their
action element inputs.

-FXML_main.fxml handles all that good setting up stuff.

For the Server:

-Server.scala: Has all the necessary parts in setting up the server. It creates a server side socet, 
passes all the corresponding info, and allows the user to access it so that they can get the corresponding pages

-Various pages: These pages basically have all the html information and also extend Page so that all the pages
and the session ID's are stored so that the user has the capacity to access that corresponding page.

-------------------------------------------------------------------------------------------------
We didn't implement any extra features and we didn't fail (hopefully) to implement any of the 
features

------------------------------------------------------------------------------------------------
No known bugs

-----------------------------------------------------------------------------------------------
How the program was tested:


Collaborated with:
Sorin Cho
Tim Wang