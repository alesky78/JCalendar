# Welcome to JCalendar
JCalendar is a Java Swing Calendar component that allows you to represent data like in an agenda of Microsoft Outlook.

this software is implemented using java Swing technology and is tested wiht the jvm version 1.8
  
The actual actions imlpemented support all the crud operations on the event:
- add
- modify
- delete 

The UI actually support only the Month view, but:
 - the infrastructure is ready to support week or day view

Many thanks to theodorcostache that implemented the first version and put a lot of good ideas on its implementation (that can be found here https://github.com/theodorcostache/java-swing-calendar).
But that was a demo with several limitation like:
 - not supported in the UI event with multiple days 
 - not present a injectable standard model class like in the standard swing component
 - the listener was managed at high level classes and the model was not always involved
 
 this implementation actually is used in one of my commercial product and has hundreds customer, just to give you confidence of the stability of this implementation
 but my UC was simple and there are still limitations in the component that can be overcome.

## How to use
this is a short introduction, doesn't intend to give a full details on the way how to use it, 
but in the test package it.spaghettisource.certgen.ui.swing.component.calendar
you will find the class JCalendarDemo that run a prepared full example that use all the functionality of the component

## Note for the model
The framework is shipped already with an in-memory model already implemented, but for a real UC a specific model to provide persistence must be implemented
the class it.spaghettisource.certgen.ui.swing.component.calendar.model.AgendaModelMemory.java is the impementation of the in-memory and can be used as guide in the implmenetation of a model that support persistence (DB / file system)
 
 ## Screenshot
 Month view
<img src="https://github.com/alesky78/JCalendar/blob/main/screenshot/month-view.png">

 
