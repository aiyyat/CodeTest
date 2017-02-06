Problem Statement: https://github.com/technicalyorker/misc/blob/master/Infosys/Calculator/resources/Java%20Tech%20test.pdf

Code primarily follows the Interpreter pattern. It uses two algorithms by one of the undisputed genius of Data Structure - E.W. Dijkstra.
It uses Shunting-Yard algorithm to convert User's input Infix Expression into Postfix Expression. This expression is processed and provided back to the user.

<br>QA Testing:<br>
After Building using<br> 
$ mvn -e clean install<br>
Under target Folder, execute:<br>
java -jar Calculator-1.0-SNAPSHOT.jar "3 * ( 4 + 5 )"<br>
ScreenShot:<br>
![alt tag](https://github.com/technicalyorker/misc/blob/master/Infosys/Calculator/resources/QA.png)
<br>ClassDiagram:<br>
![alt tag](https://github.com/technicalyorker/misc/blob/master/Infosys/Calculator/resources/Classdiagram.gif)
<br>Running In Eclipse:<br>
![alt tag](https://github.com/technicalyorker/misc/blob/master/Infosys/Calculator/resources/Arguments.png)
<br>Tree:<br>
![alt tag](https://github.com/technicalyorker/misc/blob/master/Infosys/Calculator/resources/tree.png)
<br>help:<br>
![alt tag](https://github.com/technicalyorker/misc/blob/master/Infosys/Calculator/resources/help.png)

