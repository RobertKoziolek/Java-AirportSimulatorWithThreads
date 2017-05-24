package pckg;

import java.util.ArrayList;
import java.util.Random;

public class AirportManager { 
	private static  ArrayList<Airport> list;
	private static Random rand = new Random();
	private AirportManager(){}
	
	static{
		list = new ArrayList<Airport>();
		list.add(new Airport(40.f,40.f,"Sz"));
		list.add(new Airport(300.f,50.f,"Ol"));
		list.add(new Airport(450.f,60.f,"By"));
		list.add(new Airport(300.f,200.f,"£ó"));
		list.add(new Airport(100.f,300.f,"ZG"));
		list.add(new Airport(520.f,200.f,"Lu"));
		list.add(new Airport(400.f,350.f,"Kr"));
	}
	
	public static Airport getRandom(Airport port){
		Airport ret;
		do{
			ret = list.get(rand.nextInt(list.size()));
		}while(ret==port);
		return ret;
	}

	public static ArrayList<Airport> getList(){
		return list;
	}
}
