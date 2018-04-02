#include <stdio.h>
#include <stdlib.h>
#include "stackma.h"

void dimiourgia(typos_stoivas *stoivaPtr){
	stoivaPtr->dkorifi = -1;
	stoivaPtr->akorifi = -1;	
}

int  keni(typos_stoivas stoivaPtr, char ch){
	if(ch=='a')		//an dothei h parametros gia thn aristerh stoiva
		return (stoivaPtr.akorifi == -1);
	else
		return (stoivaPtr.dkorifi == -1);
}

int  gemati(typos_stoivas stoiva){
	return ((stoiva.akorifi + stoiva.dkorifi) == PLITHOS-1);
}

void exagogi(typos_stoivas *stoivaPtr,typos_stoixeioy *stoixeioPtr, int *ypoxeilisi,char ch){
	if (keni(*stoivaPtr,ch))
		*ypoxeilisi = 1;
	else{
		*ypoxeilisi = 0;
		if(ch=='a'){
			*stoixeioPtr = stoivaPtr->pinakas[stoivaPtr->akorifi];
			stoivaPtr->akorifi--;
		}
		else{
			*stoixeioPtr = stoivaPtr->pinakas[PLITHOS-(stoivaPtr->dkorifi)-1];	////////////
			stoivaPtr->dkorifi--;		
		}
	}
}

void othisi(typos_stoivas *stoivaPtr, typos_stoixeioy stoixeio, int *yperxeilisi, char ch){
	if (gemati(*stoivaPtr)) 
		*yperxeilisi = 1;
	else
	{
		*yperxeilisi = 0;
		if(ch=='a'){
			stoivaPtr->akorifi++;
			stoivaPtr->pinakas[stoivaPtr->akorifi]=stoixeio;
		}
		else{
			stoivaPtr->dkorifi++;
			stoivaPtr->pinakas[PLITHOS-(stoivaPtr->dkorifi)-1]=stoixeio;	////////////
		}
	}
}




void paratirisi(typos_stoivas stoiva, typos_stoixeioy *stoixeio, char ch){
	if(ch=='a')
		*stoixeio=stoiva.pinakas[stoiva.akorifi];
	else
		*stoixeio=stoiva.pinakas[PLITHOS-(stoiva.dkorifi)-1];		////////////
}




