package entity;

public enum TEXTURE {
	
	Worker ("Units/worker.png"),
	TankChassis ("Units/tank/chassis.png"),
	TankCannon ("Units/tank/cannon.png"),
	  
	Headquarter ("Structures/headquarter.png"),
	Factory ("Structures/factory.png"),
	
	TankFire ("Effects/tankFire.png"),
	TankImpact ("Effects/tankImpact.png");

	private String texturePath;

	   
	TEXTURE(String texturePath){
		this.texturePath = texturePath;

	}

	public String toString(){
		return texturePath;
	}
}
