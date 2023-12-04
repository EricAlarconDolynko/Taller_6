package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Restaurante {
	
	//Attributes
	
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Ingrediente> modificacionesAgregar;
	private ArrayList<Ingrediente> modificacionesEliminar;
	private ArrayList<IdFacturas> historialFacturas;
	private ArrayList<String> nombreIngredientes;
	//Constructor 
	
	
	public Restaurante() throws IngredienteRepetidoException,ProductoRepetidoException{
		combos = new ArrayList<Combo>();
		pedidos = new ArrayList<Pedido>();
		pedidoEnCurso = null;
		menuBase = new ArrayList<ProductoMenu>();
		ingredientes = new ArrayList<Ingrediente>();
		modificacionesAgregar = new ArrayList<Ingrediente>();
		modificacionesEliminar = new ArrayList<Ingrediente>();
		historialFacturas = new ArrayList<IdFacturas>();
		nombreIngredientes = new ArrayList<String>();
		
		cargarInformacionRestaurante(new File("./data/menu.txt"), new File("./data/combos.txt"));
	}
	

	//Methods
	
	public void iniciarPedido(String nombreCliente,String direccionCliente) {
		
		if (pedidoEnCurso == null){
		pedidoEnCurso = new Pedido(nombreCliente,direccionCliente);
		pedidos.add(pedidoEnCurso);
		}
		
		else {
			System.out.println("Espere un momento, estamos antendiendo otro pedido");
		}
			
	}
	
	public void adicionElemento(int posicion) throws PedidoCaroException{
		
		Pedido pedido = getPedidoEnCurso();
		Producto productoAdicionar = menuBase.get(posicion-1);
		pedido.agregarProducto(productoAdicionar, pedido);
		
	}
	
	public void adicionCombo(int posicion)throws PedidoCaroException { 
		
		Pedido pedido = getPedidoEnCurso();
		Producto productoAdicionar = combos.get(posicion-23);

		pedido.agregarProducto(productoAdicionar, pedido);
		
		
	}
	
	public void adicionElementoModificado(int posicion) throws PedidoCaroException {
		
		Pedido pedido = getPedidoEnCurso();
		ProductoMenu productoModificar = menuBase.get(posicion-1);
		ProductoAjustado productoAjustado = new ProductoAjustado(productoModificar);
		
		
		if (modificacionesAgregar.size() != 0) {
			
			for (Ingrediente ingrediente: modificacionesAgregar) {
				
				productoAjustado.agregarAgregados(ingrediente);
			}
		}
		
		if (modificacionesEliminar.size() != 0) {
			for (Ingrediente ingrediente: modificacionesEliminar) {
				
				productoAjustado.agregarEliminados(ingrediente);
				}
			}
		
		pedido.agregarProducto(productoAjustado, pedido);
		
		modificacionesAgregar = new ArrayList<Ingrediente>();
		modificacionesEliminar = new ArrayList<Ingrediente>();
		}
		
	
	public void adicionCriteriosModificado(int posicionIngre, int modificacion) {
		
		Pedido pedido = getPedidoEnCurso();
		Ingrediente ingredienteModificacion = ingredientes.get(posicionIngre-1);
		
		
		if (modificacion == 1) {
			modificacionesAgregar.add(ingredienteModificacion);
		}
		
		else if (modificacion == 2) {
			modificacionesEliminar.add(ingredienteModificacion);
			
		}
		
	}
	
	
	public void cerrarYGuardarPedido() {
		
		String factura = pedidoEnCurso.generarTextoFactura();
		System.out.println(factura);
		historialFactura(factura,pedidoEnCurso.getIdPedido());
		pedidoEnCurso.guardarFactura(factura);
		pedidoEnCurso = null;
		
		
	}
	
	public void historialFactura(String factura, int id) {
		
		IdFacturas facturita = new IdFacturas(id,factura);
		historialFacturas.add(facturita);
	}
	
	public void getRegistroHistorial(int id) {
		
		IdFacturas facturaActual = historialFacturas.get(id-1);
		System.out.println(facturaActual.getFactura());
		
		
		int counter = 1;
		for (IdFacturas facturas: historialFacturas) {
			if ( facturas.getFactura().equals(facturaActual.getFactura())) {
				if (counter == 0){
					System.out.println("Hay un pedido exactamente igual");
				}
				else {
					counter -= 1;
				}
			}
		}
		System.out.println("Esta factura es única :D");
		
	}
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
		
	}


	public ArrayList<ProductoMenu> getMenuBase() {
		return menuBase;
	}

	public ArrayList<Combo> getCombos() {
		return combos;
	}

	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void cargarInformacionRestaurante(File archivoMenu, File archivoCombos) throws IngredienteRepetidoException, ProductoRepetidoException{
		
		cargarIngredientes(new File("./data/ingredientes.txt"));
		cargarMenu(archivoMenu );
		cargarCombos(archivoCombos);
			
	}

	
	private void cargarIngredientes(File archivoIngredientes) throws IngredienteRepetidoException{
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivoIngredientes));
			String linea = lector.readLine();
			
			while(linea!=null) {
				String [] datos = linea.split(";");
				
				String nombreIngrediente = datos[0].trim();
				
				if (nombreIngredientes.contains(nombreIngrediente)) {
					throw new IngredienteRepetidoException(nombreIngrediente);
				}
				
				nombreIngredientes.add(nombreIngrediente);
				
				int precioIngrediente = Integer.parseInt(datos[1].trim());
				Ingrediente ingrediente = new Ingrediente(nombreIngrediente,precioIngrediente);
				
				ingredientes.add(ingrediente);
				linea = lector.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void cargarMenu(File archivoMenu) throws ProductoRepetidoException{
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivoMenu));
			String linea = lector.readLine();
			while(linea!=null) {
				String [] datos = linea.split(";");
				String nombreItem = datos[0].trim();
				int precioItem = Integer.parseInt(datos[1].trim());
				
				ProductoMenu itemMenu = new ProductoMenu(nombreItem,precioItem);
				boolean verify = checkProductoRepetido(itemMenu);
				
				if (verify == true) {
					throw new ProductoRepetidoException(itemMenu.getNombre());
				}
				
				menuBase.add(itemMenu);
				 
				linea = lector.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			}
	
	
	private void cargarCombos(File archivoCombos) {
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivoCombos));
			String linea = lector.readLine();
			int counter = 0;
			while(linea!=null) {
				String [] datos = linea.split(";");
				String nombreCombo = datos[0].trim();
				String nombreDescuento = datos[1].trim().split("%")[0];
				double descuento = Double.parseDouble(nombreDescuento) / 100;
				Combo elCombo = new Combo(nombreCombo,descuento);
				
				for (int i = 2;i<5;i++) {	
					int precioBuscar = 0;
					String nombreBuscar = datos[i].trim();
					
					for (ProductoMenu items : menuBase) {
						if (nombreBuscar.equals(items.getNombre())){
							precioBuscar = items.getPrecio();
						}
					}
				Producto productoCombo = new ProductoMenu(nombreBuscar,precioBuscar);
				elCombo.agregarItemACombo(productoCombo);
				}
				
				combos.add(elCombo);
				linea = lector.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private boolean checkProductoRepetido(ProductoMenu itemMenu) {
		
		boolean verify = false;
		
		for (ProductoMenu producto: menuBase) {
			if (producto.getNombre().equals(itemMenu.getNombre())){
				verify = true;
			}
		}
		
		return verify;
	}
	
}
