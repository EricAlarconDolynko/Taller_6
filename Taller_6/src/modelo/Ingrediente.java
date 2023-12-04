package modelo;

public class Ingrediente {

	
	//Attributes
	
	private String nombre;
	private int costoAdicional;
	
	//Constructor
	
	public Ingrediente(String nombre, int costoAdicional) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}

	
	//Getters
	
	public String getNombre() {
		return nombre;
	}

	
	public int getCostoAdicional() {
		return costoAdicional;
	}
	
	
	
}
