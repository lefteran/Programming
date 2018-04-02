#include <stdio.h>
#include <stdlib.h>
#include "stackoa.h"

#define PLITHOS 20 
typedef struct StackStruct {
	int akorifi;
	int dkorifi;
	typos_stoixeioy pinakas[PLITHOS];
}StackStruct;

typos_stoivas dimiourgia()
{
    typos_stoivas ThisStoiva=(typos_stoivas)malloc(sizeof(StackStruct));
	ThisStoiva->akorifi = -1;
	ThisStoiva->dkorifi = -1;
	return ThisStoiva;
}

int  keni(const typos_stoivas stoiva, char ch)
{
	if(ch=='a')		//an dothei h parametros gia thn aristerh stoiva
		return (stoiva->akorifi == -1);
	else
		return (stoiva->dkorifi == -1);

}

int gemati(const typos_stoivas stoiva)
{
	return ((stoiva->akorifi + stoiva->dkorifi) == PLITHOS-1);
}

void exagogi(const typos_stoivas stoiva, typos_stoixeioy * const stoixeioPtr, int *ypoxeilisi, char ch)
{
	if (keni(stoiva, ch))
		*ypoxeilisi = 1;
	else
	{ 
		*ypoxeilisi = 0;
		if(ch=='a'){		
			*stoixeioPtr = stoiva->pinakas[stoiva->akorifi];
			stoiva->akorifi--;
		}
		else{
			*stoixeioPtr = stoiva->pinakas[stoiva->dkorifi];
			stoiva->dkorifi--;
		}
	}



}

void othisi(typos_stoivas stoiva,typos_stoixeioy stoixeio, int *yperxeilisi, char ch)
{
	if (gemati(stoiva))
		*yperxeilisi = 1;
	else
	{
		*yperxeilisi = 0;
		if(ch=='a'){
			stoiva->akorifi++;
		    stoiva->pinakas[stoiva->akorifi]=stoixeio;
		}
		else{
			stoiva->dkorifi++;
			stoiva->pinakas[PLITHOS-(stoiva->dkorifi)-1]=stoixeio;
		}

	}

}



void paratirisi(const typos_stoivas stoiva, typos_stoixeioy *stoixeio, char ch){
	if(ch=='a')
		*stoixeio=stoiva->pinakas[stoiva->akorifi];
	else
		*stoixeio=stoiva->pinakas[PLITHOS-(stoiva->dkorifi)-1];
}



