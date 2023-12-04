package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

public class ProductoAjustadoTest {
	
	private ProductoMenu productoMenu;
	private ProductoAjustado producto;

	@BeforeEach
	public void setUp() {
		
		
		

		this.productoMenu = new ProductoMenu("Hamburguesa",30000);
		this.producto = new ProductoAjustado(productoMenu);
		
	}
	
	@Test
	public void testAgregarIngredienteAgregado() {
		
		producto.agregarAgregados(new Ingrediente("Carne",9000));
		ArrayList<Ingrediente> listaProducto = producto.getAgregados();
		
		boolean verifyExiste = false;
		for (Ingrediente ingrediente: listaProducto) {
			if (ingrediente.getNombre().equals("Carne")) {
				if (ingrediente.getCostoAdicional() == 9000) {
					verifyExiste = true;
				}
			}
		}
		
		assertTrue(verifyExiste == true, "Did not add correctly to the list");
		
	}
	
	@Test 
	public void testEliminarIngredienteAgregado() {
		producto.agregarEliminados(new Ingrediente("Carne",9000));
		
		ArrayList<Ingrediente> listaProductoEliminado = producto.getEliminados();
		
		boolean verifyExiste = false;
		for (Ingrediente ingrediente: listaProductoEliminado) {
			if (ingrediente.getNombre().equals("Carne")) {
				if (ingrediente.getCostoAdicional() == 9000) {
					verifyExiste = true;
				}
			}
		}
		
		assertTrue(verifyExiste == true, "Did not eliminate correctly to the list");
		
	}
	
	@Test
	public void testObtenerListaAgregados() {
		
		ArrayList<Ingrediente> listaRespuesta = new ArrayList<Ingrediente>();
		listaRespuesta.add(new Ingrediente("Carne",9000));
		listaRespuesta.add(new Ingrediente("Lechona",18000));
		listaRespuesta.add(new Ingrediente("Pollo",19000));
		listaRespuesta.add(new Ingrediente("Pasta",13000));

		producto.agregarAgregados(new Ingrediente("Carne",9000));
		producto.agregarAgregados(new Ingrediente("Lechona",18000));
		producto.agregarAgregados(new Ingrediente("Pollo",19000));
		producto.agregarAgregados(new Ingrediente("Pasta",13000));

		ArrayList<Ingrediente> listaMetodo = producto.getAgregados();
		assertEquals(listaRespuesta.size(),listaMetodo.size(),"La lista no tiene el tamaño esperado");
		
		for (int i = 0;i < listaRespuesta.size(); i++) {
			
			String nombreRespuesta  = listaRespuesta.get(i).getNombre();
			int precioRespuesta  = listaRespuesta.get(i).getCostoAdicional();
			
			org.junit.Assert.assertEquals(nombreRespuesta, listaMetodo.get(i).getNombre() );
			assertEquals(precioRespuesta, listaMetodo.get(i).getCostoAdicional());
		}
		
	}
	
	@Test 
	public void testObtenerListaEliminados() {
	
		
		ArrayList<Ingrediente> listaRespuesta = new ArrayList<Ingrediente>();
		listaRespuesta.add(new Ingrediente("Carne",9000));
		listaRespuesta.add(new Ingrediente("Lechona",18000));
		listaRespuesta.add(new Ingrediente("Pollo",19000));
		listaRespuesta.add(new Ingrediente("Pasta",13000));

		producto.agregarEliminados(new Ingrediente("Carne",9000));
		producto.agregarEliminados(new Ingrediente("Lechona",18000));
		producto.agregarEliminados(new Ingrediente("Pollo",19000));
		producto.agregarEliminados(new Ingrediente("Pasta",13000));

		ArrayList<Ingrediente> listaMetodo = producto.getEliminados();
		assertEquals(listaRespuesta.size(),listaMetodo.size(),"La lista no tiene el tamaño esperado");
		
		for (int i = 0;i < listaRespuesta.size(); i++) {
			
			String nombreRespuesta  = listaRespuesta.get(i).getNombre();
			int precioRespuesta  = listaRespuesta.get(i).getCostoAdicional();
			
			org.junit.Assert.assertEquals(nombreRespuesta, listaMetodo.get(i).getNombre() );
			assertEquals(precioRespuesta, listaMetodo.get(i).getCostoAdicional());
		}
		
	}
	
	
	@Test
	public void testGenerarFacturaHayAgregadosYEliminados() {
		
		producto.agregarAgregados(new Ingrediente("Lechona",18000));
		producto.agregarEliminados(new Ingrediente("Carne",9000));
		
		String mensaje = "\n item: " + "Hamburguesa" +  "precio: " + "30000" +" cop ";
		mensaje += "\n\t";
		mensaje += "\n adición de: " + "Lechona" + " precio " + "18000" + " cop"+ "\n ";
		mensaje += "\n eliminando: " + "Carne" + "sin coste adicional \n";
		
		org.junit.Assert.assertEquals(mensaje,producto.generarTextoFactura());
		
	}
	
	@Test
	public void testGenerarFacturaSoloEliminados() {
		
		producto.agregarEliminados(new Ingrediente("Carne",9000));
		
		String mensaje = "\n item: " + "Hamburguesa" +  "precio: " + "30000" +" cop ";
		mensaje += "\n\t";
		mensaje += "\n eliminando: " + "Carne" + "sin coste adicional \n";
		
		org.junit.Assert.assertEquals(mensaje,producto.generarTextoFactura());
		
	}
	
	@Test
	public void testGenerarFacturaSoloAgregados() {
		
		producto.agregarAgregados(new Ingrediente("Lechona",18000));
		
		String mensaje = "\n item: " + "Hamburguesa" +  "precio: " + "30000" +" cop ";
		mensaje += "\n\t";
		mensaje += "\n adición de: " + "Lechona" + " precio " + "18000" + " cop"+ "\n ";
		
		org.junit.Assert.assertEquals(mensaje,producto.generarTextoFactura());
		
	}
	@Test
	public void testGetPrecioSinAdicionSinEliminado() {
		assertEquals(30000,producto.getPrecio());
	}
	
	@Test
	public void testGetPrecioConAdicionConEliminado() {
		producto.agregarAgregados(new Ingrediente("Lechona",18000));
		producto.agregarEliminados(new Ingrediente("Maiz",22000));

		assertEquals(48000,producto.getPrecio());
	}
	
	@Test
	public void testGetPrecioSoloAdicion() {
		producto.agregarAgregados(new Ingrediente("Lechona",18000));
		
		assertEquals(48000,producto.getPrecio());
	}
	
	@Test
	public void testGetPrecioSoloEliminado() {
		producto.agregarEliminados(new Ingrediente("Maiz",22000));

		assertEquals(30000,producto.getPrecio());

		
	}
}
