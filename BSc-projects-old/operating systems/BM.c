#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "BM.h"		
#include "ch8_BSTpointer.h"	


typedef struct RecBM{
     typos_deikti DDAriza; /* to BM apoteleitai apo to DDA (thn riza tou)*/
     float Barh[3];       /* kai ta 4 barh: 3 askhsevn kai graptou */
} RecBM;

/* O orismos tvn synarthsewn me thn xrhsh praxewn ths diepafhs ch8_BSTpointer.h */

typos_deikti periexomenaBM(typosBM *vathmologia){
	return (*vathmologia)->DDAriza;
}

typosBM dhmiourgia_BM(){

int i;
typosBM vathmologia;
vathmologia=malloc(sizeof(RecBM));			//Desmefsh mnhmhs
vathmologia->DDAriza=dimiourgia_dendro();
for(i=0; i<3; i++)
	vathmologia->Barh[i]=0;

return vathmologia;
}

void katastrofh_BM(typosBM *BMptr){
	katastrofh_dendro(&(*BMptr)->DDAriza);
	free(*BMptr);
}

typos_deikti dimiourgia_DDAriza(typosBM *vathmologia){

typos_deikti deiktis;
deiktis=(*vathmologia)->DDAriza;
deiktis=dimiourgia_dendro();
return deiktis;
}


void eisagogi_varon(typosBM *BMptr, float a, float b, float c){

(*BMptr)->Barh[0]=a;
(*BMptr)->Barh[1]=b;
(*BMptr)->Barh[2]=c;
}




void dhlosh_mathimatos(typosBM *BMptr, typos_stoixeiou *stoixeia){
float init=0.0;
//(*BMptr)->DDAriza=NULL;
eisagogi_komvou(&(*BMptr)->DDAriza,*stoixeia);
set_grapto(stoixeia,init);
set_ask1(stoixeia,init);
set_ask2(stoixeia,init);
set_ask3(stoixeia,init);
}



int eisagogi_vathmologias(int i, float vathmos, typos_stoixeiou stoixeia, typosBM *vathmologia){		//i=0 gia grapto 1 gia askisi1 ...
typos_deikti deiktis;
typos_stoixeiou *dedomena;

dedomena=(typos_stoixeiou *)dimiourgia_stoixeiou();

if(vathmos>10.0 || vathmos<0.0){
	printf("O vathmos pou dosate de vrisketai sto diasthma [0,10]\n");
	return WRONG;
}

deiktis=anazitisi_komvou((*vathmologia)->DDAriza, stoixeia);
if(deiktis==NULL){
	printf("De vrethike foithths sto dentro me am %d\n",get_am(stoixeia));
	return WRONG;
}

dedomena=periexomenaPtr(deiktis);

if(i==0)
	set_grapto(dedomena,vathmos);
else if(i==1)
	set_ask1(dedomena,vathmos);
else if(i==2)
	set_ask2(dedomena,vathmos);
else if(i==3)
	set_ask3(dedomena,vathmos);
return OK;

}



void ypologismos_telikon_vathmon(typosBM *vathmologia,typos_deikti *deiktis){

int i,vathmos;
float weig[3];
typos_stoixeiou *dedomena;
typos_deikti *deiktis1,*deiktis2;

dedomena=(typos_stoixeiou *)dimiourgia_stoixeiou();
for(i=0; i<3; i++)
	weig[i]=(*vathmologia)->Barh[i];
	
dedomena=periexomenaPtr(*deiktis);
//printf("am is %d\n",get_am(*dedomena));
vathmos=(int)(weig[0] * get_grapto(*dedomena) + weig[1] * get_ask1(*dedomena) + weig[2] * get_ask2(*dedomena) + weig[2] * get_ask3(*dedomena));
//printf("Vathmos is %d\n",vathmos);
set_tel(dedomena,vathmos);
deiktis1=(typos_deikti *)aristero_paidi(*deiktis);
deiktis2=(typos_deikti *)dexi_paidi(*deiktis);
if(!keno_dendro(aristero_paidi(*deiktis)))
	ypologismos_telikon_vathmon(vathmologia,&deiktis1);
if(!keno_dendro(dexi_paidi(*deiktis)))
	ypologismos_telikon_vathmon(vathmologia,&deiktis2);
}


void ypologismos_mesou_orou_takshs(typos_deikti riza, int *grade_sum, int *stud_sum){
typos_stoixeiou dedomena;

if(!keno_dendro(riza)){
	dedomena=dimiourgia_stoixeiou();
	dedomena=periexomena(riza);
	*grade_sum+=get_tel(dedomena);
	*stud_sum+=1;

ypologismos_mesou_orou_takshs(aristero_paidi(riza), grade_sum, stud_sum);
ypologismos_mesou_orou_takshs(dexi_paidi(riza), grade_sum, stud_sum);
}
}




/*void ypologismos_mesou_orou_takshs(typos_deikti riza, int grade_sum, int stud_sum){
float mesos_oros;
typos_stoixeiou dedomena;

dedomena=dimiourgia_stoixeiou();
dedomena=periexomena(riza);
grade_sum+=get_tel(dedomena);
stud_sum+=1;
if(keno_dendro(aristero_paidi(riza)) && keno_dendro(dexi_paidi(riza))){
	mesos_oros=(float)grade_sum/ (float)stud_sum;
	printf("O mesos oros einai %f\n",mesos_oros);
	//katastrofh_stoixeiou(dedomena);
	return;
}
if(!keno_dendro(aristero_paidi(riza)))
	ypologismos_mesou_orou_takshs(aristero_paidi(riza), grade_sum,stud_sum);
if(!keno_dendro(dexi_paidi(riza)))
	ypologismos_mesou_orou_takshs(dexi_paidi(riza), grade_sum,stud_sum);

//katastrofh_stoixeiou(dedomena);
}*/


void ektyposh(typos_deikti riza, FILE *fp, int i){

typos_stoixeiou dedomena;
if(riza!=NULL){
	dedomena=dimiourgia_stoixeiou();
	dedomena=periexomena(riza);
	if(i==1){
		if(get_tel(dedomena)>=5)
			fprintf(fp,"%d\t\t\t\t%d\n",get_am(dedomena),get_tel(dedomena));
	}
	if(i==2)
		fprintf(fp,"%d \t\t %.1f \t\t %.1f \t\t %.1f \t\t %.1f \t\t %d\n",
		get_am(dedomena),get_grapto(dedomena),get_ask1(dedomena),get_ask2(dedomena),get_ask3(dedomena),get_tel(dedomena));
}
}

void prospelash(typos_deikti riza, FILE *fp, int i){

if(!keno_dendro(riza)){
	prospelash(aristero_paidi(riza),fp,i);
	ektyposh(riza,fp,i);
	prospelash(dexi_paidi(riza),fp,i);
}
}


int lista_epityxonton(typosBM vathmologia){

FILE *fp;
char arithmos[]="Arithmos mhtroou";
char vathmos[]="Telikos vathmos";

if((fp=fopen("epityxontes.txt","w"))==NULL)				
	return	WRONG;
	

fprintf(fp,"%s\t\t%s\n",arithmos,vathmos);
prospelash(vathmologia->DDAriza,fp,1);
fclose(fp);
return OK;

}



int analytikh_lista_vathmologias(typosBM vathmologia){

FILE *fp;
char arithmos[]="Arithmos mhtroou";
char grapto[]="Grapto";
char askisi1[]="Askisi1";
char askisi2[]="Askisi2";
char askisi3[]="Askisi3";
char telikos[]="Telikos vathmos";

if((fp=fopen("analytikh_vathmologia.txt","w"))==NULL)				
	return	WRONG;
	

fprintf(fp,"%s \t %s \t %s \t %s \t %s \t %s\n",arithmos,grapto,askisi1,askisi2,askisi3,telikos);
prospelash(vathmologia->DDAriza,fp,2);
fclose(fp);
return OK;

}



typos_deikti dentro(typosBM *vathmologia){
	return (*vathmologia)->DDAriza;
}


typos_deikti * dentroPtr(typosBM vathmologia)
{
     return &(vathmologia->DDAriza);
};

