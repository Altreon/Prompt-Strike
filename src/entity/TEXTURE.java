package entity;

public enum TEXTURE {
	
	Worker ("Units/worker.png"),
	TankChassis ("Units/tank/chassis.png"),
	TankCannon ("Units/tank/cannon.png"),
	  
	Factory ("Structures/factory.png"),
	
	TankFire ("effects/tankFire.png"),
	TankImpact ("effects/tankImpact.png");

	private String texturePath;

	   
	TEXTURE(String texturePath){
		this.texturePath = texturePath;

	}

	public String toString(){
		return texturePath;
	}
}
