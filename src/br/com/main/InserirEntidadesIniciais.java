package br.com.main;


import javax.persistence.EntityManager;

import br.com.EntityManager.EntityManagerFactoryProducer;
import br.com.core.cliente.Cliente;
import br.com.core.cliente.ClienteServico;
import br.com.core.itemVenda.ItemVenda;
import br.com.core.itemVenda.ItemVendaServico;
import br.com.core.produto.Produto;
import br.com.core.produto.ProdutoServico;
import br.com.core.venda.Venda;
import br.com.core.venda.VendaServico;


public class InserirEntidadesIniciais {
	public static void inserir() {
		
		EntityManager entityManager = EntityManagerFactoryProducer.createEntityManager();
		
		ClienteServico clienteServico = new ClienteServico(entityManager);
		
		Cliente cliente1 = new Cliente("Willian");
		Cliente cliente2 = new Cliente("Maria");
		
		clienteServico.inserir(cliente1);
		clienteServico.inserir(cliente2);
		
		ProdutoServico produtoServico = new ProdutoServico(entityManager);
		
		Produto produto1 = new Produto("batata", 100);
		Produto produto2 = new Produto("Maca", 50);
		
		produtoServico.inserir(produto1);
		produtoServico.inserir(produto2);
		
		VendaServico vendaServico = new VendaServico(entityManager);
		
		Venda venda1 = new Venda(cliente1);
		Venda venda2 = new Venda(cliente2);
		
		vendaServico.inserir(venda1);
		vendaServico.inserir(venda2);
		
		ItemVendaServico itemVendaServico = new ItemVendaServico();
		
		ItemVenda itemVenda1 = new ItemVenda(produto1, venda1, 5);
		ItemVenda itemVenda2 = new ItemVenda(produto2, venda2, 10);
		ItemVenda itemVenda3 = new ItemVenda(produto1, venda2, 15);
		
		itemVendaServico.inserir(itemVenda1);
		itemVendaServico.inserir(itemVenda2);
		itemVendaServico.inserir(itemVenda3);
		
		entityManager.close();
	}
}
