package duoc.devolucion.repository;

import duoc.devolucion.model.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevolucionRepository extends JpaRepository<Devolucion, Integer> {
}
