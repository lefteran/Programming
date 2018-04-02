#include "court.h"


void enter(sem_t *inside, sem_t *spectin){

sem_wait(inside);
sem_wait(spectin);
printf("O dikasths eiserxetai sthn aithousa\n");
	
}

void checklist(sem_t *entered, int *checklist, sem_t *judgein){

int i;
int found;

while(1){
	found=0;
	for(i=0; i<NUMOFIMS; i++){
		if(checklist[i]==0)
			found=1;
	}
	if(!found)
		break;
}

sem_post(entered);
sem_wait(judgein);
}

void speakAdminOath(){
	
printf("O dikasths kalosorizei tous ypopshfious neous polites\n");
	
}

void confirm(sem_t *confirmed, char *name, int isonlist, int upconfirmed){

if(isonlist)
	printf("O neos poliths %s phre to pistopoihtiko tou\n",name);
if(upconfirmed)
	sem_post(confirmed);
}

void leave(sem_t *judgein, sem_t *checked, sem_t *spectin, sem_t *inside){
	
printf("O dikasths ekserxetai apo thn aithousa\n");
sem_post(checked);
sem_post(spectin);
sem_post(inside);
sem_post(judgein);
}

int main(int argc, char **argv) { 
   
		
int id, err; 
void *shared_memory=(void *)0;
shmstr *shared_struct;
FILE *fp;
int i;
int upconfirmed=0;	
int isonlist;
	
if((fp=fopen("tempfile","r"))==NULL){
	printf("Could not open file tempfile\n");
	exit(1);
}
fscanf(fp, "%d",&id);
printf("id is %d\n",id);
fclose(fp);

//---------------------- Attach shared memory ---------------------------------------
shared_memory=shmat(id, (void*) 0,0); 
if(shared_memory==(void *)-1){
	perror("Attachment."); 
	exit(1);
}
shared_struct=(shmstr *)shared_memory;

//enter
enter(&(shared_struct->inside), &(shared_struct->spectin));

//checklist
checklist(&(shared_struct->entered), shared_struct->checklist, &(shared_struct)->judgein);

//speakadminoath
speakAdminOath();

//confirm
for(i=0; i<NUMOFIMS; i++){
	if(i==NUMOFIMS-1)
		upconfirmed=1;
	if((shared_struct->checklist[i])!=-1)
		isonlist=1;
	else
		isonlist=0;
	confirm(&(shared_struct->confirmed),shared_struct->name[i], isonlist, upconfirmed);
}
printf("Oloi oi metanastes orkisthkan\n");

//leave
leave(&(shared_struct->judgein), &(shared_struct->checked), &(shared_struct->spectin), &(shared_struct->inside));

//---------------------- Detach shared memory ---------------------------------------
if((shmdt(shared_memory))==-1){
	perror ("Detach."); 
	exit(1);
}
return 0; 
}
