package excepciones;

public class ArgumentoInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
    public ArgumentoInvalidoException() {
        super();
    }

    public ArgumentoInvalidoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    public ArgumentoInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.mensaje = mensaje;
    }
    
    @Override
    public String getMessage()
    {
    	return mensaje.isEmpty() ? "Alguno de los valores insertados es incorrecto" : mensaje;
    }
}
