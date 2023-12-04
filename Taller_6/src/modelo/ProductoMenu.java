package modelo;

public class ProductoMenu implements Producto{
	
	//Attributes
	
	private String nombre;
	private int precioBase;
	
	//Constructor
	
	public ProductoMenu(String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	//Methods
	
	public int getPrecio() {
		return precioBase;
	}

	public String getNombre() {
		
		return nombre;
	}
	
	public String generarTextoFactura() {
		
		String mensaje = "\n item: " + nombre +  "precio: " + precioBase + "\n";
		
		return mensaje;
	}

	
}
