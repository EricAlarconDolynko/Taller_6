package consola;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Combo;
import modelo.Ingrediente;
import modelo.IngredienteRepetidoException;
import modelo.PedidoCaroException;
import modelo.ProductoMenu;
import modelo.ProductoRepetidoException;
import modelo.Restaurante;

public class Aplicacion {

	//Attributes
	
	private Restaurante restaurante;
	
	
	
	public Aplicacion() {
		
		try {
			
		this.restaurante = new Restaurante();
		ejecutarConsola();
		}
		catch (IngredienteRepetidoException e) {
			e.getMessage();
			System.out.println(e);
			System.out.println("Porfavor arregle los datos antes de continuar");
			System.out.println("Carga de información del restaurante fallida");
		}
		catch (ProductoRepetidoException e) {
			e.getMessage();
			System.out.println(e);
			System.out.println("Porfavor arregle los datos antes de continuar");
			System.out.println("Carga de información del restaurante fallida");
		}
	}

	//Methods
	public void ejecutarConsola(){
		
		System.out.println("Bienvenido a Hermenegilda Delights, el mejor restaurante que encontrara");
		System.out.println("Porfavor le recomendamos que le de un vistaso a nuestro menu");
		
		Scanner reader = new Scanner(System.in);
		boolean flag = true;
		while (flag == true) {
			
			System.out.println("Escriba el numero de la opcion que quiere ejecutar");
			System.out.println("1. Mostrar menu");
			System.out.println("2. Iniciar un nuevo pedido");
			System.out.println("3. Agregar un elemento a un pedido");
			System.out.println("4. Cerrar un pedido");
			System.out.println("5. Consultar la información de un pedido");
		
			String answer = reader.next();
		
			if (answer.equals("1")){
				mostrarMenu();
			}
		
			else if (answer.equals("2")) {
			
				System.out.println("A nombre de quien va a ser el pedido?");
				String nombre = reader.next();
				System.out.println("Escriba su direccion sin numeros por favor!");
				String direccion = reader.next();
				restaurante.iniciarPedido(nombre,direccion);
			}
			
			else if (answer.equals("3")) {
				System.out.println("De acuerdo a la enumeracion del menu, que desea pedir");
				String opcion = reader.next();
				int numOpcion = Integer.parseInt(opcion);
				
				if (numOpcion <= 22 && numOpcion >= 1){
					
					System.out.println("Si quiere adicionar un ingrediente al item, presione 1");
					System.out.println("Si quiere eliminar un ingrediente al item, presione 2");	
					System.out.println("Si no lo quiere modificar presione 3");
					
					String opcionModificacion = reader.next();
					int modificacion = Integer.parseInt(opcionModificacion);
					
					while (modificacion == 1 || modificacion == 2) {
						
						mostrarIngredientes();
						if (modificacion == 1) {
							System.out.println("Seleccione el ingrediente que desea adicionar por su numero correspondiente");
						}
					
						else if (modificacion == 2) {
							System.out.println("Seleccione el ingrediente que desea eliminar por su numero correspondiente");

						}
						
						String opcionIngre = reader.next();
						int numOpcionIngre = Integer.parseInt(opcionIngre);
					
						restaurante.adicionCriteriosModificado(numOpcionIngre,modificacion);
						
						System.out.println("Si desea seguir adicionando ingredientes a este item presione 1");
						System.out.println("Si desea seguir eliminando ingredientes a este item presione 2");
						System.out.println("Si desea dejar hasta aqui la modificacion presione 4");
						opcionModificacion = reader.next();
						modificacion = Integer.parseInt(opcionModificacion);
						
						if (modificacion == 4) {
							try {
							restaurante.adicionElementoModificado(numOpcion);
							}
							catch (PedidoCaroException e) {
								e.getMessage();
								System.out.println(e);
								System.out.println("NO SE PUDO ADICIONAR LO QUE SE DESEABA");
								System.out.println("ADICION FALLIDA");
							}
							
						}
					
					}
					
					if (modificacion == 3) {
						try {
						restaurante.adicionElemento(numOpcion);
						}
						catch (PedidoCaroException e) {
							e.getMessage();
							System.out.println(e);
							System.out.println("NO SE PUDO ADICIONAR LO QUE SE DESEABA");
							System.out.println("ADICION FALLIDA");
						}
					}
					
					
				}
				
				else if (numOpcion > 22 && numOpcion <= 26){
					try {
					restaurante.adicionCombo(numOpcion);
					}
					catch (PedidoCaroException e) {
						e.getMessage();
						System.out.println(e);
						System.out.println("NO SE PUDO ADICIONAR LO QUE SE DESEABA");
						System.out.println("ADICION FALLIDA");
					}
					
				}
				
				else {
					System.out.println("Ingrese una opcion valida por favor");
				}
				
			}
			
			
			else if (answer.equals("4")) {
				restaurante.cerrarYGuardarPedido();
			}
			
			else if (answer.equals("5")) {
				System.out.println("Ingrese el id de la factura o pedido que quiere checkear ");
				String opcionHistorial = reader.next();
				int historial = Integer.parseInt(opcionHistorial);
				restaurante.getRegistroHistorial(historial);
				
			}
			
		}
		
	}
	
	
	public void mostrarIngredientes() {
		
		System.out.println("=======================");
		System.out.println("      Ingredientes     ");
		System.out.println("=======================");
		
		ArrayList<Ingrediente> listaIngredientes = restaurante.getIngredientes();
		int i = 1;
		
		for (Ingrediente item: listaIngredientes) {
			
			String mensaje = i + ". " + item.getNombre() + " ---> " + item.getCostoAdicional() + " cop";
			System.out.println(mensaje);
			
			i++;
		}
		
		
	}
	
	
	public void mostrarMenu() {
		
		System.out.println("=======================");
		System.out.println("         Menu          ");
		System.out.println("=======================");
		System.out.println("       Productos       ");
		
		ArrayList<ProductoMenu> listaMenu = restaurante.getMenuBase();
		int i = 1;
		for (ProductoMenu item: listaMenu) {
			
			String mensaje = i + ". " + item.getNombre() + " ---> " + item.getPrecio() + " cop";
			System.out.println(mensaje);
			
			i++;
		}
		
		System.out.println("=======================");
		System.out.println("Combos");
		
		ArrayList<Combo> listaCombos = restaurante.getCombos();
		
		for (Combo combitos: listaCombos) {
			
			String mensajeCombo = i + ". " + combitos.getNombre() + " ---> " + combitos.getPrecio() + " cop"; 
			System.out.println(mensajeCombo);
			
			i ++;
		}	
		System.out.println("=======================");
	}
	
	public void ejecutarOpcion(int opcionSeleccionada) {
		
	}
	
	//Main Method
	
	public static void main(String[] args) {
		Aplicacion consola = new Aplicacion();
	}

}
