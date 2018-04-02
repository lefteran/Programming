#include <iostream>
#include <ctime>

using namespace std;

int gridsize,score=0,ships,tugs,submarines,aircraft_carriers;		//sto ships kratame to K1+K2+K3 kai ta alla omoios gia na doume an vythistikan ola

class ship{
	private:
		bool threat;
		int direction;		//0 gia pano apo thn plorh, 1 gia deksia, 2 gia kato, 3 gia aristera
	protected:
		int hitpoints;			//plithos shmeion pou exoun xtypithei
		int forex;				//tetmhmenh plorhs
		int forey;				//tetagmenh plorhs
		int length;
	public:
		ship();
		void set_fore(int x, int y){forex=x; forey=y;}
		int get_hitpoints(){return hitpoints;}
		int *get_fore();		//epistrefei tis syntetagmenes ths plorhs
		void set_direction(int n){direction=n;}
		int get_direction(){return direction;}
		int get_length(){return length;}
		virtual void hit(){cout<<"";}
		virtual int *threaten(){cout<<"";}		
		virtual void sink(){cout<<"";}
		void score_update(int newscore);
};

class tug: public ship{
	public:
		tug();
		void hit();
		int *threaten();
		void sink();
};

class submarine: public ship{
	public:
		submarine();
		void hit();
		int *threaten();		
		void sink();
};

class aircraft_carrier: public ship{
	public:
		aircraft_carrier();
		void hit();
		int *threaten();
		void sink();
};

class gridpoint{
	private:
		int x;
		int y;
		bool is_over;		//gia na gnorizoume an yparxei ploio se afto to shmeio
		bool hit;			//gia na gnorizoume an to ploio pou einai pano se afto exei xtypithei
		bool chosen;		//gia na gnorizoume an to shmeio exei epilegei prohgoumena apo to xrhsth
		ship * ploio;			//deikths sto ploio pou vrisketai pano sto shmeio		
	public:
		int get_x(){return x;}
		int get_y(){return y;}
		void set_ship(ship &neoploio){ploio=&neoploio;}
		ship get_ship(){return *ploio;}
		void hitpoint();
		int *threatenpoint(){return ploio->threaten();}			
		bool is_hit(){return hit;}
		bool is_chosen(){return chosen;}
		bool get_over(){return is_over;}
		void set_over(){is_over=(is_over)?false:true;}
		void set_chosen(){chosen=true;}
		gridpoint();
		~gridpoint();
};


class grid{
	private:
		gridpoint **plegma;
		int size;
	public:
		grid(int N); 
		bool insert(ship ploio, int x, int y);	//prospathei na eisagei to ploio sto plegma me plorh sto (x,y) 
												//kai epistrefei true an vrhke katallhlh thesh
		void print();
		void update(int x, int y);		//san orismatata dinontai oi syntetagmenes pou dinei o xrhsths kai psaxnei na vrei nea thesh an apeileitai
										//kapoio apo ta geitonika shmeia
		void take_out(ship ploio);		//an ena ploio paei se allh thesh metatrepei thn palia thesh tou se kenh sto plegma
		~grid();
};





