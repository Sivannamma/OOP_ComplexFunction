Project name: Math- Polynoms, Monoms, Functions and all that is between.

Project writes: Sivan namah azari, Elad vainbrand

Project description: this project support creating polynom, monoms, complex function, representing the functions and objects, on those objects we can perform serval function.

Monom is a class that implements from an interface called funtion, which includes the following function:
1. f(double x)- this function calculate the value of the monom using the variable given(x).
in adition monom has its own functions:
1.derivative()- this function returns the derivative of our exsisted monom.
2.isZero()- this function checks if the value of a given monom is zero.
3.add(monom m)- this function add a monom to our exsisted monom.

Polynom is class that implements from an interface called Polynom_able, which includes the following functions:
1. add(Monom m)- this function gets a monom and add it to our exsisted polynom.
2.add(Polynom_able m)-this function gets a Polynom_able variable and add it to our exsisted polynom, using function number 1.
3. f(double x)- this function calculate the value of the polynom using the variable given(x). this function using the function f(double x) that belogs to monom class.
4.substract(Polynom_able m)- this function gets a Polynom_able variable and substract it from our exsisted polynom, using a private function in Polynom class substract(Monom m).
5. multiply(Monom m)- this function gets a monom and multiply it with our exsisted polynom.
6.multiply(Polynom_able m)- this function gets a  Polynom_able variable and multiply it from our exsisted Polynom, using a function multiply(Monom m) number 5.
7.equals(Polynom_able m)- this function checks if two polynoms are equals, using equal function that is wrriten in monom class.
8.isZero()- this function checks if the value of a given polynom is zero.
9.root(double x0, double x1, double eps)- this function returns the closes ( f(x)=y ) y value between two point, which one f(x)=y value has to be negative and the other positive.
10.area(double x0, double x1, double eps)- this function returns the area above axis x and below the polynom between two points (x0,x1) .
11.iterator()- this function provides us iterator that helps us go over our data structure.
we chose hashmap data structure, which doesnt provide its own iterator, and so we had to build a class that implements iterator that uses keyset() ,which has its buildin iterator, to go over the hashmap.
12.derivative()- this function returns the derivative of our exsisted polynom, using a function called derivative in monom class.
13.copy()- this function returns a polynom_able  variable that is a deep copy of our exsisted polynom.

Polynom class has hashMap<int,double> data structue that saves the data of the polynom. 
The key- power of the monom, the value- coefficent of the monom.

The interface Polynom_able extends interface cont_function that extends from function that is also an interface.
For a summary- we have 3 interfaces , which two of them are extends of the others.

ComplexFunction- this class represents what we know about polynoms and monoms wih additional stuff.
We can perform more complex mathematical performance that are not include in monom and polynom class, for instance, multiplying, dividing,
,comp between two functions and etc.
This function uses three main elemnets, left side that is a function, right side that is a function, and an operator that connects them both.
Both right and left side can also be a complexFunction themself.

Function_GUI- this class summaries all the classes and what we can perform on them.
This class has a data structure that contains our functions, can be polynom, monoms, complexFunction.
In this class we can read functions from file into our data structure.
We can show all the functions in a gui window, that represents the polynoms within the variables needed to show them.

Exceptions in the project:
For each excepion we added a msg which describes the kind of the exceptions which can accure from a number of  ilegal actions:
1. Ilegal input Monom/Polynom:
	 - Support real number for thecoeffienct and natural positive number for the power.
	 - Polynom object can be build from the following steps: using x/X, numbers, to describe theshould be put '^'.
	 - Support spaces.
For a diffrent input, the system will throw an exception.
2.Negative area exception
For creating a file Json/reading from file- the file must be in the project directory, otherwise throws exception.
Must know- we chose to round above the value after sending to f function for a value, that way we can perform the methods we wrote better.


Technologies- for this project we used eclipse editor, using java language.
