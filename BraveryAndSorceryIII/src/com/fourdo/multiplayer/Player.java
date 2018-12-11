package com.fourdo.multiplayer;

public class Player extends Thread{

	int id;
	String name;
	int number;
	String IPv4;
	char[] receivebuffer;
	public Server server;
	public boolean must_sendmap;
	public boolean game_hasended;
	char[][] map;
	
	public Player(String name, String IPv4,char[] messagebuffer,char[][] map,int id) {
		this.name = name;
		this.IPv4 = IPv4;
		this.receivebuffer = messagebuffer;
		this.must_sendmap = false;
		this.game_hasended = false;
		this.map = map;
		this.id = id;
	}
	
	public void makePlay(int[] tile,char mod) {
		//System.out.printf("tamanho do map: %d %d \n",map.length,map[0].length);
		this.map[tile[0]][tile[1]] = mod;
		this.must_sendmap = true;
	}
	
	public void run() {
		while (!game_hasended) {
			
			if (must_sendmap) {
				System.out.println(this.name+" is sending a map to the server!");
				this.server.receiveMap(this.id,this.IPv4,this.map);
				this.must_sendmap = false;
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
		public void makeHack(int[] tile,char mod) {
			//System.out.printf("tamanho do map: %d %d \n",map.length,map[0].length);
			this.map[tile[0]][tile[1]] = mod;
			this.must_sendmap = false;
		}
		
		
	
}
