#include <stdio.h>
#include <stdlib.h>
#include "stackoa.h"

void FillSorted(typos_stoivas Input, int * overflow, char ch);
void StackMerge(typos_stoivas In, typos_stoivas Out, int * overflow);
void EmptySorted(typos_stoivas Output);
void Move(typos_stoivas From, typos_stoivas To, int *overflowTo, char ch);

int main()
{   typos_stoivas sorted=dimiourgia(),    
		allsorted=dimiourgia();
                  
    int overflow1, overflow2, overflow3;
    
    FillSorted(sorted, &overflow1, 'a');
    if (overflow1)
       printf("H Stoiva sorted den xorese ola ta stoixeia\n");
    /* EmptySorted(Sorted1); Energopoihste edw mono gia dokimh. Grafei kai Adeiazei! Stoiva */
    
    FillSorted(sorted, &overflow2, 'd');
    if (overflow2)
       printf("H Stoiva sorted den xorese ola ta stoixeia\n");
    /* EmptySorted(Sorted2); Energopoihste edw mono gia dokimh. Grafei kai Adeiazei! Stoiva */
  
    StackMerge(sorted, allsorted, &overflow3);
    if (overflow3)
       printf("H Telikh Stoiva allsorted den xorese ola ta stoixeia\n");

    EmptySorted(allsorted);
    
  return 0;
}

void FillSorted(typos_stoivas Input, int * overflow, char ch)
{    int HowMany, Next, i;
     printf("Posa stoixeia exei h seira;");
     scanf("%d", &HowMany);

     printf("Dwse ta %d stoixeia (me kena anamesa):", HowMany);
     for (i=1; i<= HowMany; i++)
     {   scanf("%d", &Next);
         othisi(Input, Next, overflow, ch);
         if (*overflow)
            printf("Stoiva Full %d not in stoiva\n", Next);
         else 
			printf("othisi stoixeiou %d\n", Next);
     }
     printf("\n");
     getchar();
}

void Move(typos_stoivas From, typos_stoivas To, int *overflowTo, char ch)
{    int underflow, Next;

     while (!keni(From, ch))
     {     exagogi(From, &Next, &underflow, ch);
           othisi(To, Next, overflowTo, 'a');
           if (*overflowTo)
                 printf("Move: to stoixeio %d den eishxthei sthn telikh\n", Next);
     }
}
     
void StackMerge(typos_stoivas In, typos_stoivas Out, int *overflow)
{ 
     int Max1, Max2, underflow1, underflow2, overflow1, overflow2;
     *overflow=0;
     //printf("in Merge, In1=%d, In2=%d \n", keni(In1), keni(In2));
     while (!keni(In, 'a') && !keni(In, 'd'))
     {     //printf("in loop\n");
		paratirisi(In, &Max1, 'a');
		paratirisi(In, &Max2, 'd');
           
           //printf("Max1=%d Max2=%d \n", Max1, Max2);
           if (Max1 < Max2)
           {  exagogi(In, &Max2, &underflow2, 'd');
              othisi(Out, Max2, overflow, 'a');
              if (*overflow)
                 printf("Merge: to stoixeio %d ths 2hs seiras den eishxthei sthn telikh\n", Max2);
           }
           else
           {  exagogi(In, &Max1, &underflow1, 'a');
              othisi(Out, Max1, overflow, 'a');  // eisafvgh mefalyteroy sthn Out
              if (*overflow)
                 printf("Merge: to stoixeio %d ths 1hs seiras den eishxthei sthn telikh\n", Max1);
           }
     } /* while */
     //printf("out of loop\n");
     if (keni(In, 'a'))
     {     //printf("In1 is keni\n");
           Move(In, Out, overflow, 'd');
     }
     else
     {     //printf("In2 is keni\n");
           Move(In, Out, overflow, 'a');
     }
}

void EmptySorted(typos_stoivas Output)
{/* Prosoxh: Adiazei olh thn stoiva */
     int Next, underflow;
     printf("H seira einai(antistrofa):");
     while (!keni(Output, 'a'))
     {     exagogi(Output, &Next, &underflow, 'a');
           printf("%d ", Next);
     }
     printf("\n");
}
