package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

public class ProductoMenuTest {
	
	private ArrayList<ProductoMenu> listaProductoMenus = new ArrayList<ProductoMenu>();
	
	@BeforeEach
	public void setUp() throws Exception{
		try {
			BufferedReader lector = new BufferedReader(new FileReader(new File("./data/menu.txt")));
			String linea = lector.readLine();
			while(linea!=null) {
				String [] datos = linea.split(";");
				String nombreItem = datos[0].trim();
				int precioItem = Integer.parseInt(datos[1].trim());
				ProductoMenu itemMenu = new ProductoMenu(nombreItem,precioItem);
				listaProductoMenus.add(itemMenu);
				linea = lector.readLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testGenerarFactura(){
		
		for (ProductoMenu productoActual: listaProductoMenus) {
			String factura = productoActual.generarTextoFactura();
			String resultadoCorrecto = "\n item: " + productoActual.getNombre() +  "precio: " + 
										productoActual.getPrecio() + "\n";
			
			assertEquals(resultadoCorrecto,factura);
		}
		
	}
	
}
