package ash;
import java.util.Arrays;

public class Practice {
	
	int sizeY = 400;
	int sizeX = 600; 
	int numLayers = 8;
	int[] layer_y_pos = new int[numLayers];//{25,75,115...375}
	
	
	public void populateLayersY() {
		
		int layerHeight = sizeY/8;
		for(int i = 0; i < layer_y_pos.length; i++) {
			layer_y_pos[i] = sizeY-(i*layerHeight+25);
			System.out.print(layer_y_pos[i] + "->");
		}
		
		System.out.println();		
	}
	
	public int findClosestLayer(int playerY) {
		System.out.println("POS 0: "+layer_y_pos[0]);
		System.out.println("MAX: " + layer_y_pos[layer_y_pos.length-1]);
		
		//IF NUMBER > 325
		if(playerY > layer_y_pos[1])return 375;
		//IF NUMBER < 75
		else if(playerY < layer_y_pos[layer_y_pos.length-2])return 25;
	
		
		else {
			for(int i = 0;i < numLayers;i++) {
				
				if(playerY > layer_y_pos[i]) {
					System.out.println(playerY-layer_y_pos[i]);
					System.out.println(layer_y_pos[i-1]-playerY);
					
					//IF 
					if(playerY-layer_y_pos[i] <= layer_y_pos[i-1]-playerY) {
						System.out.println("Player Y:" + playerY);
						System.out.println("Closest Layer:" + layer_y_pos[i]);
						return layer_y_pos[i];
					}
					else{
						System.out.println("Player Y:" + playerY);
						System.out.println("Closest Layer:" + layer_y_pos[i-1]);
						return layer_y_pos[i-1];
					}
				}
			}	
		}
		
		return 0;
		
	}
	
	public int findClosestLayer_R(int[] y_values,int target) {
		
		if(y_values.length==1)return y_values[0];
		
		//if sorted
		int m = y_values.length/2;
		int mid = y_values[m];
		
		if(target == mid) return mid;
		
		int[] lowerHalf = Arrays.copyOfRange(y_values, 0, m);
		int[] upperHalf = Arrays.copyOfRange(y_values, m, y_values.length);
		
		
		if(target < mid) return findClosestLayer_R(upperHalf,target);
		
		else return findClosestLayer_R(lowerHalf,target);
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		Practice p = new Practice();
		p.populateLayersY();
		
		//int x = p.findClosestLayer(301);
		int y = p.findClosestLayer_R(p.layer_y_pos, 390);
		System.out.println(y);
		

	}

}
