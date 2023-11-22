package entidad;

public enum eTipoMovimiento {
	Transferencia("Transferencia"),
	AltaPrestamo("Alta Prestamo"),
	AltaCuenta("Alta Cuenta"),
	PagoPrestamo("Pago Prestamo");
	
    private final String descripcion;

    eTipoMovimiento(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
