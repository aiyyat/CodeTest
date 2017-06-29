Weather Simulator
=============================================================================

Problem Statement:
-----------------------------------------------------------------------------

https://github.com/technicalyorker/commbank/blob/master/CommonWealthBank/misc/WeatherData.pdf


Solution:
-----------------------------------------------------------------------------

Introduction: 
-----------------------------------------------------------------------------

Approach: 
<br>I. First thing to do in the use case is to identify the nouns in ordered to arrive at the entities. Terms such as Weather, Position etc. would make up the dictionary of entities for our use case. More on this as we go further. 
<br>II. Next, after we identify the components that make up the players of the usecase,  we "Define the Contract" such that the simulator is easily extensible into a full fledged Real Time Weather Monitoring System. 
<br>III. Finally, we focus on the systems behaviour to emit data. In terms of Message Exchange Patterns (MEPs) it resembles the 'Out Only' or the 'Robust Out Only' Pattern. 

Design motivation: 
-----------------------------------------------------------------------------

For a moment let's imagine that the system is Real Time in nature and not just a simulator; We would have had a system that would have been triggered by a scheduler or some external means, it would then be 'expected' to "Read" or "Poll" Data Samples from sources such as a Tranducer, a Database or even a Web-Service End Point; to be able to have to emit the weather data samples into another system by: being a Subject to an Observer <br> (refer Observer Pattern https://technicalyorker.wordpress.com/2015/07/14/behavioral-patterns/) <br>or publishing message into a Messaging Destination such as a Queue or Topic or a Web Service End Point or even a Data Store. 
<br>We thus must design the simulator providing these "Extension Points" while still writing code targetting a simulator. <br>Thus During Design thought process, we will bare in mind Design Principles. e.g.:<br>
<b>S</b>ingle Responsibility Principle.  
<b>O</b>pen Close Principle.  
<b>L</b>iscovs Substitution Principle.  
<b>I</b>nterface Segregation Principle.  
<b>D</b>ependency Inversion Principle.  
The process of defining the contract of the System churns out Interfaces such 
<br>* WeatherDataEmitter
<br>* WeatherEngine
<br>* WeatherObserver
<br>* WeatherService
<br>* Trigger

Use Case:
-----------------------------------------------------------------------------

Once we have identified the actors in the problem statment, we have to define how they interact. Due to time constraints, Usecase diagram and sequence diagrams have been avoided. 
<br>The Use case given in the problem comes with a great potential for imagination to run wild. It defines very little boundaries in terms of how the Weather Simulator behaves within its ecosystem and is going to be used. Thus I did take my own example to demonstrate the extensibility, modularity, code reuse characteristics of the framework. We have pulled in data from "The Australian Government Bureau of Meteorology" website, which comes in the form of feed files refer:
http://www.bom.gov.au/products/IDS60801/IDS60801.94675.shtml#other_formats
We thus use Real Time Data Samples of Adelaide provided in the website for our use case. This data which is of Sampling interval of 30 min, in our implementation would be emitted on a much smaller scale interval basis [of course, we have no reason to have to wait for day to get hold of the data we already have ;) ] in our Simulator.
The data would then be interepreted by multiple obsevers doing the following jobs:
<br>DateTimeBasedTemperatureThresholdSimulatorObserver: Would warn us if the temperature is above or below a defined threshold value.
<br>DateTimeBasedAverageSampleObserver: Would tell us the average Temperature and Relative Humidity over the whole sample.
<br>DateTimeBasedMaxMinTemperatureSampleObserver: Would produce the Max and Min Temperature values over the whole sample. It even indicates to us when the weather reaches a new High or Low.
<br>DateTimeBasedRealTimeSampleObserver: This Simulates the receiver or subscriber described in the problem statement.
<br>Maven Build Output of which look like this:
![picture alt](https://raw.githubusercontent.com/technicalyorker/commbank/master/CommonWealthBank/misc/build.png "Build Results")

Explaination:
-----------------------------------------------------------------------------

The WeatherEngine acts as a mediator co-ordinating all the components so that none need to talk to each other all by itself. An Engine is always associated with a Trigger which in Real Time Implementation is probably a Scheduler, a Timer, a downstream System, or a Job of some kind which indicates to the Engine to Sample a Weather Data. Parameterization of the output of a trigger lets one define literally any forms of input for the weather service to query on. It could be a combination of latitude, longitude, altitude and localtime; In our case its just local time since our weather station is located at Adelaide lat: -34.92 long: 138.62 altitude: 48. 
<br>In our implementation a Trigger that uses a separate thread to notify Engine to demonstrate a fairly complex usuage. On reception of indication to "Request" or "Poll" Data, Engine liaise a DataService for data Samples. An appropriate Data Service implementation could literally pull from any source. 
<br> Once the Engine gets its data, it emits it to an Emitter. In our implementation Emitter in this consists of a number of Observers. In real time implementation this would haven taken the form of a Message channelled into a Messaging Destination controlled by a Message Oriented Middleware/Message Broker (which is a cleaner and more reliable way of loose coupling two systems) or it could have been a record a database from which the interfacing system would Pull.  
<br>Entities involved here are a Weather class that produces the output in the format expected by the problem statement. However for complex transformations, a separate component exclusive for transformation could be used. Weather has an inbuilt builder to construct, it being a very complex object to build. I would have replaced all the boiler plate code with a library such lombok (http://jnb.ociweb.com/jnb/jnbJan2010.html) which would have let the code look cleaner if it not were for a Coding Test.
<br>A Position object like weather is immutable in nature since as such it is mostly going to used to query the data by services along with an instance of a localtime. Condition is an enum used to represent various weather conditions such as Rain, Sunny, Snow etc. 

Time Series Extrapolation:
-----------------------------------------------------------------------------

The System uses TimeSeries Extrapolation to predict the temperature and relative humidity. Rain is a condition that occurs when the relative humidity reaches 100%. 
<br>The following output represents actual value against the calculated value using the time series extrapolation. This is to get a sence of the calculated values against the actual values.  
![picture alt](https://raw.githubusercontent.com/technicalyorker/commbank/master/CommonWealthBank/misc/timeseries_calculated.png "Class Diagram")
<br>The following output represents Predicted Values of temperature and relative humidity. 
![picture alt](https://raw.githubusercontent.com/technicalyorker/commbank/master/CommonWealthBank/misc/timeseries_predicted.png "Class Diagram")

Class diagram (Main Classes Only):
----------------------------------

![picture alt](https://raw.githubusercontent.com/technicalyorker/commbank/master/CommonWealthBank/misc/WeatherSimulator.gif "Class Diagram")

Main class:
-----------------------------------------------------------------------------

com.technicalyorker.misc.commonwealthbank.weathersimulator.WeatherSimulatorApplication

Building the project:
-----------------------------------------------------------------------------

mvn -e clean install

Running the application:
-----------------------------------------------------------------------------

mvn -e exec:java

javadoc:
-----------------------------------------------------------------------------

mvn -e clean site
<br>classes: target/site/apidocs/index.html<br>
![picture alt](https://raw.githubusercontent.com/technicalyorker/commbank/master/CommonWealthBank/misc/javadoc.png "javadoc")

Tree View:
-----------------------------------------------------------------------------

![picture alt](https://raw.githubusercontent.com/technicalyorker/commbank/master/CommonWealthBank/misc/tree.png "Tree")

Sources of Data:
-----------------------------------------------------------------------------

Source of Weather Samples:
-----------------------------------------------------------------------------

http://www.bom.gov.au/products/IDS60801/IDS60801.94675.shtml#other_formats

Source of Conditions:
-----------------------------------------------------------------------------

http://weather.wikia.com/wiki/Types_of_Weather

Contact:
-----------------------------------------------------------------------------

For any further Questions please reach out to me at:
<br>achuth.i@gmail.com
