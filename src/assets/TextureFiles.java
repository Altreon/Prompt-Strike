package assets;

public enum TextureFiles {
		
	Worker ("Units/worker.png"),
	TankChassis ("Units/tank/chassis.png"),
	TankCannon ("Units/tank/cannon.png"),
	  
	Headquarter ("Structures/headquarter.png"),
	Factory ("Structures/factory.png"),
	
	TankFire ("Effects/tankFire.png"),
	TankImpact ("Effects/tankImpact.png");

	private String filePath;

	   
	TextureFiles(String filePath){
		this.filePath = filePath;

	}

	public String toString(){
		return filePath;
	}
}
