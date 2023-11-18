package excepciones;

public class SaldoInsuficienteException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
    public SaldoInsuficienteException() {
        super();
        mensaje = "El saldo de la cuenta es insuficiente";
    }

    
    @Override
    public String getMessage()
    {
    	return mensaje;
    }
}
