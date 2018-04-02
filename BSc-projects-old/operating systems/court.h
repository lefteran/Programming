#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include <sys/types.h> 
#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <sys/sem.h>
#include <unistd.h>
#include <semaphore.h>
#define NUMOFIMS 20


typedef struct shmstr{
	sem_t judgein;
	sem_t checked;
	sem_t confirmed;
	sem_t entered;
	sem_t spectin;
	sem_t inside;
	int pid[NUMOFIMS];
	char name[NUMOFIMS][30];
	int checklist[NUMOFIMS];
	int curindex;
}shmstr;
