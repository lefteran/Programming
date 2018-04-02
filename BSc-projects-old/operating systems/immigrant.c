#include "court.h"


void enter(sem_t *checked, char *name, int *pid, int *checklist, sem_t *inside){

sem_wait(inside);
sem_wait(checked);
printf("O metanastis eiserxetai sthn aithousa\n");	
*pid=getpid();
printf("Please give a name: ");
scanf("%s",name);
printf("checked was up\n");
*checklist=0;
sem_post(checked);
sem_post(inside);

}

void checkin(sem_t *checked, int *pid, int *checklist){

int i;

sem_wait(checked);
for(i=0; i<NUMOFIMS; i++){
	if(pid[i]==getpid()){
		checklist[i]=1;
		printf("Immigrant %d checked-in\n",pid[i]);
	}		

}
sem_post(checked);
}

void sitDown(){

printf("O metanasths %d kathise sth thesh tou\n",getpid());

}

void takeOath(sem_t *entered){

sem_wait(entered);
printf("O metanasths %d phre orko\n",getpid());
sem_post(entered);

}

void getCertificate(sem_t *confirmed){

sem_wait(confirmed);
printf("O neos poliths %d phre phre to pistopoihtiko tou\n",getpid());
sem_post(confirmed);

}


void leave(sem_t *judgein){

sem_wait(judgein);
printf("O metanastis ekserxetai apo thn aithousa\n");
sem_post(judgein);
}

int main(int argc, char **argv) { 
   
		
int id, err; 
void *shared_memory=(void *)0;
shmstr *shared_struct;
FILE *fp;
int n,i;
int allchecked=1;

	
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
n=shared_struct->curindex;
n++;
if(n==NUMOFIMS){
	printf("Court is full. You are not allowed to enter\n");
	sem_post(&(shared_struct->checked));
	exit(1);
}
//enter
enter(&(shared_struct->checked), shared_struct->name[shared_struct->curindex], 
&(shared_struct->pid[shared_struct->curindex]), &shared_struct->checklist[shared_struct->curindex],
&(shared_struct->inside));
(shared_struct->curindex)++;

//checkin
checkin(&(shared_struct->checked), shared_struct->pid, shared_struct->checklist);

//sitdown
sitDown();

//takeOath
takeOath(&(shared_struct->entered));

//getcertificate
getCertificate(&(shared_struct->confirmed));

//leave
leave(&(shared_struct->judgein));

//---------------------- Detach shared memory ---------------------------------------
if((shmdt(shared_memory))==-1){
	perror ("Detach."); 
	exit(1);
}
return 0; 
}
