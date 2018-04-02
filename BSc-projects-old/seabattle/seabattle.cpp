#include "seabattle.h"

ship::ship(){
	hitpoints=0;	
	threat=false;			
}


int *ship::get_fore(){
	int coor[2],*p;
	coor[0]=forex;
	coor[1]=forey;
	p=coor;
	return p;
}

void ship::score_update(int newscore){
	score+=newscore;
}


tug::tug(){
	length=1;
}

void tug::hit(){
	cout<<"entered hit in tug"<<endl;
	hitpoints++;
	sink();
}

void tug::sink(){
	int n;
	cout<<"You just got a small boat!"<<endl;
	n=1;
	score_update(n);
	tugs--;		//gia kathe tug pou vythizetai meiono kata 1 th global metavlhth gia na gnorizo posa tugs exoun vythistei otan exoume game over
	ships--;
}


int *tug::threaten(){			

int coor[2],*p;
long curtime;
curtime = time(NULL);
srand((unsigned int) curtime); /* Seed for random number generator */
coor[0]=rand()%gridsize;
coor[1]=rand()%gridsize;
p=coor;
return p;
}

submarine::submarine(){
	length=3;
}

void submarine::hit(){
	cout<<"entered hit in submarine"<<endl;
	hitpoints++;
	if(hitpoints==length)
		sink();
}

int *submarine::threaten(){

int coor[2],*p;
long curtime;	
curtime = time(NULL);
srand((unsigned int) curtime); /* Seed for random number generator */
coor[0]=rand()%gridsize;
coor[1]=rand()%gridsize;
p=coor;
return p;
}

void submarine::sink(){
	int n;
	cout<<"You just got a submarine!"<<endl;
	n=5;
	score_update(n);
	submarines--;
	ships--;
}

aircraft_carrier::aircraft_carrier(){
	length=5;
}

void aircraft_carrier::hit(){
	cout<<"entered hit in aircraft_carrier"<<endl;
	hitpoints++;
	if(hitpoints==length)
		sink();
}

int *aircraft_carrier::threaten(){
	
}

void aircraft_carrier::sink(){
	int n;
	cout<<"You just got a large ship!"<<endl;
	n=3;
	score_update(n);
	aircraft_carriers--;
	ships--;
}

gridpoint::gridpoint(){
	ploio=new ship;
	chosen=false;	
	is_over=false;
	hit=false;
}

void gridpoint::hitpoint(){
	if(is_over)
		hit=true; 
	else
		chosen=true;
	ploio->hit();
}

gridpoint::~gridpoint(){
	delete ploio;
}

grid::grid(int n){
	size=n;
	plegma=new gridpoint*[n];
	for(int i=0; i<size; i++)
		plegma[i]=new gridpoint[n];
}

bool grid::insert(ship neoploio, int x, int y){			
	bool found=false;					//h found ginetai true an vrethike katallhlh mia apo tis 4 pithanes theseis (pano,kato,aristera,deksia)
	bool full=false;					//h full ginetai true an vrethei ena shmeio apo afta pou elegxontai sto opoio yparxei ploio pano
										//h exei epilegei prohgoumenos apo to xrhsth
	int xi,yi,l,d;
	l=neoploio.get_length();
		xi=x;
		yi=y;
		if(l==1){			//arxika elegxoume gia to rymoulko
			if(!plegma[xi][yi].get_over() && (!plegma[xi][yi].is_chosen())){		//an de vrisketai ploio sto shmeio afto kai den exei epilegei
												//prohgoumenos to shmeio afto apo to xrhsth   
				plegma[xi][yi].set_ship(neoploio);		//to shmeio afto deixnei sto neoploio
				plegma[xi][yi].set_over();				//to shmeio exei ploio pano tou
				neoploio.set_fore(xi,yi);				//thetoume thn plorh se afto to shmeio
				found=true;								//vrethike katallhlh thesh
			}
		}
		else{				//kai meta elegxoume gia to ypovryxio kai to aeroplanoforo
			if(xi>=l-1 && (!found)){			//arxika elegxumeo to (xi,yi) an einai egkyro gia thn plorh kai ta 3 h 5 shmeia tou pou vriskontai 
												//aristera tou. Gia na mhn pesoume ekso apo ton pinaka elegxoume an xi>=l-1. An ena apo afta 
												//den einai katallhlo tote h full ginetai true
				for(int i=0; i<l; i++)			
					if(plegma[xi-i][yi].get_over() || plegma[xi-i][yi].is_chosen())
						full=true;
				if(!full){				//synexizoume opos parapano me to tug thetontas to ploio se 3 h 5 shmeia
					d=3;
					neoploio.set_direction(d);
					neoploio.set_fore(xi,yi);
					for(int i=0; i<l; i++){
						plegma[xi-i][yi].set_ship(neoploio);	
						plegma[xi-i][yi].set_over();
					}
					found=true;
				}
			}
			full=false;
			if(xi<=size-l && (!found)){		//meta elegxoume to (xi,yi) an einai egkyro gia thn plorh kai ta 3 h 5 shmeia tou pou vriskontai 
											//deksia tou.
				for(int i=0; i<l; i++)
					if(plegma[xi+i][yi].get_over() || plegma[xi+i][yi].is_chosen())
						full=true;
				if(!full){
					d=1;
					neoploio.set_direction(d);
					neoploio.set_fore(xi,yi);
					for(int i=0; i<l; i++){
						plegma[xi+i][yi].set_ship(neoploio);
						plegma[xi+i][yi].set_over();
					}
					found=true;				
				}
			}
			full=false;
			if(yi>=l-1 && (!found)){	//meta elegxoume to (xi,yi) an einai egkyro gia thn plorh kai ta 3 h 5 shmeia tou pou vriskontai 
											//kato tou.
				for(int i=0; i<l; i++)
					if(plegma[xi][yi-i].get_over() || plegma[xi][yi-i].is_chosen())
						full=true;
				if(!full){
					d=2;
					neoploio.set_direction(d);
					neoploio.set_fore(xi,yi);
					for(int i=0; i<l; i++){
						plegma[xi][yi-i].set_ship(neoploio);
						plegma[xi][yi-i].set_over();
					}
					found=true;				
				}
			}
			full=false;
			if(yi<=size-l && (!found)){		//meta elegxoume to (xi,yi) an einai egkyro gia thn plorh kai ta 3 h 5 shmeia tou pou vriskontai 
											//pano tou.
				for(int i=0; i<l; i++)
					if(plegma[xi][yi+i].get_over() || plegma[xi][yi+i].is_chosen())
						full=true;
				if(!full){
					d=0;
					neoploio.set_direction(d);
					neoploio.set_fore(xi,yi);
					for(int i=0; i<l; i++){
						plegma[xi][yi+i].set_ship(neoploio);
						plegma[xi][yi+i].set_over();
					}
					found=true;				
				}
			}
		}
return found;
}

void grid::print(){

for(int i=0; i<size; i++){
	for(int j=0; j<size; j++){
		if(plegma[i][j].is_hit())
			cout<<"B ";
		else if(plegma[i][j].is_chosen())
			cout<<"F ";
		//else if(plegma[i][j].get_over())
		//	cout<<"X ";
		else
			cout<<". ";
	}
	cout<<endl;
}
cout<<"Score:"<<score<<endl;
}

void grid::update(int x, int y){
	int *p;
	bool inserted=false;
	ship ploio,oldship;	
		plegma[x][y].hitpoint();
	/*if(x>0 && plegma[x-1][y].get_over()){		//elegxoume to shmeio aristera
		p=plegma[x-1][y].threatenpoint();		//kai h threatenpoint kalei th threat h opoia epistrefei tyxaies syntetagmenes
		ploio=plegma[x-1][y].get_ship();
		oldship=plegma[x-1][y].get_ship();
		if(ploio.get_hitpoints()==0){			//sthn periptosh ypovryxiou h aeroplanoforou an den exoun xtypithei
			inserted=insert(ploio,p[0],p[1]);	//eisagoume to ploio sth nea thesh
			take_out(oldship);					//kai to svhnoume apo thn palia
		}
	}
	if(x<size-1 && plegma[x+1][y].get_over()){	//elegxoume to shmeio deksia
		p=plegma[x+1][y].threatenpoint();
		ploio=plegma[x+1][y].get_ship();
		oldship=plegma[x+1][y].get_ship();
		if(ploio.get_hitpoints()==0){
			inserted=insert(ploio,p[0],p[1]);
			take_out(oldship);
		}
	}
	if(y>0 && plegma[x][y-1].get_over()){	//elegxoume to shmeio kato
		p=plegma[x][y-1].threatenpoint();
		ploio=plegma[x][y-1].get_ship();
		oldship=plegma[x][y-1].get_ship();
		if(ploio.get_hitpoints()==0){		
			inserted=insert(ploio,p[0],p[1]);
			take_out(oldship);
		}
	}
	if(y<size-1 && plegma[x][y+1].get_over()){	//elegxoume to shmeio pano
		p=plegma[x][y+1].threatenpoint();
		ploio=plegma[x][y+1].get_ship();
		oldship=plegma[x][y+1].get_ship();
		if(ploio.get_hitpoints()==0){
			inserted=insert(ploio,p[0],p[1]);
			take_out(oldship);
		}
	}*/
}

void grid::take_out(ship ploio){
	int *p;
	int l,d,x,y;
	p=ploio.get_fore();
	x=p[0];
	y=p[1];
	l=ploio.get_length();
	d=ploio.get_direction();
	for(int i=0; i<l; i++){
		if(d==0)
			plegma[x][y+i].set_over();
		if(d==1)
			plegma[x+i][y].set_over();
		if(d==2)
			plegma[x][y-i].set_over();
		if(d==3)
			plegma[x-i][y].set_over();
	}

}

grid::~grid(){
	for(int i=0; i<size; i++)
		delete [] plegma[i];
	delete plegma;
}

int main(int argc, char *argv[]){

int N,K,K1,K2,K3,x,y;
long curtime;
bool inserted=false;
tug *t;
submarine *sb;
aircraft_carrier *a1,a[2];
submarine b[3];
tug c[6];
curtime = time(NULL);
srand((unsigned int) curtime); /* Seed for random number generator */
N=atoi(argv[1]);
//cout<<"Please give N:"<<endl;
//cin>>N;
gridsize=N;
grid neoplegma(N);
neoplegma.print();
K=0.75*N*N;
K1=0.02*N*N;
K2=0.03*N*N;
K3=0.06*N*N;
aircraft_carriers=K1;
submarines=K2;
tugs=K3;
ships=K1+K2+K3;
//a1=new aircraft_carrier[K1];
//sb=new submarine[K2];
//t=new tug[K3];
for(int i=0; i<2; i++){			//i<K1
	x=rand()%gridsize;
	y=rand()%gridsize;
	while(!inserted)
		inserted=neoplegma.insert(a[i],x,y);
	inserted=false;
}
inserted=false;
for(int i=0; i<3; i++){		//i<K2
	x=rand()%gridsize;
	y=rand()%gridsize;
	while(!inserted)
		inserted=neoplegma.insert(b[i],x,y);
	inserted=false;
}
inserted=false;
for(int i=0; i<2; i++){		//K3
	x=rand()%gridsize;
	y=rand()%gridsize;
	while(!inserted)
		inserted=neoplegma.insert(c[i],x,y);
	inserted=false;
}
for(int i=0; i<5; i++){			//i<K
	cout<<"Please give x:";
	cin>>x;
	cout<<"Please give y:";
	cin>>y;
	neoplegma.update(x,y);
	neoplegma.print();
	if(ships==0){
		cout<<"You, Master of the Sea!"<<endl;
		cout<<"Total score:"<<score<<"\t"<<K3-tugs<<" tugs, "<<K2-submarines<<" submarines, "<<K1-aircraft_carriers<<" aircraft carriers"<<endl;
		break;
	}
}
if(ships!=0){
	cout<<"Game Over!"<<endl;
		cout<<"Total score:"<<score<<"\t"<<K3-tugs<<" tugs, "<<K2-submarines<<" submarines, "<<K1-aircraft_carriers<<" aircraft carriers"<<endl;
}
}




