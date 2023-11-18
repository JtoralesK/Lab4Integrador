package excepciones;

public class CbuIncorrecto extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
    public CbuIncorrecto() {
        super();
        mensaje = "Cbu incorrecto. Verifique los datos ingresados";
    }

    
    @Override
    public String getMessage()
    {
    	return mensaje;
    }
}
