package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.helper.Template;
import godinner.app.model.Produto;
import godinner.app.model.TemplateRestaurante;

public interface TemplateRestauranteRepository extends JpaRepository<TemplateRestaurante, Long> {
	
	@Query("SELECT t FROM TemplateRestaurante t WHERE t.restaurante.id = ?1")
	TemplateRestaurante getTemplate(int idRestaurante);
	

}
