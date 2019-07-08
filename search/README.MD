Timothy Wang and Ivan Zhao

README

--------------------------------------------------------------------------

1. With the scoring mechanism used in the PageRank algorithm in mind, what could an
organization do to ensure their own pages get promoted to the top of the search results?

They can reach out to well-known organizations so that somewhere on their website, they'd link to your website. Because you're adding another link to your page, and that page is very authoritative, your page's pagerank score will increase and get closer to the top of the search results.

2. Say an inaccurate news article has gone viral and individuals have been linking to it
heavily in their online feeds (social media, blogs, pages, etc.). How could this affect the
results of someone who is attempting to research the story through a search engine that
uses PageRank?

Because many more links are being adding that link to the inaccurate news article, the pagerank score of the inaccurate news article will shoot up. As such, when someone is trying to research the story through the search engine, that inaccurate news article is more likely to appear near the top of the search results and may give the researcher false information (if they didn't know it was inaccurate).

3. Both scenarios above are examples of likely undesirable outcomes of the PageRank
algorithm. Contrast the two scenarios now with respect to human behavior. To what
extent is intent or malice required to get the adverse affects in each of the scenarios?
Unlike the first two questions, here we are asking you to think about the social, not
technical, aspects of PageRank.

Human-nature tends to succumb to virality. When people see an interesting article, they tend to post on social media to share with others. So, while no ill-intent is involved among the people that do share articles (unknowingly inaccurate ones), they end up linking to that article, and as such promotes the inaccurate article in the searches.

People also tend to be


--------------------------------------------------------------------------

Instructions for use, describing how a user would interact with your program:
Navigate to the scalaproject directory and compile the code with the following:
scalac -d bin s*/search/*/*.scala
Then navigate to the bin folder (using cd bin) and run:
scala -J-Xmx1g search.sol.Index
with the desired arguments. The first argument is the corpus (for example, the path to "SmallWiki.xml"). The second argument is the txt file you want to print your id and title pairs to. The third argument is the txt you want to print your id, word, and score data. The fourth argument is the txt file you want to print your ids and pagerank scores.

Wait until it compiles. Now, run the following line:
scala search.sol.Query
with the desired arguments. The first argument is the txt you want to read the titles and id pairs from. The second argument is the txt file you want to read the id, word, and score data from. The third argument is the txt file you want to get the id and pagerank scores from. There is an optional argument you can add on the beginning. Typing in "--pagerank" will tell the Query to use the pagerank scores in deciding which are the top 10 titles. Typing in "--smart" will simply do nothing extra.

NOTE: The full file path must be inputted.

Now, after this compiles, you can type in as many words as you want. When you're done with writing the words you're looking up, press "enter". The top 10 document titles will appear, in order from most relevant to least relevant (of the top 10 results). If there are less than 10 documents containing the words entered by the user, then there will be less than 10 documents outputted. If the user ever enters "" or ":quit", then the program will terminate.

---------------------------------------------------------------------------

A brief overview of your design, including how the pieces of your program fit:
We have two classes, Query and Index. We organized our Index class into several parts. At the top of the class, we have vals declared that are used throughout the rest of the class. We have three large methods that take care of certain aspects that Index needs to handle. Our pageRank method calculates the pagerank scores. It then writes the pagerank scores with the ids associated with them in a txt file for Query to read. The scoring method calculates idf and tf, and combines the scores into a HashMap[Int, HashMap[Word, Score]] and then prints it out. Finally, we print out the titles and id pairs.

Our Query class takes in all 3 of the files that Index writes to, and creates HashMaps, one for ids to titles, one for words to ids to scores, and one for ids to pagerank scores. We then use a while loop to read user input. The user can type in any number of words as they like, and after pressing enter, we split the user input by space, find the scores of all relevant documents and finds the top 10 titles that correspond with the 10 highest scores. The user can keep typing in until they want to quit. To quit, user must type in ":quit". The program will also quit on an empty input (asked on Piazza).

---------------------------------------------------------------------------

A description of features you failed to implement, as well as any extra features you implemented:
We did not fail to implement anything. We did not include any extra features.

---------------------------------------------------------------------------

A description of any known bugs in your program:
There are no known bugs in our program.

---------------------------------------------------------------------------

A description of how you tested your program:
The way we've designed our program, System testing seems to be the most appropriate form of testing. Below this paragraph is the system testing. Additionally, we tested pagerank using the PageRankWiki.xml file. Documents 1-99 all had a pagerank score of 0.0015 and document 100 had a score of 0.8515. They all add up to 1 (an indicator that it works). And, since each of documents 1-99 has a link to document 100, and 100's score was MUCH higher than the other documents, the results indicate that our pagerank algorithm is working.

--System Testing--
------------------------------
Small Wiki Testing
------------------------------
ivan

1 Feudal fragmentation

(shows that if the word only appears once, then
only one document will show up)
-----------------------
war

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

(the following 8 show that the same word will have the
same queries even if they have different capitalization)
-------------------------
War

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)
-------------------------
wAr

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

-------------------------
waR

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

---------------------------
WaR

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

---------------------------
WAr

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

---------------------------------
wAR

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

------------------------------
WAR

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

------------------------------
tim

No documents found

(example of a word not found)
------------------------------
sorin

No documents found

(another example of a word not found)
------------------------------
thought

1 Aggressionism
2 Intellectual history
3 Anatopism
4 Philosophy of war
5 Carthaginian Iberia
6 Psychohistory
7 Tertullian
8 Pantelleria
9 Jean Emile Humbert
10 Anachronism

------------------------------
thought?

No documents found

(example of a word found that adds a special character and
is not found)
------------------------------
thoughtful

1 Aggressionism
2 Intellectual history
3 Anatopism
4 Philosophy of war
5 Carthaginian Iberia
6 Psychohistory
7 Tertullian
8 Pantelleria
9 Jean Emile Humbert
10 Anachronism

(examples of the same results if the word has the same stem)
------------------------------
war tim

1 War
2 Progressive war
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

(multiple words resulting in one query type (cause 1 of them
has 0 documents))

------------------------------
flying

1 Ash heap of history
2 Service d'exploitation de la formation a?ronautique
3 Anachronism
4 Military history

------------------------------
sky

1 Anachronism
2 Military history
3 Outline of China

------------------------------
flying sky

1 Ash heap of history
2 Service d'exploitation de la formation a?ronautique
3 Anachronism
4 Military history
5 Outline of China


------------------------------
Small wiki Testing w/o pagerank (to show that it works and that
they have new results because of it)
------------------------------
war

1 Progressive war
2 War
3 List of wartime cross-dressers
4 Philosophy of war
5 Effects of war
6 War referendum
7 War profiteering
8 War porn
9 Military history
10 Theater (warfare)

------------------------------
thought

1 Aggressionism
2 Intellectual history
3 Anatopism
4 Philosophy of war
5 Carthaginian Iberia
6 Psychohistory
7 Pantelleria
8 Jean Emile Humbert
9 Tertullian
10 Comparative historical research

------------------------------
ivan

1 Feudal fragmentation

------------------------------
MED WIKI TESTING
------------------------------

cats

1 Kattegat
2 Kiritimati
3 Morphology (linguistics)
4 Northern Mariana Islands
5 W. Heath Robinson
6 Lynx
7 Isle of Man
8 Freyja
9 Autosomal dominant polycystic kidney
10 Oakland Athletics

------------------------------
ruler

1 Mohism
2 Monarch
3 Imperialism in Asia
4 Jadwiga of Poland
5 Henry the Fowler
6 Michael
7 Manasseh
8 Empress Suiko
9 Islamabad Capital Territory
10 Empress Jit?

------------------------------
cats ruler

1 Mohism
2 Kattegat
3 Monarch
4 Kiritimati
5 Imperialism in Asia
6 Jadwiga of Poland
7 Henry the Fowler
8 Michael
9 Politics of Lithuania
10 Manasseh

------------------------------
cat

1 Kattegat
2 Kiritimati
3 Morphology (linguistics)
4 Northern Mariana Islands
5 W. Heath Robinson
6 Lynx
7 Isle of Man
8 Freyja
9 Autosomal dominant polycystic kidney
10 Oakland Athletics

------------------------------
for your life and your pain

1 Laparoscopic surgery
2 Autosomal dominant polycystic kidney
3 Hydrocodone
4 Kocher-Debre-Semelaigne syndrome
5 Pope Gregory XIV
6 Henry Bordeaux
7 Georges Braque
8 Hugh Binning
9 Paolo Uccello
10 Karl Amadeus Hartmann



------------------------------
BIG WIKI TESTING
------------------------------

war

1 Peloponnesian War
2 Punic Wars
3 Lebanon War
4 On War
5 List of conflicts in the Near East
6 Imperialism in Asia
7 National War College
8 History of Europe
9 Irish Civil War
10 Katyusha rocket launcher

------------------------------
wars

1 Peloponnesian War
2 Punic Wars
3 Lebanon War
4 On War
5 List of conflicts in the Near East
6 Imperialism in Asia
7 National War College
8 History of Europe
9 Irish Civil War
10 Katyusha rocket launcher

(shows that stemming works for both war
and wars)
------------------------------
warring

1 Peloponnesian War
2 Punic Wars
3 Lebanon War
4 On War
5 List of conflicts in the Near East
6 Imperialism in Asia
7 National War College
8 History of Europe
9 Irish Civil War
10 Katyusha rocket launcher

(same principle as above)
------------------------------
cuisine

1 Hunan cuisine
2 Fusion cuisine
3 Cuisine of the Midwestern United States
4 German cuisine
5 Hakka cuisine
6 French cuisine
7 Greek cuisine
8 Mexican cuisine
9 Moroccan cuisine
10 Kutia

------------------------------
cuisin

1 Hunan cuisine
2 Fusion cuisine
3 Cuisine of the Midwestern United States
4 German cuisine
5 Hakka cuisine
6 French cuisine
7 Greek cuisine
8 Mexican cuisine
9 Moroccan cuisine
10 Kutia

(same stemming principle with a different word)
------------------------------
computer science

1 Fred Brooks
2 Garbage in, garbage out
3 HCI
4 Electronic Delay Storage Automatic Calculator
5 History of computing hardware
6 LEO (computer)
7 Parasitic computing
8 PCM (disambiguation)
9 Minicomputer
10 Non-deterministic Turing machine

(shows that the output is dependent on the scores
of the two possible words)
------------------------------
computer

1 History of computing hardware
2 Fred Brooks
3 Garbage in, garbage out
4 Parasitic computing
5 PCM (disambiguation)
6 HCI
7 Electronic Delay Storage Automatic Calculator
8 Minicomputer
9 LEO (computer)
10 Non-deterministic Turing machine

------------------------------
science

1 Hugo Gernsback
2 Hard science fiction
3 Pathological science
4 History of science and technology
5 Protoscience
6 Junk science
7 Outline of physical science
8 Katherine MacLean
9 History of science
10 Futurians

------------------------------
computer engineering

1 Fred Brooks
2 History of computing hardware
3 Mechanical engineering
4 LEO (computer)
5 Electronic Delay Storage Automatic Calculator
6 Minicomputer
7 Garbage in, garbage out
8 Parasitic computing
9 PCM (disambiguation)
10 HCI

(same principle as above but with different words)
------------------------------
engineering

1 Internal combustion engine
2 Mechanical engineering
3 Jet engine
4 Felix Wankel
5 Heat engine
6 Jetsprint
7 IEE
8 Geotechnical engineering
9 Horsepower
10 ICD (disambiguation


In index, if we incorrectly input the # of files, it should print
"4 files are needed as input (corpus, and three txt files)"

In query, if we incorrectly input the # of files, it should print 
"Must have 3 or 4 arguments"

If any of the files are incorrect, it should catch the Exception accordingly
and print an informative statement

---------------------------------------------------------------------------

A list of the people with whom you collaborated:
Sorin Cho, Ethan Chung
