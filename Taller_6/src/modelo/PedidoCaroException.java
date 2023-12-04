package modelo;

public class PedidoCaroException extends HamburguesaException {

	public PedidoCaroException(int precio, String producto) {
		super("Error, el producto: "+ producto + " hace que el pedido supere los 150 000 pesos \n"
				+ "ya que haría que el pedido total cueste: " + precio + "pesos ");
	}
	
}
