#ifndef __2_4tree__ 
#define __2_4tree__ 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct lesson *newlesson;
typedef struct node *newnode;
int levels;
int empty;

typedef struct lesson{
	int grade;
	char *courseid;
	char *coursename;
	newlesson next;
}lesson;


typedef struct node{
	int numofids;
	int isleaf;
	int *ids;
	newnode next;
	void **child;
}node;

newnode root;

void create_lesson(newlesson *mathima, char *courseid, char *coursename, int grade);
void remove_lesson(newlesson mathima);
void create_node(newnode *komvos);
void remove_node(newnode *komvos);
newnode initialize();
void insert(newnode *komvos, int studid, char *courseid, char *coursename, int grade);
int set_node_info(newnode *komvos, int studid, int *index);
void find(newnode *komvos, int studid);
void insert_lesson(newlesson *mathima, char *courseid, char *coursename, int grade);
newnode * find_leaf(newnode *komvos, int studid, int *found, int *index);
void print_ids();

#endif    /*__2-4tree__ */





