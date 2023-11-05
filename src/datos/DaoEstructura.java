package datos;

import java.util.List;

public interface DaoEstructura<T> {
	  List<T>  selectAll();
	  Boolean insert();
	  Boolean logicalDelete();
}
