package assets;

public enum FontFiles {
	
	DigitalFont ("uiskin/uiskin.json");

	private String filePath;

	   
	FontFiles(String filePath){
		this.filePath = filePath;

	}

	public String toString(){
		return filePath;
	}
}
