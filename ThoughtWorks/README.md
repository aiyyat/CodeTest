ThoughtWorks Code Test
=======================
In ordered to clear any coding testing, remember to adhere to the Design Principles as much as possible. Refer the posting: https://technicalyorker.wordpress.com/2015/04/27/common-software-design-principles/. This not only makes your code reusable and look well written, it also serves as pillars to your framework that you can count on to justify your design. If you haven’t yet, Browse through the book, “Clean Coding” by Robert C Martin. This saves a lot of time in writing effective code and make it more intutive and less theoritical.

Problem Statement:
------------------
https://github.com/technicalyorker/misc/blob/master/ThoughtWorks/resources/questions.txt

Solution to Problem 1: 
----------------------
My Solution to the problem statement revolves round the State Pattern. The way I see the solution is that the Rover displays different characteristics in different (Direction Facing) States.
For e.g. When the Rover is North Facing,
* Moving up would mean incrementing the Y co-ordinate,
* Taking a turn left would mean Rover finds itself Facing West wards.
* A Right turn from the North would translate into Rover Facing East.

Class Diagram:
------------------
![alt tag](https://github.com/technicalyorker/misc/blob/master/ThoughtWorks/resources/Problem1.png)

URL to Source Code:
https://github.com/technicalyorker/misc/tree/master/ThoughtWorks/src/main/java/com/thoughtworks/problem1/rover

URL to TestCases:
https://github.com/technicalyorker/misc/tree/master/ThoughtWorks/src/test/java/com/thoughtworks/problem1

Solution to Problem 2:
----------------------
My Solution mirrors the ‘Decorator Design Pattern’ or sometimes called a ‘Wrapper Pattern’. ‘Goods’ as described by the problem statement are of different types.
* Goods without any tax
* Good on which Basic Sales Tax are levied
* Imported goods that have imposed Duty
* Imported Good with Sales Tax
are some of the possibilities described in the Use Case.

Implementations of Good include: Books, Chocolates, CD etc.
A Good (generic term to the items sold) is more of a type and should not be instantiable, while their implementations can be.
Basket is the class that is the container to all the goods. Once the goods are chosen a call to finalizeShopping() concludes the shopping and calculates the Tax and the Total Amount payable.
Any imported good is encapsulated inside an ImportedGood Class. This is a typical Object Wrapping or Decoration Characteristic nature of Decorator Design Pattern.

Class Diagram:
![alt tag](https://github.com/technicalyorker/misc/blob/master/ThoughtWorks/resources/Problem2.png)

URL to Source Code:
https://github.com/technicalyorker/misc/tree/master/ThoughtWorks/src/main/java/com/thoughtworks/problem2

URL to test cases:
https://github.com/technicalyorker/misc/tree/master/ThoughtWorks/src/test/java/com/thoughtworks

I do not know if the composers of the Problem wanted to add quantity as a parameters. Even so it isn’t much of an effort to deal with it like we dealt the Price in the first place.
Final piece of Advice: BigDecimals should be used for all calculation and Representation, including parameters to BigDecimals. A Double on amount would prove to be a Programmers Nightmare!!
