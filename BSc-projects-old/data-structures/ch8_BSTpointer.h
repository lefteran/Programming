/************************************************************************
Header File	        : ch8_BSTpointer.h
Syggrafeas			: Y. Cotronis
Skopos				: Diepafh DDA (me deiktes)
*************************************************************************/
#ifndef __BSTPOINTER__ 
#define __BSTPOINTER__
#include "typos_stoixeiou.h"

/*orismos typou DDA */
typedef struct typos_komvou *typos_deikti; 

/*δηλώσεις συναρτήσεων*/

typos_deikti dimiourgia_dendro();
void katastrofh_dendro(typos_deikti * riza);

int	keno_dendro(typos_deikti riza);

/* Prosvash se komvous*/
typos_deikti aristero_paidi(typos_deikti p);
typos_deikti dexi_paidi(typos_deikti p);

/* diadromes */
void episkepsi(typos_deikti riza);
void endodiataksi(typos_deikti riza);
void prodiataksi(typos_deikti riza);
void metadiataksi(typos_deikti riza);

/* Diaxeirish DDA */
void eisagogi_komvou(typos_deikti *riza, typos_stoixeiou stoixeio);
void diagrafi_komvou(typos_deikti *riza, typos_stoixeiou stoixeio);

/* Anazhthsh komvwn kai periexomenvn tous*/
typos_deikti anazitisi_komvou(typos_deikti riza, typos_stoixeiou stoixeio);
typos_stoixeiou periexomena(typos_deikti riza); 
typos_stoixeiou * periexomenaPtr(typos_deikti riza);

#endif /*#ifndef __BSTPOINTER__ */
