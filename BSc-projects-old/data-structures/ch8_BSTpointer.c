/************************************************************************
Implemantation File	: ch8_BSTpointer.c
Syggrafeas			: Y. Cotronis
Skopos				: Ylopoihsh praxewn DDA me deiktes
*************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include "ch8_BSTpointer.h"

typedef struct typos_komvou{
	typos_stoixeiou dedomena;
	typos_deikti apaidi,dpaidi;
} typos_komvou;

typos_deikti dimiourgia_dendro( )
{/*	
 *	Pro : 		kamia
 *	Meta: 		Dhmiourgia (kenou) DDA
*/	
	return NULL;
}

void katastrofh_dendro(typos_deikti * riza)
{/* metadiataxi aparaithth */
    typos_deikti L, R;
     if (!keno_dendro(*riza))
	 {  L=aristero_paidi(*riza);
        katastrofh_dendro(&L);
        R=dexi_paidi(*riza);
		katastrofh_dendro(&R);
		/* printf("freeing %p\n", *riza); */
		free(*riza);
		*riza = NULL;
	}
}

int	keno_dendro(typos_deikti riza)
{/*	
 *	Pro : 		Dhmiourgia DDA
 *	Meta:		epistrefei 1 an to DDA einai keno, alliws 0
*/
	return (riza == NULL);
}

typos_deikti aristero_paidi(typos_deikti p)
{/*	
 *	Pro : 		o p deixnei se komvo tou DDA
 *	Meta:		epistrefei deikth sto aristero paidi toy komvou pou deixnei o p
*/
	if (!keno_dendro(p))
		return (p->apaidi);
	else
		return NULL;
}

typos_deikti dexi_paidi(typos_deikti p)
{/*	
 *	Pro : 		o p deixnei se komvo tou DDA
 *	Meta:		epistrefei deikth sto dexio paidi toy komvou pou deixnei o p
*/
	if (!keno_dendro(p))
		return (p->dpaidi);
	else
		return NULL;
}

void episkepsi(typos_deikti riza)
{/*	
 *	Pro : 		Dhmiourgia DDA
 *	Meta:		Typwnei ta dedomena tou komvou pou deixnei h riza 
                opws orizei h Print (orizetai sto typo_stoixeiou.h+.c)
*/
	if (riza!=NULL)
       Print(riza->dedomena);
}

void endodiataksi(typos_deikti riza)
{/*	
 *	Pro: 		Dhmiourgia DDA
 *	Meta:		Diatrexei me endodiatetagmenh diadromh to DDA
*/	
	if (!keno_dendro(riza))
	{  
		endodiataksi(aristero_paidi(riza));      /* episkepsi aristerou ypodendrou*/
		episkepsi(riza);	                        /* episkepsi rizas*/
		endodiataksi(dexi_paidi(riza));          /*episkepsi dexiou ypodendrou*/
	}
}

void prodiataksi(typos_deikti riza)
{/*	
 *	Pro: 		Dhmiourgia DDA
 *	Meta:		Diatrexei me prodiatetagmenh diadromh to DDA
*/
	if (!keno_dendro(riza))
	{
		episkepsi(riza);
		prodiataksi(aristero_paidi(riza));
		prodiataksi(dexi_paidi(riza));
	}
}

void metadiataksi(typos_deikti riza)
{/*	
 *	Pro: 		Dhmiourgia DDA
 *	Meta:		Diatrexei me metadiatetagmenh diadromh to DDA
*/
	if (!keno_dendro(riza))
	{
		metadiataksi(aristero_paidi(riza));
		metadiataksi(dexi_paidi(riza));
		episkepsi(riza);
	}
}

void eisagogi_komvou(typos_deikti *riza, typos_stoixeiou stoixeio)
{/*	
 *	Pro: 		Dhmiourgia DDA
 *	Meta:		Eisagei neo komvo sto dendro efoson den yparxei komvos me ta idia dedomena
*/
	if (keno_dendro(*riza)) /* brhkame thn 9esh eisagvghs*/
	{
		*riza = malloc(sizeof(struct typos_komvou));
		/* printf("dhmiourgia %p\n", *riza); */
		(*riza)->dedomena = stoixeio;
		(*riza)->apaidi = NULL;
		(*riza)->dpaidi = NULL;
	}
	else if (mikrotero(stoixeio,(*riza)->dedomena))	/* eisagvgh sto aristero ypodendro */
		eisagogi_komvou(&(*riza)->apaidi, stoixeio);
	else if (megalytero(stoixeio,(*riza)->dedomena))/*eisagvgh sto dexio ypodendro*/
		eisagogi_komvou(&(*riza)->dpaidi,stoixeio);
	else  /* ta periexomena tou komvou hdh yparxoun */
	{     printf("to stoixeio ");
	      Print(stoixeio);
	      printf(" einai hdh sto dendro\n");
    }
}

void diagrafi_komvou(typos_deikti *riza, typos_stoixeiou stoixeio)
{/*	
 *	Pro : 		Dhmiourgia DDA
 *	Meta:		Exei diagrfei o kombos me periexomeno stoixeio
*/
	typos_deikti prosorinos,xepomenos;

	if (!keno_dendro(*riza))
	{
		if (iso(stoixeio,(*riza)->dedomena)) /* 9a ginei diagrafh */
		{
			if (keno_dendro((*riza)->apaidi))/* den exei aristero paidi */
			{
				prosorinos = *riza;
				*riza = (*riza)->dpaidi;
				/* printf("diagrafi free %p\n", prosorinos); */
				free(prosorinos);
			}
			else if (keno_dendro((*riza)->dpaidi))/* den exei dexi paidi */
			{
				prosorinos = *riza;
				*riza = (*riza)->apaidi;				
                /* printf("diagrafi free %p\n", prosorinos); */
				free(prosorinos);
			}
			else /* o komvos exei dyo paidia */
			{
				/* eyresh tou amesvs epomenou */
				xepomenos = (*riza)->dpaidi;
				while (xepomenos->apaidi !=NULL)
					xepomenos = xepomenos->apaidi;
				/* ta periexomena tou xepomenos antigrafontai sthn riza */
				(*riza)->dedomena = xepomenos->dedomena;
				diagrafi_komvou(&((*riza)->dpaidi),(*riza)->dedomena);
			}
		}
		else if (mikrotero(stoixeio,(*riza)->dedomena)) /* anazhthsh sto aristero */
			diagrafi_komvou(&((*riza)->apaidi),stoixeio);
		else                                             /* anazhthsh sto dexi */
			diagrafi_komvou(&((*riza)->dpaidi),stoixeio);
	}
}

typos_deikti anazitisi_komvou(typos_deikti riza, typos_stoixeiou stoixeio)
{/*	
 *	Pro : 		Dhmiourgia DDA
 *	Meta:		Eistrefei deikti ston komvo pou brisketai sto stoixeio diaforetika NULL
*/
	if (keno_dendro(riza)) /* den bre9hke */
		return NULL;
	if (iso(riza->dedomena,stoixeio))
		return riza;
	if (mikrotero(stoixeio,riza->dedomena))	/* anazhthsh sto aristero */
		return anazitisi_komvou(aristero_paidi(riza),stoixeio);
	else	                                /* anazhthsh sto dexi */
		return anazitisi_komvou(dexi_paidi(riza),stoixeio);
}

typos_stoixeiou periexomena(typos_deikti riza)
{/*	
 *	Pro : 		Dhmiourgia DDA
 *	Meta:		Epistrefei ta dedomena tou komvou pou deixnei h riza 
                prosoxh: h riza !=NULL
*/
  return riza->dedomena;
};

typos_stoixeiou * periexomenaPtr(typos_deikti riza)
{/*	
 *	Pro: 		Dhmiourgia DDA
 *	Meta:		Epistrefei Deikti sta dedomena tou komvou pou deixnei h riza 
                prosoxh: An riza ==NULL epistrefei NULL
*/
  if (riza != NULL)
     return &(riza->dedomena);
  else return NULL;
};
