# Welcome to JCalendar
JCalendar is a Java Swing Calendar component that allows you to represent data like in an agenda like Microsoft Outlook.

this software is implemented using java Swing technology and is tested wiht the jvm version 1.8
  
The actual actions implemented support all the crud operations on the event:
- add
- modify
- delete 

The UI actually implement only the Month view, but:
 - the infrastructure is ready to support week or day view

Many thanks to theodorcostache that implemented the first version and put a lot of good ideas on its implementation (that can be found here https://github.com/theodorcostache/java-swing-calendar).
But its implementation had  several limitation like:
 - not supported in the UI events that cover multiple days! (event longer then 1 day, it is show as one-day event) 
 - not present a injectable standard model class like in the standard swing component
 - the listener was managed at high level classes and the model was not always involved
 - the state of the components was stored in singleton with potential memory leak during the dispose of the component because you have to clean the memory manually

in this implementation all this issues are solved and a more clear structure has be defined
 
## How to use
this is a short introduction, doesn't intend to give a full details on the way how to use it, 
but in the test package you will find the class JCalendarDemo that run a prepared full example that use all the functionality of the component

## Note for the model
The framework is shipped already with an in-memory model already implemented, but for a real UC a specific model that provide persistence probably should be implemented. The class CalendarModelMemory is the in-memory implementation and can be used as guide in the implementation of a model that support real persistence
 
## Screenshot
Month view
<img src="https://github.com/alesky78/JCalendar/blob/main/screenshot/month-view.png">

 
