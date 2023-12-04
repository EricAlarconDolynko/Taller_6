package modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {

	//Attributes
	
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	//Constructor
	
	public ProductoAjustado(ProductoMenu base) {
		agregados = new ArrayList();
		eliminados = new ArrayList();
		this.base = base;
	}
	
	//Methods 
	
	public void agregarAgregados(Ingrediente ingrediente){
		agregados.add(ingrediente);
	}
	
	public void agregarEliminados(Ingrediente ingrediente){
		eliminados.add(ingrediente);
	}
	
	public ArrayList<Ingrediente> getAgregados(){
		return agregados;
	}
	
	public ArrayList<Ingrediente> getEliminados(){
		return eliminados;
	}
	
	public String generarTextoFactura() {
		
		String mensaje = "\n item: " + base.getNombre() +  "precio: " + base.getPrecio()+" cop ";
		mensaje += "\n\t";
		
		for (Ingrediente itemAgregado: agregados) {
			mensaje += "\n adición de: " + itemAgregado.getNombre() + " precio " + itemAgregado.getCostoAdicional() + " cop"+ "\n ";		
			}
		
		for (Ingrediente itemEliminado: eliminados) {
			mensaje += "\n eliminando: " + itemEliminado.getNombre() + "sin coste adicional \n";
		}
		
		
		return mensaje;
	}
	
	//Getters
	
	public String getNombre() {
		
		String nombre = base.getNombre();
		return nombre;
	}
	
	public int getPrecio() {
		
		int precio = base.getPrecio();
		
		for (Ingrediente agregaditos: agregados) {
			precio += agregaditos.getCostoAdicional();
		}
		
		return precio;
	}
	
}
