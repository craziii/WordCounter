# Word Counter

## Installation

#### Requirements:
Java 11 (https://www.oracle.com/java/technologies/downloads/)<br>
Linux or Windows Operating System

#### Installation:
Install Java 11<br>
Pull project via git (https://github.com/craziii/WordCounter)<br>

#### Running Service:
Either:<br>
A)
Open the project in an IDE and use its internal service system<br>
or:<br>
B)
Run the .jar file as a service via windows, or through a linux based service solution.

#### Communicating with the service:
Unless stated otherwise the default port for the application is 8080.<br>
A test communication can be performed by sending any request to localhost:8080.<br>
The resulting message returned should be: "Word Counter up and running."<br>
<br>
Using a system such as postman, create a REST POST to localhost:8080/countWords with the parameter of filePath such as the example below.<br>
e.g: localhost:8080/countWords?filePath=https://janelwashere.com/files/bible_daily.txt<br>

The body of the reply will contain the required information, for the above example the response should be as follows:<br><br>
Word count = 793826<br>
Average word length = 4.243<br>
Number of words of length 1 is 18502<br>
Number of words of length 2 is 125345<br>
Number of words of length 3 is 211197<br>
Number of words of length 4 is 162450<br>
Number of words of length 5 is 104256<br>
Number of words of length 6 is 55302<br>
Number of words of length 7 is 48783<br>
Number of words of length 8 is 28309<br>
Number of words of length 9 is 19744<br>
Number of words of length 10 is 10375<br>
Number of words of length 11 is 4981<br>
Number of words of length 12 is 2481<br>
Number of words of length 13 is 1186<br>
Number of words of length 14 is 694<br>
Number of words of length 15 is 157<br>
Number of words of length 16 is 44<br>
Number of words of length 17 is 16<br>
Number of words of length 18 is 2<br>
Number of words of length 19 is 2<br>
The most frequently occurring word length is 211197, for word lengths of 3 <br><br>

Creating a link to a local file will require the filepath plus the "file:" prefix as follows:<br>
e.g: file:N/Files/testDoc.txt

#### Assumptions made:
A word is a set of numbers or letters connected with no whitespace inbetween.<br>
2 Words are seperated if there is a whitespace character or a newline between them.<br>
It is assumed that some characters such as "." are not counted when measuring the length of a word.<br>
In extention to the above point a list of characters have been found to be cleaned before counting word lengths.<br>
this is found listed as the following regex: /[!"#$%'\(\)*+,.:;<=>?@\[\]^_`{|}~]+/<br>
