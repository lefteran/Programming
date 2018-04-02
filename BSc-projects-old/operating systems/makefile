# Variable to delete executable and objects
RM=/bin/rm -f

# Rule to create the final program
all: court immigrant judge spectator

# Compile arguments:
# -Wall: warnings
# -g: debugging symbols

court: court.o
	$(CC) -o court court.o -lrt

court.o: court.c
	$(CC) -c court.c 

immigrant: immigrant.o
	$(CC) -o immigrant immigrant.o -lrt

immigrant.o: immigrant.c
	$(CC) -c immigrant.c 

judge: judge.o
	$(CC) -o judge judge.o -lrt

judge.o: judge.c
	$(CC) -c judge.c 

spectator: spectator.o
	$(CC) -o spectator spectator.o -lrt

spectator.o: spectator.c
	$(CC) -c spectator.c 


# Rule to delete executable and objects
clean:
	$(RM) court.o
	$(RM) court
	$(RM) immigrant.o
	$(RM) immigrant
	$(RM) judge.o
	$(RM) judge
	$(RM) spectator.o
	$(RM) spectator
