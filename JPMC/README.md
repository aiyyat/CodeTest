JP Morgan Code Test.
====================
The Post is on the first round of JPMC interview which was a coding test consisting of 2 questions.
One of easy ways of clearing such tests is to adhere to Robert C Martin’s ideas in his book ‘Clean Code’. Books gives a lot of advice which will not only save time but also helps you justify certain industry wide wrong practices. For example there is a stereotype that all good code "MUST" contain comments. But just as the book prescribes, any code must just have enough comments that are unavoidable in nature e.g. definition of the Jargons used in the solution.
Additionally, the method names were to be quite self explanatory of its purpose and fine grained enough to serve a single purpose. Each Method only performed a single level of Abstraction.
<br>
As described in the book, the solution was an effort to adhere to the common Design Principles:
OCP – an RCM prescription for OOPLs
SRP
LoD
DRY
etc.
which can already be found in the Post:
https://technicalyorker.wordpress.com/2015/04/27/common-software-design-principles/
<br>
The solution in itself was a pursuit to attain seamlessly readable and effortlessly understandable code.
<br>
Additionally, the ‘simply’ working code base had to go through multiple revisions of improvement to its method and identifier names before it was redeemed even presentable.
With a fairly good test coverage of the solution, almost all public methods have been asserted while unit testing.
<br>
Source: https://github.com/technicalyorker/misc/blob/master/JPMC
<br>Question:
https://github.com/technicalyorker/misc/blob/master/JPMC/resources/Test.txt

PROBLEM 1
----------
<br>Main Classes: https://github.com/technicalyorker/misc/tree/master/JPMC/src/main/java/com/jpmc/problem1
<br>Test Classes: https://github.com/technicalyorker/misc/tree/master/JPMC/src/test/java/com/jpmc/problem1

Problem 2:
----------
Class diagram: 
![alt tag](https://github.com/technicalyorker/misc/blob/master/JPMC/resources/problem2.png)
<br>Main Classes: https://github.com/technicalyorker/misc/tree/master/JPMC/src/main/java/com/jpmc/problem2
<br>Better Representation of the question: https://github.com/technicalyorker/misc/blob/master/JPMC/resources/Problem2.xlsx
<br>Test Classes: https://github.com/technicalyorker/misc/tree/master/JPMC/src/test/java/com/jpmc/problem2
