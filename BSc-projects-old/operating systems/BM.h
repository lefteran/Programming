#ifndef __BM__ 
#define __BM__
#include <stdio.h>
#include <stdlib.h>
#include "typos_stoixeiou.h"
#include "ch8_BSTpointer.h"

/*orismos typou BM */
typedef struct RecBM *typosBM; 

/* epikefalides praxewn */

typos_deikti periexomenaBM(typosBM *);
typosBM dhmiourgia_BM();
typos_deikti dimiourgia_DDAriza(typosBM *vathmologia);
void katastrofh_BM(typosBM *);
void eisagogi_varon(typosBM *, float , float , float );
void dhlosh_mathimatos(typosBM *, typos_stoixeiou *);
int eisagogi_vathmologias(int , float , typos_stoixeiou , typosBM *);
void ypologismos_telikon_vathmon(typosBM *,typos_deikti *);
void ypologismos_mesou_orou_takshs(typos_deikti, int *, int *);
void prospelash(typos_deikti , FILE *, int);
int lista_epityxonton(typosBM);
void prospelash2(typos_deikti , FILE *);
int analytikh_lista_vathmologias(typosBM);
typos_deikti dentro(typosBM *);
typos_deikti * dentroPtr(typosBM vathmologia);
void ektyposh(typos_deikti riza, FILE *fp, int i);

#endif
