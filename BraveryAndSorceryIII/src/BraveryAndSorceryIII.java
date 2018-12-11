import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.fourdo.multiplayer.Player;
import com.fourdo.multiplayer.Server;

public class BraveryAndSorceryIII {

	
	static void waitAndPlay(Player player, int[] tile) {
		
		
		//tile[0] = ThreadLocalRandom.current().nextInt(0,40+1);
		//tile[1] = ThreadLocalRandom.current().nextInt(0,40+1);
		char mod = (char)ThreadLocalRandom.current().nextInt(33,126+1);
		player.makePlay(tile, mod);
		
		try {
			Thread.sleep(100);;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void hack(Player player, int[] tile) {
	
		char mod = (char)ThreadLocalRandom.current().nextInt(33,126+1);
		player.makeHack(tile, mod);
	}
	
	
	public static void main(String args[]) {
		
		char[][][] mbs_server = new char[2][40][40]; //mb = message buffers
		char[][] mbs_player = new char[2][30];
		
		//TODO open map1 and map2
		char[][][] maps = new char[2][40][40];
		int i,j,k;
		for (i=0;i<40;i++) {
			for (j=0;j<40;j++) {
				for (k=0;k<2;k++) {
					maps[k][j][i] = 'O';
				}
			}
		}
		
		Player player1 = new Player("player1","192.0.0.2",mbs_player[0],maps[0],1);
		Player player2 = new Player("player2","192.0.0.3",mbs_player[1],maps[1],2);
		Player[] playerlist = {player1,player2};
		Server server = new Server(playerlist,mbs_server,"192.0.0.2",maps[0]);
		
		player1.server = server;
		player2.server = server;
		player1.start();
		player2.start();
		server.start();
		
		
		int[] tile = new int[2];
		
		tile[0]= 11;tile[1]= 25;
		waitAndPlay(player1,tile);
		
		tile[0]= 25;tile[1]= 9;
		waitAndPlay(player2,tile);
		
		/*
		tile[0]= 37;tile[1]= 8;
		hack(player1,tile);
		*/
		tile[0]= 14;tile[1]= 2;
		waitAndPlay(player1,tile);
		
		
		tile[0]= 37;tile[1]= 8;
		hack(player2,tile);
		
		tile[0]= 36;tile[1]= 8;
		waitAndPlay(player2,tile);
		
		
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		player1.game_hasended = true;
		player2.game_hasended = true;
		server.game_hasended = true;
		
		try {
			player1.join();
			player2.join();
			server.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("End of the execution.............");
		
		
		
	}
}
