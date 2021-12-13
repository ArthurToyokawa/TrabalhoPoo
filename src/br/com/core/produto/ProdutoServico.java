package br.com.core.produto;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.EntityManager.EntityManagerFactoryProducer;


public class ProdutoServico {
	
	private final EntityManager entityManager;
	
	public ProdutoServico() {
		this.entityManager = EntityManagerFactoryProducer.createEntityManager();
	}
	
	public ProdutoServico(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Produto getProdutoPorId(long id) {
		return getEntityManager().find(Produto.class, id);
	}
	
	public List<Produto> getTodosProdutos() {
		return getEntityManager().createQuery("from Produto", Produto.class).getResultList();
	}
	
	public void inserir(Produto produto) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(produto);
		getEntityManager().getTransaction().commit();
	}
	
	public Produto atualizar(Produto produto) {
		getEntityManager().getTransaction().begin();
		Produto managedProduto = getEntityManager().merge(produto);
		getEntityManager().getTransaction().commit();
		return managedProduto;
	}
	
	public void excluir(long id) {
		Produto produto = getProdutoPorId(id);
		if (produto != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(produto);
			getEntityManager().getTransaction().commit();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
