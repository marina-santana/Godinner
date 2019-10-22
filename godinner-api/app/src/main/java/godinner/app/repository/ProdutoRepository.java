package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Categoria;
import godinner.app.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p WHERE p.id = ?1")
	Produto getProdutoById(int id);
	
	@Query("SELECT p FROM Produto p WHERE p.restaurante.id = ?1 AND p.status = 1 ORDER BY p.id DESC")
	List<Produto> getProdutosByIdRestaurante(int id);

	@Query(value = "SELECT p FROM Produto p  WHERE p.desconto > 0 AND p.restaurante.id = ?1 and p.status = 1", nativeQuery = false)
	List<Produto> getProdutoPromocao(int id);

	@Query(value = "SELECT p.* FROM tbl_produto AS p where p.id_restaurante = ?1 limit 10;", nativeQuery = true)
	List<Produto> getTodosProdutosExibicao(int id);

	@Query(value = "SELECT p.* FROM tbl_produto AS p where p.id_restaurante = ?1 and p.status = 1 limit 10;", nativeQuery = true)
	List<Produto> getProdutoExibicao(int id);
	
	@Query(value = "SELECT p FROM Produto p WHERE p.status = 0 AND p.restaurante.id = ?1")
	List<Produto> getProdutosDesativados(int idRestaurante);
	
	@Query(value = "SELECT 	p.* FROM tbl_produto AS p WHERE p.id_restaurante = ?1 AND p.status = 1 ORDER BY RAND() LIMIT 6", nativeQuery=true)
	List<Produto> getProdutosTemplate(int idRestaurante);
	
	@Query(value = "select p.*, sum(pp.quantidade) as quantidade From tbl_produto as p Join tbl_produto_pedido as pp ON pp.id_produto = p.id_produto WHERE p.id_restaurante = ?1 group by pp.id_produto order by quantidade desc", nativeQuery=true)
	List<Produto> getProdutosQuantidade(int idRestaurante);
	
}