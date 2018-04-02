#include "BM.h"
#include "ch8_BSTpointer.h"


int main(){


typosBM vathmologia;
typos_deikti riza;
FILE *fp[5];
int am,i,j=0,init1=0,init2=0;
char ch,eponymo[50],onoma[30],patronymo[3];
float vathmos;
typos_stoixeiou student[760],mathitis;

vathmologia=dhmiourgia_BM();
for(i=0;i<760;i++)
	student[i]=dimiourgia_stoixeiou();
mathitis=dimiourgia_stoixeiou();
//dimiourgia_DDAriza(&vathmologia);			//xreiazetai sigoura;
riza=dentro(&vathmologia);
//riza=dimiourgia_dendro();


if((fp[0]=fopen("BathmoiGrapta.txt","r"))==NULL)				
	printf("Cannot open file BathmoiGrapta.txt\n");
if((fp[1]=fopen("BathmoiAskhsh1.txt","r"))==NULL)				
	printf("Cannot open file BathmoiAskhsh1.txt\n");
if((fp[2]=fopen("BathmoiAskhsh2.txt","r"))==NULL)				
	printf("Cannot open file BathmoiAskhsh2.txt\n");
if((fp[3]=fopen("BathmoiAskhsh3.txt","r"))==NULL)				
	printf("Cannot open file BathmoiAskhsh3.txt\n");
if((fp[4]=fopen("Dhlwseis.txt","r"))==NULL)				
	printf("Cannot open file Dhlwseis.txt\n");


while((ch=fgetc(fp[4]))!='\n')			//Afhnoume thn proth grammh tou arxeiou
	continue;
	i=0;
while(!feof(fp[4])){
	fscanf(fp[4],"%d %s %s %s\n ",&am,eponymo,onoma,patronymo);						//tha grafontai kai ta kena
	//printf("%d %s %s %s\n",am,eponymo,onoma,patronymo);
	set_am(&student[i],am);
	set_surname(&student[i],eponymo);
	set_name(&student[i],onoma);
	set_fathname(&student[i],patronymo);
	//printf("%d\n",get_am(student[i]));
	//printf("%s\n",get_eponymo(student[i]));
	//printf("%s\n",get_onoma(student[i]));
	//printf("%s\n",get_patronymo(student[i]));
	//eisagogi_komvou(&riza,student[i]);
	dhlosh_mathimatos(&vathmologia,&student[i]);
	i++;
}
eisagogi_varon(&vathmologia, 0.6, 0.1, 0.15);
//riza=dentro(&vathmologia);
//printf("Is the tree empty: %d\n",keno_dendro(riza));
//endodiataksi(riza);

for(i=0; i<4; i++)
	while((ch=fgetc(fp[i]))!='\n')		//Afhnoume thn proth grammh ton arxeion
		continue;
for(i=0; i<4; i++){
	while(!feof(fp[i])){
		fscanf(fp[i],"%d %f\n ",&am,&vathmos);
		//printf("am is %d and vathmos is %f\n",am,vathmos);
		set_am(&mathitis,am);
		//printf("The am of mathitis is: %d\n",get_am(mathitis));
		j=eisagogi_vathmologias(i, vathmos, mathitis, &vathmologia);			//den anaferoume poio student[i] prepei na ton vroume meso tou am
		//printf("j is %d\n",j);
	}
	//printf("\n");
}
//riza=dentro(&vathmologia);
//printf("Is the tree empty: %d\n",keno_dendro(riza));
//endodiataksi(riza);


ypologismos_telikon_vathmon(&vathmologia,dentroPtr(vathmologia));		//prepei na epistrefei &riza
//riza=dentro(&vathmologia);
//printf("Is the tree empty: %d\n",keno_dendro(riza));
//endodiataksi(riza);

ypologismos_mesou_orou_takshs(dentro(&vathmologia),&init1,&init2);
printf("O mesos oros takshs einai: %f\n",(float)init1/(float)init2);
if(lista_epityxonton(vathmologia))
	printf("Den egine h lista epityxonton\n");
//riza=dentro(&vathmologia);
//printf("Is the tree empty: %d\n",keno_dendro(riza));
//endodiataksi(riza);

if(analytikh_lista_vathmologias(vathmologia))
	printf("Den egine h analytikh lista vathmologias\n");

katastrofh_BM(&vathmologia);

for(i=0; i<4; i++)
	fclose(fp[i]);

for(i=0;i<760;i++)
	free(student[i]);

return 0;
}

