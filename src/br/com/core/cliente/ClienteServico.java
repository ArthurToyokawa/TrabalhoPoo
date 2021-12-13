package br.com.core.cliente;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.EntityManager.EntityManagerFactoryProducer;


public class ClienteServico {
	
	private final EntityManager entityManager;
	
	public ClienteServico() {
		this.entityManager = EntityManagerFactoryProducer.createEntityManager();
	}
	
	public ClienteServico(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Cliente getClientePorId(long id) {
		return getEntityManager().find(Cliente.class, id);
	}
	
	public List<Cliente> getTodosClientes() {
		return getEntityManager().createQuery("from Cliente", Cliente.class).getResultList();
	}
	
	public void inserir(Cliente cliente) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(cliente);
		getEntityManager().getTransaction().commit();
	}
	
	public Cliente atualizar(Cliente cliente) {
		getEntityManager().getTransaction().begin();
		Cliente managedCliente = getEntityManager().merge(cliente);
		getEntityManager().getTransaction().commit();
		return managedCliente;
	}
	
	public void excluir(long id) {
		Cliente cliente = getClientePorId(id);
		if (cliente != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(cliente);
			getEntityManager().getTransaction().commit();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
