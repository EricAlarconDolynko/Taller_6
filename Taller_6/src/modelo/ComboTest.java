package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

public class ComboTest {

	private Combo combo;
	private Producto productoPrueba1;
	private Producto productoPrueba2;
	
	@BeforeEach
	public void setUp() {
		combo = new Combo("Corral", 0.139);
		productoPrueba1 = new ProductoMenu("Pizza", 11000);
		productoPrueba2 = new ProductoMenu("Sushi", 20000);
	}
	
	
	@Test
	public void testGetItemCombo() {
		combo.agregarItemACombo(productoPrueba1);
		ArrayList<Producto> listaRetorno = combo.getItemsCombo();
		
		assertEquals(1,listaRetorno.size());
		
		Producto item = listaRetorno.get(0);
		org.junit.Assert.assertEquals(item.getNombre(),"Pizza");
		assertEquals(item.getPrecio(),11000);
		
	}
	
	@Test
	public void testAgregarItemCombo(){
		combo.agregarItemACombo(productoPrueba1);
		ArrayList<Producto> listaRetorno = combo.getItemsCombo();
		
		boolean verifyExist = false;
		
		for (Producto producto : listaRetorno) {
			if (producto.getNombre().equals("Pizza")){
				if (producto.getPrecio() == 11000) {
					verifyExist = true;
				}
			}
		}
		
		assertTrue(verifyExist);
		
	}
	
	
	@Test
	public void testGenerarFacturaSinElementos() {
		
		String mensaje = "\n Combo " + "Corral" + "Precio " +  0 +" cop \n";
		
		org.junit.Assert.assertEquals(mensaje,combo.generarTextoFactura());
	}
	
	@Test
	public void testGenerarFacturaConUnElemento() {
		
		combo.agregarItemACombo(productoPrueba1);

		String mensaje = "\n Combo " + "Corral" + "Precio " +  9471 +" cop \n";
		org.junit.Assert.assertEquals(mensaje,combo.generarTextoFactura());

	}
	
	@Test
	public void testGenerarFacturaConVariosElementos() {
		
		combo.agregarItemACombo(productoPrueba1);
		combo.agregarItemACombo(productoPrueba2);

		String mensaje = "\n Combo " + "Corral" + "Precio " + 26691 +" cop \n";
		org.junit.Assert.assertEquals(mensaje,combo.generarTextoFactura());

	}
	
}
