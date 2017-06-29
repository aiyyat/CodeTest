Skyhub Digital Code Test
==========================
<br>Problem Statement: https://github.com/technicalyorker/misc/blob/master/Skyhub/docs
<br><b>Sales Tax Problem:</b>
<br>The problem is most easily solved by using the decorator (or wrapper) pattern. This approach to the usecase ensures that there is no redundant code while keep the classes Cohesive & Clean at the same time. This approach also ensures classes are close to modification but open for extension (Open Close Principle). Thus adding a new good is as easy as simply defining a new class or category of class that extends an exising appropriate Good types.
<br>class diagram:
![Alt text](https://github.com/technicalyorker/misc/blob/master/Skyhub/parent/salestax/docs/SalesTax.gif "Class Diagram")
<br>A "Good" is the super class of all purchasable items and defines the behaviours such as calcating the sales tax and total amount. There are basically groups of Goods from a Tax perspective. Goods with Basic Sales Tax and Goods free of basic sales tax. Yet there is an additional tax levied (decorated) as an Imported good.
<br><br><b>source code:</b>https://github.com/technicalyorker/misc/blob/master/Skyhub/parent/salestax
![Alt text](https://github.com/technicalyorker/misc/tree/master/Skyhub/docs/sales.png "Sales Tax")
<br><br>
<b>Brackets:</b>
The solution is iterating through the elements adding into a stack and checking the against the pushed contents when a terminating bracket if found.
<br><b>source code:</b>
https://github.com/technicalyorker/misc/blob/master/Skyhub/parent/brackets
![Alt text](https://github.com/technicalyorker/misc/tree/master/Skyhub/docs/bracket.png "Brackets")
<br><br>
<b>Recommendations:</b>
</br>The solution describes two different approaches to solving the problem.
<br>KnapSack Recommendation Strategy:
<br>This is more real time & "scalable" approach with a time complexity of only O(NW), N being the number of movies and W being 0..Flight time in minutes. Though it gives accurate result movie with the fit of the total time in minutes, the approach cannot return an entire list of movie combination recommendation from which a user can choose from. Knapsack divides the problem into atomic parts with recommendations based on combination of increasing flight time v/s movie play back times. The best results are accumulated in the form of elements (cells) of a matrix to provide the Optimal Solution. Unfortunately the table only provides the optimal solution and arriving at the ingredients of the solution demands some level of backtracing.
<br>Cached Recommendation Strategy:
<br>This approach gives all the possible movie list during the flight time ordered by the best fit recommendation first. It Caches the Movie Combinations from the movie database in order of Total Runtime in order of longest playback time. This solution follows a One Time Load approach followed by any number of Querying for Recommendation. Querying in this approach is extremely fast. Since the movies in the flight dont change frequently, particularly during flight and is same for all customer it would be the closest approach to real world implementation.
<br>
<b>source code:</b>
https://github.com/technicalyorker/misc/blob/master/Skyhub/parent/recommendations
![Alt text](https://github.com/technicalyorker/misc/tree/master/Skyhub/docs/recommendation.png "Recommendations")
