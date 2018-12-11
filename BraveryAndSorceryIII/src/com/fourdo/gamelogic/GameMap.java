package com.fourdo.gamelogic;

public class GameMap {
		
	public static char[][] updateMap(char[][][] mapsbuffers){
		char[][] updatedmap = new char[40][40];
		
		int i,j,k;
		i=0;
		for (j=0;j<40;j++) {
			for (k=0;k<40;k++) {
				if (mapsbuffers[0][j][k] == mapsbuffers[1][j][k]) {
					updatedmap[j][k] = mapsbuffers[0][j][k];
				}
				else {
					if (i==0) {
						updatedmap[j][k]= mapsbuffers[0][j][k];
						i+=1;
					}
					else {
						updatedmap[j][k]= mapsbuffers[1][j][k];
					}
				}
			}
		}
		
				
				
		return updatedmap;
	}
	
	public static boolean newmap_isvalid(char oldmap[][],char newmap[][]) {
		int dif_counter = 0;
		
		int i,j;
		for (i=0;i<40;i++) {
			for (j=0;j<40;j++) {
				if (oldmap[i][j]!=newmap[i][j]) {
					dif_counter+=1;
				}
			}	
		}
		//System.out.printf("number of differences:%d \n",dif_counter);
		if (dif_counter>2) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
}
