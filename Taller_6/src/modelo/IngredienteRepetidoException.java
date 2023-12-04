package modelo;

public class IngredienteRepetidoException extends HamburguesaException{

	public IngredienteRepetidoException(String ingrediente) {
		super("Error, se repitió el ingrediente: "+ingrediente);
	}
	
}
