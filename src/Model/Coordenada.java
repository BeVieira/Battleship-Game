package Model;

public class Coordenada {
	private int xCoord;
	private int yCoord;
	
	public Coordenada(String coordenada) {
		xCoord = Integer.valueOf(coordenada.substring(1,coordenada.length()))-1;
		yCoord = Integer.valueOf(coordenada.charAt(0))-65;
	}

	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}
	
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
}
