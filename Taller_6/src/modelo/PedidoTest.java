package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;


public class PedidoTest {

	private Pedido pedido;
	private Producto producto;
	
	@BeforeEach
	public void setUp() throws PedidoCaroException {
		pedido = new Pedido("Eric","Cra1#13-14");
		producto = new ProductoMenu("Hamburguesa", 140000);
		
		pedido.agregarProducto(producto, pedido);
		
	}
	
	@Test
	public void testAgregarProductoSinExcepcion() throws PedidoCaroException {
		
		Producto productoNoCaro = new ProductoMenu("Mani",5000);
		pedido.agregarProducto(productoNoCaro, pedido);
		
		assertEquals(2,pedido.itemsPedido.size());
		Producto itemRetorno = pedido.itemsPedido.get(1);
		
		org.junit.Assert.assertEquals("Mani",itemRetorno.getNombre());
		assertEquals(5000, itemRetorno.getPrecio());
		assertEquals(145000,pedido.precioActual);
		
	}
	
	@Test
	public void testAgregarProductoBordeExacto() throws PedidoCaroException {
		
		Producto productoNoCaro = new ProductoMenu("Mani",10000);
		pedido.agregarProducto(productoNoCaro, pedido);
		
		assertEquals(2,pedido.itemsPedido.size());
		Producto itemRetorno = pedido.itemsPedido.get(1);
		
		org.junit.Assert.assertEquals("Mani",itemRetorno.getNombre());
		assertEquals(10000, itemRetorno.getPrecio());
		assertEquals(150000,pedido.precioActual);

	}
	
	@Test
	public void testAgregarProductoLanzaExcepcion() throws PedidoCaroException {
		Producto productoNoCaro = new ProductoMenu("Mani",10001);
		
		try {
		pedido.agregarProducto(productoNoCaro, pedido);
		fail("Expected debería mostrar error");
		}
		catch(PedidoCaroException e) {
			
		}
		
		assertEquals(1,pedido.itemsPedido.size());
		
	}
	
	@Test
	public void testGenerarFactura() {
		
		String mensaje = "=================";
	      mensaje += "     Factura     ";
	      mensaje += "=================";
	      mensaje += "\n id de la factura " + 2;
	      mensaje += "\n A nombre de: " + "Eric";
	      mensaje += "\n Lugar destino: " + "Cra1#13-14";
	      
	      mensaje += "\n";
	      mensaje += producto.generarTextoFactura() + " ";
	      mensaje += "\n";
	      mensaje += "\n Precio Neto: " + 140000 + " cop \n";
	      double iva = 0.19*140000;
	      double total = iva + 140000;
	      mensaje += "IVA (19%): " + iva + " cop \n";
	      mensaje += "Precio Total: " + total + " cop \n";
	      
	      org.junit.Assert.assertEquals(mensaje,pedido.generarTextoFactura());
	}
	
	@Test
	public void testGuardarFactura() {
		
		try {
			Properties pFactura = new Properties();
			
			String mensaje = "=================";
		      mensaje += "     Factura     ";
		      mensaje += "=================";
		      mensaje += "\n id de la factura " + 1;
		      mensaje += "\n A nombre de: " + "Eric";
		      mensaje += "\n Lugar destino: " + "Cra1#13-14";
		      
		      mensaje += "\n";
		      mensaje += producto.generarTextoFactura() + " ";
		      mensaje += "\n";
		      mensaje += "\n Precio Neto: " + 140000 + " cop \n";
		      double iva = 0.19*140000;
		      double total = iva + 140000;
		      mensaje += "IVA (19%): " + iva + " cop \n";
		      mensaje += "Precio Total: " + total + " cop \n";
		      
			pedido.guardarFactura(mensaje);
			
			pFactura.load(new FileInputStream(new File("./data/facturas.txt")));
			String respuesta = (String) pFactura.get("1");
			org.junit.Assert.assertEquals(mensaje, respuesta);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetIdFactura() {
		
		assertEquals(1,pedido.idPedido);
		
	}
	

}
