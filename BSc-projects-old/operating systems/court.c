#include "court.h"
#include <semaphore.h>


int main(int argc, char **argv){ 

int id=0, err=0; 
void *shared_memory=(void *)0;
shmstr *shared_struct;
char input[30];
FILE *fp;
int retval;
sem_t newsem;
int i;


if((fp=fopen("tempfile","w+"))==NULL){
	printf("Could not create file tempfile\n");
	exit(1);
}
//---------------------- Get shared memory ---------------------------------------
id=shmget(IPC_PRIVATE,sizeof(shmstr),0666 | IPC_CREAT);				/* Make shared memroy segment */ 
if(id==-1){
	perror ("Creation"); 
	exit(1);
}

fprintf(fp,"%d",id);

fclose(fp);
//---------------------- Attach shared memory ---------------------------------------
shared_memory=shmat(id, (void *)0, 0);			/* Attach the segment */ 
if(shared_memory==(void *)-1){
	perror("Attachment."); 
	exit(1);
}

shared_struct=(shmstr *)shared_memory;
for(i=0; i<NUMOFIMS; i++){
	shared_struct->checklist[i]=-1;	
}
shared_struct->curindex=0;
retval = sem_init(&(shared_struct->checked),1,1);
if (retval != 0) {
	perror("Couldn't initialize.");
	exit(3);
}
retval = sem_init(&(shared_struct->judgein),1,1);
if (retval != 0) {
        perror("Couldn't initialize.");
        exit(3);
}
retval = sem_init(&(shared_struct->entered),1,1);
if (retval != 0) {
        perror("Couldn't initialize.");
        exit(3);
}
sem_wait(&(shared_struct->entered));
retval = sem_init(&(shared_struct->confirmed),1,1);
if (retval != 0) {
        perror("Couldn't initialize.");
        exit(3);
}
sem_wait(&(shared_struct->confirmed));
retval = sem_init(&(shared_struct->spectin),1,1);
if (retval != 0) {
        perror("Couldn't initialize.");
        exit(3);
}
retval = sem_init(&(shared_struct->inside),1,1);
if (retval != 0) {
        perror("Couldn't initialize.");
        exit(3);
}
printf("Immigrants and spectators may now enter >");
while(1){
	scanf("%s",input);
	if(!strncmp(input,"exit",4))
		break;
}


sem_destroy(&(shared_struct->checked));
sem_destroy(&(shared_struct->judgein));
sem_destroy(&(shared_struct->entered));
sem_destroy(&(shared_struct->confirmed));
sem_destroy(&(shared_struct->inside));
//---------------------- Detach shared memory ---------------------------------------
if((shmdt(shared_memory))==-1){
	perror ("Detach."); 
	exit(1);
}

//---------------------- Remove shared memory ---------------------------------------
if((shmctl(id, IPC_RMID, 0))==-1){
	perror ("Removal."); 
	exit(1);
}

remove("tempfile");

return 0; 
}










