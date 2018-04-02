#include "2-4tree.h"


void create_lesson(newlesson *mathima, char *courseid, char *coursename, int grade){

*mathima=(newlesson)malloc(sizeof(struct lesson));
(*mathima)->grade=grade;
(*mathima)->courseid=malloc(3*sizeof(char));
strncpy((*mathima)->courseid, courseid, 3);
(*mathima)->coursename=malloc(50*sizeof(char));
strncpy((*mathima)->coursename, coursename, 50);
(*mathima)->next=NULL;
}


void remove_lesson(newlesson mathima){

free(mathima->courseid);
free(mathima->coursename);
free(mathima);
}


void create_node(newnode *komvos){

int i;
*komvos=(newnode)malloc(sizeof(struct node));
(*komvos)->numofids=0;
(*komvos)->isleaf=-1;
(*komvos)->ids=malloc(4*sizeof(int));
for(i=0; i<4; i++)
	(*komvos)->ids[i]=0;
(*komvos)->next=NULL;
(*komvos)->child=malloc(4*sizeof(void *));
for(i=0; i<4; i++)
	(*komvos)->child[i]=NULL;
}


void remove_node(newnode *komvos){

int i;

free((*komvos)->ids);
for(i=0; i<4; i++)
	free((*komvos)->child[i]);
free((*komvos)->child);
free(*komvos);
}


newnode * find_father(newnode *komvos, int studid){

newnode child;
int i;

if(((*komvos)->isleaf)==0){
	for(i=0; i < ((*komvos)->numofids); i++)
		if(studid <= ((*komvos)->ids[i])){
			child=(*komvos)->child[i];
			if((child->isleaf)==0)
				return find_father((newnode *)&((*komvos)->child[i]), studid);
			else
				return komvos;
		}
}
}


int set_node_info(newnode *komvos, int studid, int *index){

int i;
int temporary;
void *temporaryptr, *newptr=NULL;
int done=0;

for(i=0; i<4; i++){
	if((*komvos)->ids[i]==studid){
		//printf("ids[%d] is %d studid is %d and i is %d\n",i, (*komvos)->ids[i], studid, i);
		*index=i;
		printf("studid %d exists\n",studid);
		return 1;	
	}
}	
if(((*komvos)->numofids) < 4){
	for(i=0; i<4; i++){
		if(studid < ((*komvos)->ids[i])){
			printf("studid %d is less than %d\n",studid, (*komvos)->ids[i]);
			temporary=(*komvos)->ids[i];
			(*komvos)->ids[i]=studid;
			studid=temporary;
			if(done==0){
				*index=i;
				done=1;
			}
			//if(((*komvos)->isleaf)==1){
			temporaryptr=(newlesson)(*komvos)->child[i];
			(*komvos)->child[i]=newptr;
			newptr=(newlesson)temporaryptr;
			//}
			//else{			//an o komvos den einai fyllo
			//}
		}
		else if(((*komvos)->ids[i])==0){
			(*komvos)->ids[i]=studid;
			(*komvos)->child[i]=newptr;
			if(done==0){
				*index=i;
				done=1;
			}
			break;
		}
	}
	printf("inserted studid %d\n",studid);
	((*komvos)->numofids)++;
	//(*komvos)->isleaf=isleaf;
	printf("numofids is %d\n", (*komvos)->numofids);
	printf("in set_node_info index is %d\n",*index);
}
else{						//An numofids==4
	/*create_node(&(*neoskomvos));
	if(((*komvos)->isleaf)==1)
		(*neoskomvos)->isleaf=1;
	else
		(*neoskomvos)->isleaf=0;
	
	(*neoskomvos)->ids[0]=(*komvos)->ids[2];
	(*neoskomvos)->ids[1]=(*komvos)->ids[3];
	(*neoskomvos)->child[0]=(*komvos)->child[2];
	(*neoskomvos)->child[1]=(*komvos)->child[3];
	(*komvos)->ids[2]=0;
	(*komvos)->ids[3]=0;
	(*komvos)->next=(*neoskomvos);
	((*komvos)->numofids)-=2;
	((*neoskomvos)->numofids)+=2;*/
	
/*	if(studid > ((*komvos)->ids[3])){
		(*neoskomvos)->ids[0]=(*komvos)->ids[3];
		(*neoskomvos)->child[0]=(*komvos)->child[3];
		(*neoskomvos)->ids[1]=studid;
		*index=5;					//thesi 1 tou neou komvou
		(*komvos)->ids[3]=0;
		(*komvos)->child[3]=NULL;
	}
	else if((studid > ((*komvos)->ids[2])) && studid < ((*komvos)->ids[3])){
		(*neoskomvos)->ids[1]=(*komvos)->ids[3];
		(*neoskomvos)->child[1]=(*komvos)->child[3];
		(*neoskomvos)->ids[0]=studid;
		*index=4;					//thesi 0 tou neou komvou
		(*komvos)->ids[3]=0;
		(*komvos)->child[3]=NULL;		
	}
	else{
		(*neoskomvos)->ids[1]=(*komvos)->ids[3];
		(*neoskomvos)->child[1]=(*komvos)->child[3];
		(*neoskomvos)->ids[0]=(*komvos)->ids[2];
		(*neoskomvos)->child[0]=(*komvos)->child[2];
		(*komvos)->ids[3]=0;
		(*komvos)->child[3]=NULL;
		(*komvos)->ids[2]=0;
		(*komvos)->child[2]=NULL;		
		set_node_info(&(*komvos), studid, index);	
		(*komvos)->next=(*neoskomvos);
	}*/

}
return 0;
}

void print_ids(){
int i;
newnode *komvos1;
newnode *komvos2;
newlesson mathima;

printf("Entered print_ids\n");
komvos1=(root->child[0]);
komvos2=(root->child[1]);
mathima=(*komvos1)->child[0];

for(i=0; i< root->numofids; i++)
	printf("root ids[%d] is %d\n",i, root->ids[i]);
for(i=0; i< (*komvos1)->numofids; i++)
	printf("komvos1 ids[%d] is %d\n",i, (*komvos1)->ids[i]);
for(i=0; i< (*komvos2)->numofids; i++)
	printf("komvos2 ids[%d] is %d\n",i, (*komvos2)->ids[i]);
printf("courseid of mathima is %s\n",mathima->courseid);

}


void find(newnode *komvos, int studid){
int i;
newlesson *mathima;
int index,found;


komvos=(newnode *)find_leaf(komvos, studid, &found, &index);
for(i=0; i<(*komvos)->numofids; i++)
	printf("in find ids[%d] is %d\n",i, (*komvos)->ids[i]);
for(i=0; i<4; i++){
	if(studid==((*komvos)->ids[i])){
		printf("found studid %d\n",studid);
		mathima=(newlesson *)&((*komvos)->child[i]);
		printf("---------------------------------------------------\n");
		printf("courseid %s coursename %s grade %d\n",(*mathima)->courseid, (*mathima)->coursename, (*mathima)->grade);
	}
}
while((*mathima)!=NULL){
	mathima=&((*mathima)->next);
	if((*mathima)==NULL)
		printf("next is NULL\n");
	else{
		printf("next is not NULL\n");
		printf("courseid %s coursename %s grade %d\n",(*mathima)->courseid, (*mathima)->coursename, (*mathima)->grade);
	}
}
}


void check_find(){

newnode komvos1, komvos21, komvos22, komvos31, komvos32, komvos33, komvos34;
newnode *neoskomvos;
int found;
int index;
int i;

//na dokimaso th find
printf("Entered check_find\n");
create_node(&komvos1);
create_node(&komvos21);
create_node(&komvos22);
create_node(&komvos31);
create_node(&komvos32);
create_node(&komvos33);
create_node(&komvos34);
komvos1->ids[0]=15;
komvos1->ids[1]=30;
komvos1->isleaf=0;
komvos1->numofids=2;

komvos21->ids[0]=11;
komvos21->ids[1]=15;
komvos21->isleaf=0;
komvos21->numofids=2;

komvos22->ids[0]=20;
komvos22->ids[1]=30;
komvos22->isleaf=0;
komvos22->numofids=2;

komvos31->ids[0]=3;
komvos31->ids[1]=11;
komvos31->isleaf=1;
komvos31->numofids=2;
create_lesson(&(komvos31->child[0]), "K31", "neomathima", 10);

komvos32->ids[0]=13;
komvos32->ids[1]=15;
komvos32->isleaf=1;
komvos32->numofids=2;
create_lesson(&(komvos32->child[0]), "K32", "neomathima", 10);

komvos33->ids[0]=16;
komvos33->ids[1]=20;
komvos33->isleaf=1;
komvos33->numofids=2;
create_lesson(&(komvos33->child[0]), "K33", "neomathima", 10);

komvos34->ids[0]=25;
komvos34->ids[1]=30;
komvos34->isleaf=1;
komvos34->numofids=2;
create_lesson(&(komvos34->child[0]), "K34", "neomathima", 10);

komvos1->child[0]=komvos21;
komvos1->child[1]=komvos22;

komvos21->child[0]=komvos31;
komvos21->child[1]=komvos32;

komvos22->child[0]=komvos33;
komvos22->child[1]=komvos34;

neoskomvos=find_leaf(&komvos1, 11, &found, &index);
for(i=0; i<((*neoskomvos)->numofids); i++)
	printf("neoskomvos ids[%d] is %d\n",i, (*neoskomvos)->ids[i]);
neoskomvos=find_leaf(&komvos1, 13, &found, &index);
for(i=0; i<((*neoskomvos)->numofids); i++)
	printf("neoskomvos ids[%d] is %d\n",i, (*neoskomvos)->ids[i]);
neoskomvos=find_leaf(&komvos1, 20, &found, &index);
for(i=0; i<((*neoskomvos)->numofids); i++)
	printf("neoskomvos ids[%d] is %d\n",i, (*neoskomvos)->ids[i]);
neoskomvos=find_leaf(&komvos1, 28, &found, &index);
for(i=0; i<((*neoskomvos)->numofids); i++)
	printf("neoskomvos ids[%d] is %d\n",i, (*neoskomvos)->ids[i]);

printf("/////////////////////////////\n");
find(&komvos1, 11);
}


/*newlesson * find_lesson(newlesson *mathima, char *courseid){

if(!strncmp((*mathima)->courseid, courseid, 3))
	return &(*mathima);
else
	return find_lesson(&((*mathima)->next), courseid);
}
*/

void find_lesson(newlesson *mathima, char *courseid, int *n){


//na epistrefei newlesson *
//na exei orisma ena n pou na afksanetai kathe fora 
// sth delete for(i=0; i<n-1 i++)
//(*mathima)=&((*mathima)->next)
//printf("in find_lesson courseid is %s\n",courseid);
//printf("in find_lesson courseid of mathima is %s\n",(*mathima)->courseid);
if(!strncmp((*mathima)->courseid, courseid, 3)){
	//printf("in find_lesson courseid of mathima is %s\n",(*mathima)->courseid);
	return;
}
else{
	//printf("in find_lesson courseidis not %s\n",(*mathima)->courseid);
	(*n)++;
	mathima=&((*mathima)->next);
	return find_lesson(&(*mathima), courseid, n);
}
}


int exist(newnode *komvos, int studid){
int i;

for(i=0; i < ((*komvos)->numofids); i++)
	if(((*komvos)->ids[i])==studid)
		return i;
return -1;
}

newlesson * find_last(newlesson *mathima){

if(((*mathima)->next)==NULL)
	return &(*mathima)->next;
else
	return find_last(&(*mathima)->next);
}

newnode * find_leaf(newnode *komvos, int studid, int *found, int *index){
int i;

*found=0;
if(((*komvos)->isleaf)==1){
	for(i=0; i<((*komvos)->numofids); i++){
		if(((*komvos)->ids[i])==studid){
			*index=i;
			*found=1;
		}
	}
	printf("in find_leaf komvos is leaf\n");
	for(i=0; i<((*komvos)->numofids); i++)
		printf("in find_leaf ids[%d] of leaf is %d\n",i, (*komvos)->ids[i]);
	return &(*komvos);
}
else{
	for(i=0; i<((*komvos)->numofids); i++){
		if(studid <= ((*komvos)->ids[i])){
			printf("in find_leaf studid is %d and ids[%d] is %d\n",studid, i, (*komvos)->ids[i]);
			return find_leaf((newnode *)&(*komvos)->child[i], studid, found, index);
		}
	}
}
}


newnode find_leaf1(newnode *komvos, int studid, int *found, int *index){
int i;

printf("entered find_leaf1\n");
*found=0;
if(((*komvos)->isleaf)==1){
	printf("is leaf is 1\n");
	for(i=0; i<((*komvos)->numofids); i++){
		if(((*komvos)->ids[i])==studid){
			*index=i;
			*found=1;
		}
	}
	for(i=0; i<((*komvos)->numofids); i++)
		printf("in find_leaf ids[%d] of leaf is %d\n",i, (*komvos)->ids[i]);
	printf("exiting find_leaf1\n");
	return (*komvos);
}
else{
	printf("isleaf is %d\n",(*komvos)->isleaf);
	for(i=0; i<((*komvos)->numofids); i++){
		if(studid <= ((*komvos)->ids[i])){
			printf("in find_leaf studid is %d and ids[%d] is %d\n",studid, i, (*komvos)->ids[i]);
			return find_leaf1((*komvos)->child[i], studid, found, index);
		}
	}
}
}


void break_node(newnode *riza, int studid, newnode *pointer, newnode *nextpointer, char *courseid, char *coursename, int grade){		
newnode neoskomvos;					//na valo na dinetai san orisma kai o komvos pou prepei an spasei
int found=0, index=0;
newnode neariza;
newnode *komvos;
newnode *child;
newnode brother;
int newindex;
int i;
int n,m,k=0;
newnode *komvos1;
newnode *komvos2;
newlesson *mathima;
newnode newpointer;
newlesson neomathima;
void *lessons[4];


printf("-----------------------------entered break_node---------------------------------\n");
printf("numofids of riza is %d\n",(*riza)->numofids);
for(i=0; i<((*riza)->numofids); i++)
	printf("ids is %d\n",(*riza)->ids[i]);
printf("/////////////////////\n");
if(pointer==NULL){
	printf("---------------entered if ------------------\n");
	pointer=find_leaf(riza, studid, &found, &index);		// vriskei to node pou prepei na mpei to id
	create_node(&neoskomvos);
	printf("created newnode\n");			//isos h find_leaf na mh xreiazetai *
	if(((*pointer)->isleaf)==1){			//exei xrhsimopoihthei h find_leaf
		(neoskomvos)->isleaf=1;
		(neoskomvos)->next=(*pointer)->next;		// ftiaxnei ta next
		(*pointer)->next=(neoskomvos);
	}
	else
		(neoskomvos)->isleaf=0;	
	(neoskomvos)->ids[0]=(*pointer)->ids[2];		// moirazei ta ids
	(neoskomvos)->ids[1]=(*pointer)->ids[3];
	(neoskomvos)->child[0]=(*pointer)->child[2];
	(neoskomvos)->child[1]=(*pointer)->child[3];
	(*pointer)->ids[2]=0;
	(*pointer)->ids[3]=0;
	(*pointer)->child[2]=NULL;				//isos einai lathos
	(*pointer)->child[3]=NULL;				//isos einai lathos
	((*pointer)->numofids)-=2;
	((neoskomvos)->numofids)+=2;
	n=(*pointer)->ids[(*pointer)->numofids-1];
	m=neoskomvos->ids[neoskomvos->numofids-1];
	mathima=&(neoskomvos->child[0]);
	printf("courseid of mathima is %s\n", (*mathima)->courseid);
	printf("///////////// n is %d, m is %d and studid is %d ////////////////////\n",n, m, studid);
	if(studid<=n){
		printf("studid will be inserted to pointer\n");
		//set_node_info(pointer, studid, &newindex);
		//void insert(newnode *komvos, int studid, char *courseid, char *coursename, int grade)
		insert(pointer, studid, courseid, coursename, grade);
	}
	else{
		printf("studid will be inserted to neoskomvos\n");
		//set_node_info(&neoskomvos, studid, &newindex);
		insert(&neoskomvos, studid, courseid, coursename, grade);
	}
	printf("after break\n");
	for(i=0; i<((*pointer)->numofids); i++)
		printf("pointer ids[%d] is %d\n",i, ((*pointer)->ids[i]));
	for(i=0; i<(neoskomvos->numofids); i++)
		printf("neoskomvos ids[%d] is %d\n",i, (neoskomvos->ids[i]));	
	break_node(&(*riza), studid, pointer, &neoskomvos, courseid, coursename, grade);		// kalei tin break gia to akrivos pano epipedo
}
else if(riza==pointer){							// an to root einai gemato, ftiaxnei neo kai vazei ta dio ids kai pointers
	printf("---------------entered else if ------------------\n");
	for(i=0; i<((*pointer)->numofids); i++)
		printf("pointer ids[%d] is %d\n",i, ((*pointer)->ids[i]));
	if((*nextpointer)==NULL)
		printf("nextpointer is NULL\n");
	else{
		for(i=0; i<((*nextpointer)->numofids); i++)
			printf("nextpointer ids[%d] is %d\n",i, ((*nextpointer)->ids[i]));	
	}
	mathima=&((*nextpointer)->child[0]);
	printf("***************************************************\n");
	printf("courseid of mathima is %s\n", (*mathima)->courseid);	
	create_node(&neariza);
	neariza->isleaf=0;
	(neariza)->child[0]=pointer;
	(neariza)->ids[0]=(*pointer)->ids[(*pointer)->numofids -1];
	//nextpointer=&(*pointer)->next;			//prepei na svistei
	(neariza)->child[1]=nextpointer;
	(neariza)->ids[1]=(*nextpointer)->ids[(*nextpointer)->numofids -1];		//na dhloso th riza global
	(neariza->numofids)+=2;
	create_node(&newpointer);
	//*riza=neariza;				//------------------ isos xreiazetai &
	for(i=0; i<(root->numofids); i++){
		newpointer->ids[i]=root->ids[i];
		newpointer->child[i]=root->child[i];
	}
	newpointer->isleaf=root->isleaf;
	newpointer->numofids=root->numofids;
	newpointer->next=nextpointer;
	//neomathima=newpointer->child[0];
	//printf("*********** courseid of neomathima is %s and coursename is %s ***************************\n",neomathima->courseid, neomathima->coursename);
	//newpointer=root;
	printf("newpointer ids[0] is %d\n",newpointer->ids[0]);
	printf("root ids[0] is %d\n",root->ids[0]);
	neomathima=newpointer->child[0];
	printf("*********** courseid of neomathima is %s and coursename is %s ***************************\n",neomathima->courseid, neomathima->coursename);
	remove_node(&root);
	neomathima=newpointer->child[0];
	printf("*********** after remove_node courseid of neomathima is %s and coursename is %s ***************************\n",neomathima->courseid, neomathima->coursename);
	printf("after remove_node newpointer ids[0] is %d and ids[1] is %d\n",newpointer->ids[0], newpointer->ids[1]);
	create_node(&root);
	//root=neariza;
	for(i=0; i<(neariza->numofids); i++){
		root->ids[i]=neariza->ids[i];
	}
	root->isleaf=neariza->isleaf;
	root->numofids=neariza->numofids;
	root->next=NULL;
	root->child[0]=&newpointer;
	for(i=0; i<newpointer->numofids; i++)
		printf("newpointer ids[%d] is %d\n",i, newpointer->ids[i]);
	komvos1=(root->child[0]);
	for(i=0; i<(*komvos1)->numofids; i++)
		printf("komvos1 ids[%d] is %d\n",i, (*komvos1)->ids[i]);
	root->child[1]=nextpointer;
	printf("numofids of neariza is %d\n",(neariza)->numofids);
	/*for(i=0; i<((neariza)->numofids); i++)
		printf("neariza ids[%d] is %d\n",i, (neariza)->ids[i]);
	printf("numofids of riza is %d\n",(*riza)->numofids);
	for(i=0; i<((*riza)->numofids); i++)
		printf("riza ids[%d] is %d\n",i, (*riza)->ids[i]);*/
	printf("numofids of root is %d\n",root->numofids);
	for(i=0; i<(root->numofids); i++)
		printf("root ids[%d] is %d\n",i, root->ids[i]);
	/*komvos1=(root->child[0]);
	for(i=0; i<(*komvos1)->numofids; i++)
		printf("komvos1 ids[%d] is %d\n",i, (*komvos1)->ids[i]);*/
	printf("***************************************************\n");
	komvos1=(root->child[0]);	
	komvos2=(root->child[1]);
	for(i=0; i< root->numofids; i++)
		printf("root ids[%d] is %d\n",i, root->ids[i]);
	for(i=0; i< (*komvos1)->numofids; i++)
		printf("komvos1 ids[%d] is %d\n",i, (*komvos1)->ids[i]);
	for(i=0; i< newpointer->numofids; i++)
		printf("newpointer ids[%d] is %d\n",i, newpointer->ids[i]);
	for(i=0; i< (*komvos2)->numofids; i++)
		printf("komvos2 ids[%d] is %d\n",i, (*komvos2)->ids[i]);
	neomathima=(*komvos1)->child[0];
	printf("*********** courseid of neomathima is %s and coursename is %s***************************\n",neomathima->courseid, neomathima->coursename);
	print_ids();
}
else{					// elegxei ena ena epipedo mexri na vrei ton patera tou node pou espase, gia na ananeosei ta ids
printf("---------------entered else ------------------\n");
komvos=riza;											//************ tha prepei na mhdenistoun oi theseis tou komvou otan antigrapsei ta ids ********************
for(i=0; i < ((*komvos)->numofids); i++){
	if(studid <= ((*komvos)->ids[i])){
		child=(*komvos)->child[i];
		if(child==pointer){
			if(((*komvos)->numofids)==4){				//--------------------
				create_node(&brother);				 // moirazei ta ids sto neo node
				(brother)->isleaf=(*komvos)->isleaf;
				(brother)->ids[0]=(*komvos)->ids[2];
				(brother)->ids[1]=(*komvos)->ids[3];
				(brother)->child[0]=(*komvos)->child[2];
				(brother)->child[1]=(*komvos)->child[3];
				((*komvos)->numofids)-=2;
				((brother)->numofids)+=2;
				if(i<=1){					 //vlepei an to 5o id prepei na mpei sto aristero i sto dexi node
					(*komvos)->ids[i+2]=(*komvos)->ids[i+1];
					(*komvos)->child[i+2]=(*komvos)->child[i+1];
					(*komvos)->ids[i]=(*pointer)->ids[(*pointer)->numofids -1];
					(*komvos)->ids[i+1]=(*nextpointer)->ids[(*nextpointer)->numofids -1];
					(*komvos)->child[i+1]=nextpointer;
					((*komvos)->numofids)++;
				}
				else{
					(brother)->ids[i+2]=(brother)->ids[i+1];
					(brother)->child[i+2]=(brother)->child[i+1];
					(brother)->ids[i]=(*pointer)->ids[(*pointer)->numofids -1];
					(brother)->ids[i+1]=(*nextpointer)->ids[(*nextpointer)->numofids -1];
					(brother)->child[i+1]=nextpointer;
					((brother)->numofids)++;
				}
				break_node(&(*riza), studid, &(*komvos), &brother, courseid, coursename, grade);
				
			}
			else{				// an exei xoro apla prosthetei to id
				(*komvos)->ids[i]=(*pointer)->ids[(*pointer)->numofids -1];		//isos tha prepei na taksinomithei
				(*komvos)->ids[i+1]=(*nextpointer)->ids[(*nextpointer)->numofids -1];
				(*komvos)->child[i+1]=nextpointer;
				return;
			}
		}
		else{
			break_node(child, studid, pointer, nextpointer, courseid, coursename, grade);
		}	
	}
}	
	
}

printf("exiting break_node\n");
}


void insert_lesson(newlesson *mathima, char *courseid, char *coursename, int grade){

//newlesson *neomathima;

printf("entered insert_lesson\n");
if(((*mathima)->next)==NULL)
	printf("next is NULL\n");
create_lesson(find_last(mathima), courseid, coursename, grade);
//create_lesson(&(*mathima)->next, courseid, coursename, grade);
//(*mathima)->next=*neomathima;
printf("grade of mathima is %d\n",(*mathima)->grade);
//printf("grade of neomathima is %d\n",(*neomathima)->grade);
}

void reset_lesson(newlesson *mathima, int n){
//for(i=0; i<n; i++)
printf("courseid in reset_lesson is %s\n", (*mathima)->courseid);
mathima=&((*mathima)->next);
if(mathima==NULL)
	printf("mathima is NULL\n");
else
	printf("courseid of next in reset_lesson is %s\n", (*mathima)->courseid);
}

///////////////////////////////////// functions ///////////////////////////////////////////////////////////

newnode initialize(){

levels=-1;
empty=1;
return NULL;
}


void insert(newnode *komvos, int studid, char *courseid, char *coursename, int grade){

int index;
int exists;
int n=0;
int k=1;
newnode pointer=NULL;
newnode nextpointer=NULL;


if(*komvos==NULL){		//An to root deixnei se NULL
	create_node(komvos);
	if(*komvos==NULL)
		printf("after create komvos is NULL\n");
	(*komvos)->isleaf=1;
	exists=set_node_info(&(*komvos), studid, &index);			//na do to isleaf
	printf("index is %d\n",index);
	create_lesson((newlesson *)&(*komvos)->child[index], courseid, coursename, grade);
	//insert_lesson(&(*komvos)->child[index], courseid, coursename, grade);
}
else{
	//na valo th find_leaf
	//if(((*komvos)->isleaf)==1){					//komvos=find_leaf()
		if(((*komvos)->numofids) < 5){
			if(((*komvos)->numofids) ==4){
				n=exist(&(*komvos), studid);
				if(n<0)
					k=1;
			}
			 if(n>=0){
				k=0;
				exists=set_node_info(&(*komvos), studid, &index);
				printf("index is %d\n",index);
				if(exists==1){
					if(((*komvos)->child[index])!=NULL){
						//printf("index is %d\n",index);
						insert_lesson((newlesson *)&(*komvos)->child[index], courseid, coursename, grade);
					}
			
				}
				else{
					printf("studid %d does not exist\n",studid);
					//printf("index is %d\n",index);
					if(((*komvos)->child[index])==NULL)
						create_lesson((newlesson *)&((*komvos)->child[index]), courseid, coursename, grade);		//----------------------------
					else
						insert_lesson((newlesson *)&(*komvos)->child[index], courseid, coursename, grade);
				}
			}
		}
		if(k==1){					//anti gia else na mpei ena if pou na elegxei mia timh gia thn periptosh pou n<0
			//printf("inserted fifth student\n");
			
			break_node(&(*komvos), studid, pointer, nextpointer, courseid, coursename, grade);		//-------
			//break_node(&(*komvos), studid, NULL, NULL);
			//insert(&(*komvos), studid, courseid, coursename, grade);
			
			
			/*set_node_info(&(*komvos), studid, &index);
			if(index==4 || index==5)
				create_lesson(&((*komvos)->next)->child[index], courseid, coursename, grade);
			else
				create_lesson(&(*komvos)->child[index], courseid, coursename, grade);*/
		}
	//}
//	else
//		printf("isleaf is %d\n", (*komvos)->isleaf);
}


}

newlesson * find_next(newlesson *mathima, int n, int *i){
if((*i) < n){
	(*i)++;
	return find_next(&((*mathima)->next), n, i);
}
else
	return mathima;
}

void delete(newnode *komvos, int studid, char *courseid){

newnode *leaf;
int found,index;
newlesson *temporary;
newlesson newtemporary;
newlesson *mathima;
int n=0;
int i=0;

leaf=find_leaf(&(*komvos), studid, &found, &index);
mathima=(newlesson *)&((*leaf)->child[index]);							//--------------an xreiastei na kano casting
if(mathima!=NULL)
	printf("in delete courseid is %s\n", (*mathima)->courseid);
find_lesson(&(*mathima), courseid, &n);
if(n==0)
	(*leaf)->child[index]=(*mathima)->next;
else{
	n=n-1;
	temporary=find_next(mathima, n, &i);
	if(temporary!=NULL){
		printf("courseid is %s\n",(*temporary)->courseid);
		newtemporary=(*temporary)->next;
		(*temporary)->next=((*temporary)->next)->next;
		remove_lesson(newtemporary);
	}
}
}


void load(char *inputfile, newnode *komvos){			//den exei thn rlookup

FILE *fp;
char instruction[30];
int i;
int studid1;
char courseid[5];
char coursename[50];
int grade;

for(i=0; i<5; i++)
	courseid[i]='\0';
for(i=0; i<50; i++)
	coursename[i]='\0';
for(i=0; i<30; i++)
	instruction[i]='\0';

if((fp=fopen(inputfile,"r"))==NULL)
	printf("Cannot open file %s\n",inputfile);
while(!feof(fp)){
	fscanf(fp,"%s",instruction);	
	if(!strncmp(instruction, "insert", 6)){
		fscanf(fp,"%d %s %s %d\n",&studid1, courseid, coursename, &grade);
		insert(komvos, studid1, courseid, coursename, grade);
	}
	if(!strncmp(instruction, "lookup", 6)){
		fscanf(fp,"%d",&studid1);
		find(&(*komvos), studid1);
	}
}
}

void reset_info(newnode *komvos, newnode *pointer, int newstudid, int oldstudid){

int i;
newnode *child;

for(i=0; i < ((*komvos)->numofids); i++){
	if(oldstudid <= ((*komvos)->ids[i])){
		child=(*komvos)->child[i];
		if(child==pointer){
		}
	}
}
}


void merge_node(newnode *komvos, newnode *pointer, int studid){

int i;
int found=0;
newnode *father;
newnode *child;
newnode *newpointer;
int newid;

for(i=0; i < ((*komvos)->numofids); i++){
	if(studid <= ((*komvos)->ids[i])){
		child=(*komvos)->child[i];
		if(child==pointer){
			father=komvos;
			if(i!=0){
				newpointer=(*komvos)->child[i-1];
				if(((*newpointer)->numofids)>2)
					found=1;
			}
			if((i!=3) && (found!=1)){
				newpointer=(*komvos)->child[i+1];
				if(((*newpointer)->numofids)>2)
					found=1;		
			}
			if(found==1){
				newid=(*newpointer)->ids[(*newpointer)->numofids-1];
				(*pointer)->ids[(*pointer)->numofids-1]=newid;
				(*pointer)->child[(*pointer)->numofids-1]=(*newpointer)->child[(*newpointer)->numofids-1];
				((*pointer)->numofids)--;
				(*newpointer)->numofids++;
				reset_info(komvos, pointer, newid, studid);
			}
			else{							//an ta aderfia exoun numofids=2
			
			
			}
		}
	}
}
}


void sdelete(newnode *komvos, int studid){

newnode *leaf;
int found,index;
int n;
int i;
newlesson *mathima;
newlesson temporary;

leaf=find_leaf(&(*komvos), studid, &found, &index);
n=(*leaf)->numofids;
if(found==0)
	printf("Cannot find student %d\n",studid);
else{
	mathima=(newlesson *)&((*leaf)->child[index]);
	while(((*mathima)->next)!=NULL){
		temporary=(*mathima)->next;
		(*mathima)->next=((*mathima)->next)->next;
		remove_lesson(temporary);
	}
	temporary=*mathima;
	(*leaf)->child[index]=NULL;
	remove_lesson(temporary);
	for(i=index; i<3; i++){
		(*leaf)->ids[i]=(*leaf)->ids[i+1];
		(*leaf)->child[i]=(*leaf)->child[i+1];
	}
	(*leaf)->ids[3]=0;
	if(n==3)
		(*leaf)->ids[2]=0;
	((*leaf)->numofids)--;
	if(index==n-1)
		reset_info(komvos, leaf, (*leaf)->ids[(*leaf)->numofids-1], studid);			
	
	if(n>2)
		merge_node(komvos, leaf, studid);
}
}


void rlookup(newnode komvos, int studid1, int studid2){			//na valo sth lookup th find_leaf
int i;
newlesson mathima;
newnode next;
int found, index;

komvos=find_leaf1(&komvos, studid1, &found, &index);
printf("entered rlookup after find_leaf1\n");
if(found==0)
	printf("Could not find %d\n",studid1);
//while(1){
	for(i=0; i<4; i++){
		if((studid1 <= ((komvos)->ids[i])) && (studid2 >= ((komvos)->ids[i]))){
			//printf("found studid %d\n",studid);
			mathima=(komvos)->child[i];
			printf("---------------------------------------------------\n");
			printf("studid %d\n",(komvos)->ids[i]);
			printf("courseid %s coursename %s grade %d\n",mathima->courseid, mathima->coursename, mathima->grade);
			while(mathima!=NULL){
				mathima=mathima->next;
				if(mathima==NULL)
					printf("next is NULL\n");
				else{
					printf("next is not NULL\n");
					printf("courseid %s coursename %s grade %d\n",mathima->courseid, mathima->coursename, mathima->grade);
				}
			}
		}
	}
next=(komvos)->next;
//if(next==NULL)
//	break;
//else
if(next!=NULL)
	rlookup(&next, studid1, studid2);
//}
}
















