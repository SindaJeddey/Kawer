package kawer.tn.field;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field,Long> {
    List<Field> findAllByLocation(String location);

}
