package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import main.Game;

public class Factory extends Structure{
	
	private final int TANK = 0;
	private final int WORKER = 1;
	
	private final int[] prodTime = {10000,1000};
	private final int[] prodCost = {10,2};
	
	private long prodTimeStart;
	private long prodTimeRemaining;
	private int prodType;
	private String prodName;
	
	public Factory(int posX, int posY) {
		super(new Sprite(new Texture("Structures/factory.png")), posX, posY);
		prodTimeRemaining = -1;
	}

	@Override
	public void update() {
		if(prodTimeRemaining >= 0) {
			prodTimeRemaining = System.currentTimeMillis() - prodTimeStart;
			if(prodTimeRemaining >= prodTime[prodType]) {
				if(prodType == TANK) {
					Game.createTank(prodName, posX, posY);
				}else {
					Game.createWorker(prodName, posX, posY);
				}
				prodTimeRemaining = -1;
			}
		}
	}
	
	public boolean canProduce (String product) {
		return prodTimeRemaining == -1 && (product.equals("tank") || product.equals("worker"));
	}
	
	public void produce (String product, String productName) {
		if(product.equals("tank")) {
			prodType = TANK;
		}else {
			prodType = WORKER;
		}
		
		prodName = productName;
		prodTimeStart = System.currentTimeMillis();
		prodTimeRemaining = prodTime[prodType];
	}

}
