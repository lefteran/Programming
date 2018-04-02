#ifndef __CH4_STACK__ 
#define __CH4_STACK__

#define PLITHOS 10


typedef int typos_stoixeioy;
typedef struct {
	int dkorifi;
	int akorifi;
	typos_stoixeioy pinakas[PLITHOS];
}typos_stoivas;


void dimiourgia(typos_stoivas *stoivaPtr);
int  keni(typos_stoivas stoiva,char ch);
int  gemati(typos_stoivas stoiva);
void exagogi(typos_stoivas *stoivaPtr, typos_stoixeioy *stoixeio, int *ypoxeilisi,char ch);
void othisi(typos_stoivas *stoivaPtr, typos_stoixeioy stoixeio, int *yperxeilisi,char ch);
void paratirisi(typos_stoivas stoiva, typos_stoixeioy *stoixeio, char ch);

#endif /*#ifndef __CH4_STACK__ */
