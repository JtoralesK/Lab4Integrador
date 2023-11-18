package excepciones;

public class ErrorGenerico extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
    public ErrorGenerico() {
        super();
        mensaje = "Algo sali� mal. Intente de nuevo m�s tarde";
    }

    
    @Override
    public String getMessage()
    {
    	return mensaje;
    }
}
