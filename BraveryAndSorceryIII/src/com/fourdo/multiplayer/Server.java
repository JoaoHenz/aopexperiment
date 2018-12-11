package com.fourdo.multiplayer;

import com.fourdo.gamelogic.GameMap;

public class Server extends Thread{

	Player players[];
	char[][][] mapsbuffers;
	String IPv4;
	char[][] map;
	boolean map_washacked;
	int maps_received;
	public boolean game_hasended;
	
	public void receiveMap(int id,String player_ipv4,char[][] newmap) {
		if (player_ipv4 != this.IPv4) {
			if (!GameMap.newmap_isvalid(this.map, newmap)) {
				System.out.println("The map was hacked!\n");
				this.map_washacked = true;
			}	
		}
		if (this.map_washacked==false) {
			mapsbuffers[id-1] = newmap;
			this.maps_received +=1;
		}
	}
	
	public Server(Player players[],char[][][] messagebuffers, String IPv4,char[][] map) {
		this.players = players;
		this.mapsbuffers = messagebuffers;
		this.IPv4 = IPv4;
		this.map = map;
		this.map_washacked = false;
		this.maps_received = 0;
		this.game_hasended = false;
	}
	
	
	public void run() {
		while (!game_hasended) {
			//System.out.printf("valor de maps received: %d\n",this.maps_received);
			if (maps_received == players.length && map_washacked == false) {
				this.map = GameMap.updateMap(mapsbuffers);
				System.out.println("The map was updated!!!\n");
				//System.out.printf("player length is %d",players.length);
				maps_received = 0;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
