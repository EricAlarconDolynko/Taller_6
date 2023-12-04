package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Pedido {
	
	//Attributes
	
	private static int numeroPedidos = 0;
	public static int idPedido = 1;
	private String nombreCliente;
	private String direccionCliente;
	public ArrayList<Producto> itemsPedido;
	public int precioActual;
	
	//Constructor 
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		itemsPedido = new ArrayList<Producto> ();
		this.precioActual = 0;
	}
	
	
	//Methods
	
	public void agregarProducto(Producto nuevoItem, Pedido pedidoActual) throws PedidoCaroException{
		
		int precioFuturo = precioActual + nuevoItem.getPrecio();
		if (precioFuturo > 150000) {
			throw new PedidoCaroException(precioFuturo,nuevoItem.getNombre());
		}
		else {
			itemsPedido.add(nuevoItem);
			precioActual += nuevoItem.getPrecio();
			numeroPedidos +=1;
		}
		
	}
	
	public String generarTextoFactura() {
		
		String mensaje = "=================";
		      mensaje += "     Factura     ";
		      mensaje += "=================";
		      mensaje += "\n id de la factura " + idPedido;
		      mensaje += "\n A nombre de: " + nombreCliente;
		      mensaje += "\n Lugar destino: " + direccionCliente;
		
		int precio = 0;
		
		for (Producto producto: itemsPedido) {
			mensaje += "\n";
			mensaje += producto.generarTextoFactura() + " ";
			mensaje += "\n";
			precio += producto.getPrecio();
			
		}
		
		mensaje += "\n Precio Neto: " + precio + " cop \n";
		double precioDecimal = precio * 0.19;
		mensaje += "IVA (19%): " + precioDecimal + " cop \n";
		double total = precio + precioDecimal;
		mensaje += "Precio Total: " + total + " cop \n";
		
		return mensaje;
	}
	
	public void guardarFactura (String textoFactura) {
		
		
		try {
			Properties pFactura = new Properties();
			pFactura.load(new FileInputStream(new File("./data/facturas.txt")));
			String llave = Integer.toString(idPedido);
			pFactura.put(llave, textoFactura);
			
			try {
				pFactura.save(new FileOutputStream(new File ("./data/facturas.txt")),"");
				}
				catch(FileNotFoundException e) {
					System.err.println("File not found: " + e.getMessage());
				}
			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		numeroPedidos = 0;
		idPedido += 1;
	}
	
	
	//Getters
	
	public int getIdPedido() {
		return idPedido;	
	}

	
	
	
	
	
	
	
	
}
