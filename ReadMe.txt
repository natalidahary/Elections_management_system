Elections Management System

This program is designed to simulate an election process using the Model-View-Controller (MVC) architecture. The "Model" class acts as a mediator between the "View" class, which contains all graphical user interface (GUI) elements, and the "Controller" class, which manages the interaction between the view and model.

The core of the program is the "Elections" class, which stores information about the election such as the citizens, parties, and ballot boxes. It also includes methods to read and write binary files.

The "Party" class stores information about each political party, including a vector of all candidates. The "Citizen" class stores information about each individual citizen, including their ID and age, with corresponding exception classes for validation.

There are three derived classes, including the "Candidate" class which receives pre-election votes (primaries grading), the "Soldier" class which is connected with a weapon interface, and the "InfectedWithCorona" class which represents a citizen infected with COVID-19.

The "CardBox" class is a generic class that stores information about each ballot box, including a vector of local citizens and a vector of votes each party received. It also includes three possible scenarios: Regular, Military, and Corona-Military.

The "ElectionsView" interface manages all methods that will be applied in the user representation.

To run the program, the user will need to first run the read binary files method. The program then simulates an election process and allows the user to view the results through the GUI.