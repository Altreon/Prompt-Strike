package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import main.Game;

public class Factory extends Structure{
	
	private static final int COST = 50;
	
	private final int TANK = 0;
	private final int WORKER = 1;
	
	private final int[] prodTime = {10000,1000};
	private final int[] prodCost = {10,2};
	
	private long prodTimeStart;
	private long prodTimeRemaining;
	private int prodType;
	private String prodName;
	
	public Factory(String name, int posX, int posY) {
		super(name, new Sprite(new Texture("Structures/factory.png")), posX, posY);
		prodTimeRemaining = -1;
	}
	
	public static int getCost () {
		return COST;
	}

	@Override
	public void update(int dt) {
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
		if(prodTimeRemaining != -1) {
			return false;
		}else {
			if(product.equals("tank")) {
				return Game.getPlayers().get(0).sufficientMoney(Tank.getCost());
			}else if(product.equals("worker")) {
				return Game.getPlayers().get(0).sufficientMoney(Worker.getCost());
			}else {
				return false;
			}
			
		}
	}
	
	public void produce (String product, String productName) {
		if(product.equals("tank")) {
			prodType = TANK;
			Game.removeMoney(Tank.getCost());
		}else {
			prodType = WORKER;
			Game.removeMoney(Worker.getCost());
		}
		
		prodName = productName;
		prodTimeStart = System.currentTimeMillis();
		prodTimeRemaining = prodTime[prodType];
	}

}
