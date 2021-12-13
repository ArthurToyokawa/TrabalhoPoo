package br.com.core.venda;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.EntityManager.EntityManagerFactoryProducer;


public class VendaServico {
	
	private final EntityManager entityManager;
	
	public VendaServico() {
		this.entityManager = EntityManagerFactoryProducer.createEntityManager();
	}
	
	public VendaServico(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Venda getVendaPorId(long id) {
		return getEntityManager().find(Venda.class, id);
	}
	
	public List<Venda> getTodosVendas() {
		return getEntityManager().createQuery("from Venda", Venda.class).getResultList();
	}
	
	public void inserir(Venda venda) {
		venda.setData(LocalDateTime.now());
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(venda);
		getEntityManager().getTransaction().commit();
	}
	
	public Venda atualizar(Venda venda) {
		venda.setData(LocalDateTime.now());
		getEntityManager().getTransaction().begin();
		Venda managedVenda = getEntityManager().merge(venda);
		getEntityManager().getTransaction().commit();
		return managedVenda;
	}
	
	public void excluir(long id) {
		Venda venda = getVendaPorId(id);
		if (venda != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(venda);
			getEntityManager().getTransaction().commit();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
