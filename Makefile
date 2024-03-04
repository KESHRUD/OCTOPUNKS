all :
	javac src/controller/*.java
	javac src/files/*.java
	javac levels/*.java
	javac src/model/*.java
	javac src/view/*.java

clean :
	rm -rf src/controller/*.class
	rm -rf src/files/*.class
	rm -rf src/model/*.class
	rm -rf src/view/*.class


controller :
	javac src/controller/*.java

files :
	javac src/files/*.java

levels :
	javac levels/*.java

model :
	javac src/model/*.java

tests :
	javac tests/*.java

view :
	javac src/view/*.java
