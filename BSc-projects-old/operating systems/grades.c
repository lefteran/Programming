#include "2-4tree.h"




int main(){

char instruction[100];
int studid1,studid2;
char courseid[5];
char coursename[50];
char inputfile[40];
int i,start=1;
int grade;
//int n=1;
//newlesson child;

for(i=0; i<5; i++)
	courseid[i]='\0';
for(i=0; i<50; i++)
	coursename[i]='\0';
for(i=0; i<40; i++)
	inputfile[i]='\0';
for(i=0; i<100; i++)
	instruction[i]='\0';

while((strncmp(instruction,"exit",4)) || (start==1)){
	start=0;
	printf("Please give an instruction: ");
	scanf("%s",instruction);
	if(!strncmp(instruction, "initialize", 10)){
		root=initialize();
	}
	if(!strncmp(instruction, "insert", 6)){
		scanf("%d %s %s %d",&studid1, courseid, coursename, &grade);
		insert(&root, studid1, courseid, coursename, grade);
		if(root==NULL)
			printf("after insert riza is NULL\n");
		/*printf("n is %d\n",n);
		for(i=0; i<n; i++){
			child=(newlesson)riza->child[i];
			printf("id[%d] is %d\n",i, riza->ids[i]);
		}
		n++;*/
	}
	if(!strncmp(instruction, "lookup", 6)){
		scanf("%d",&studid1);
		find(&root, studid1);
	}
	if(!strncmp(instruction, "rlookup", 7)){
		scanf("%d %d",&studid1,&studid2);
		rlookup(root, studid1, studid2);
		//printf("studid1 is %d and studid2 is %d\n",studid1,studid2);
	}
	if(!strncmp(instruction, "load", 4)){
		scanf("%s",inputfile);
		load(inputfile, &root);
	for(i=0; i<(root->numofids); i++)
		printf("root ids[%d] is %d\n",i, root->ids[i]);
	}
	if(!strncmp(instruction, "delete", 6)){
		scanf("%d %s",&studid1,courseid);
		delete(&root, studid1, courseid);
	}
	if(!strncmp(instruction, "sdelete", 7)){
		scanf("%d",&studid1);
		sdelete(&root, studid1);
	}	
	if(!strncmp(instruction, "check_find", 10)){
		check_find();
	}

	
}

return 0;
}
