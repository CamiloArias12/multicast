#Makefile for java projects

#paths of files
SOURCES=$(wildcard  src/**/**/*.java src/**/*.java src/*.java)

all:  compile run 
	

compile:  
	@javac -cp out/ -d out/ $(SOURCES)

	

run: 
	@java -cp out/ Run

clean: 
	rm -Rf out/
