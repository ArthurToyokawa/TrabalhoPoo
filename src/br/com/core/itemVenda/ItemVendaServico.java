package br.com.core.itemVenda;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.EntityManager.EntityManagerFactoryProducer;


public class ItemVendaServico {
	
	private final EntityManager entityManager;
	
	public ItemVendaServico() {
		this.entityManager = EntityManagerFactoryProducer.createEntityManager();
	}
	
	public ItemVendaServico(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ItemVenda getItemVendaPorId(long id) {
		return getEntityManager().find(ItemVenda.class, id);
	}
	
	public List<ItemVenda> getTodosItemVendas() {
		return getEntityManager().createQuery("from ItemVenda", ItemVenda.class).getResultList();
	}
	
	public void inserir(ItemVenda itemVenda) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(itemVenda);
		getEntityManager().getTransaction().commit();
	}
	
	public ItemVenda atualizar(ItemVenda itemVenda) {
		getEntityManager().getTransaction().begin();
		ItemVenda managedItemVenda = getEntityManager().merge(itemVenda);
		getEntityManager().getTransaction().commit();
		return managedItemVenda;
	}
	
	public void excluir(long id) {
		ItemVenda itemVenda = getItemVendaPorId(id);
		if (itemVenda != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(itemVenda);
			getEntityManager().getTransaction().commit();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
