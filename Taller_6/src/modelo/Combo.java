package modelo;

import java.util.ArrayList;

public class Combo implements Producto{
	
	//Attributes
	
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> itemsCombo;
	
	
	//Constructor
	
	public Combo(String nombreCombo,double descuento) {
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		itemsCombo = new ArrayList<Producto>();
	}

	
	//Methods
	
	public void agregarItemACombo(Producto itemCombo) {
		
		itemsCombo.add(itemCombo);
		
	}
	

	public String generarTextoFactura() {
		
		String mensaje = "\n Combo " + nombreCombo + "Precio " +  getPrecio() +" cop \n";
		
		return mensaje;
	}
	
	public int getPrecio() {
		
		double precioCompleto = 0;
		
		for (Producto items: itemsCombo) {
			
			precioCompleto += (items.getPrecio() * (1 - descuento));
		}
		
		int precioRetorno = (int) precioCompleto;
		return precioRetorno;
	}
	
	public String getNombre() {
		return nombreCombo;
	}
	
	public ArrayList<Producto> getItemsCombo(){
		return itemsCombo;
	}
}
