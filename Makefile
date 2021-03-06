JFLAGS = -g -J-Xmx5m -J-Xmx4m
JC = javac
.SUFFIXES: .java .class
.java.class:
	 $(JC) $(JFLAGS) $*.java

CLASSES = \
	ABPuzzle.java \
	InputGetter.java \
	Board.java \
	Piece.java \
	State.java \
	Goal.java \
	NodeList.java \
	Search.java \
	SearchNode.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
run:
	java -Xmx256m ABPuzzle $(arg)
