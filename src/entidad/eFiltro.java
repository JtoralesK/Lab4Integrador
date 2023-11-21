package entidad;

public enum eFiltro {
    Menor("Menor"),
    MenorIgual("Menor o igual"),
    Mayor("Mayor"),
    MayorIgual("Mayor o igual"),
    Igual("Igual");

    private final String descripcion;

    eFiltro(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
