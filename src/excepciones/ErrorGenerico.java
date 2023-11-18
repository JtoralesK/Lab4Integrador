package excepciones;

public class ErrorGenerico extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
    public ErrorGenerico() {
        super();
        mensaje = "Algo salió mal. Intente de nuevo más tarde";
    }

    
    @Override
    public String getMessage()
    {
    	return mensaje;
    }
}
