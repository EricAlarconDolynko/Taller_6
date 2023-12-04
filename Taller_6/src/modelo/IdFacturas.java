package modelo;

public class IdFacturas {

	private int id;
	private String factura;
	
	
	
	public IdFacturas(int id, String factura) {
		
		this.id = id;
		this.factura = factura;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	
	
	
}
