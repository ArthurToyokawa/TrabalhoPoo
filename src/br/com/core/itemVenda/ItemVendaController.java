package br.com.core.itemVenda;

import java.util.List;
import java.util.Scanner;

import br.com.core.produto.Produto;
import br.com.core.produto.ProdutoServico;
import br.com.core.venda.Venda;
import br.com.core.venda.VendaServico;


public class ItemVendaController {
	
	private final ProdutoServico produtoServico;
	private final VendaServico vendaServico;
	private final ItemVendaServico itemVendaServico;
	private Scanner entrada;
	
	public ItemVendaController(Scanner entrada) {
		this.produtoServico = new ProdutoServico();
		this.vendaServico = new VendaServico();
		this.itemVendaServico = new ItemVendaServico();
		this.entrada =  new Scanner(System.in);
	}
	
	public void solicitarOpcao() {
		System.out.println("----- Sistema de Exemplo -----");
		System.out.println("1 - listar todos");
		System.out.println("2 - listar por id");
		System.out.println("3 - Cadastrar novo");
		System.out.println("4 - atualizar cadastro");
		System.out.println("5 - deletar por id");
		System.out.println("0 - voltar");

		System.out.println("Selecione uma opcao:");

		chamarService(entrada.nextLine());
	}
	private void chamarService(final String opcao) {
		if (opcao.equals("0")) {
			return;
		}
		
		switch (opcao) {
		case "1":
			listarTodos();
			break;
		case "2":
			listarPorId();
			break;
		case "3":
			cadastrar();
			break;
		case "4":
			atualizar();
			break;
		case "5":
			deletarPorId();
			break;

		default:
			System.out.println("Opcao Invalida!");
			break;
		}
	}
	
	private void cadastrar() {
		try {
			System.out.println("digite o id do produto:");
			Long idProduto = entrada.nextLong();
			Produto produto = produtoServico.getProdutoPorId(idProduto);
			System.out.println("digite o id da venda:");
			Long idVenda = entrada.nextLong();
			Venda venda = vendaServico.getVendaPorId(idVenda);
			System.out.println("digite uma quantidade:");
			int quantidade = entrada.nextInt();
			
			if(produto.getEstoque() < quantidade) {
				System.out.println("quantidade maior que o estoque atual:");
				return;
			}
			
			ItemVenda itemVenda = new ItemVenda(produto, venda, quantidade);
			itemVendaServico.inserir(itemVenda);
			
			produto.setEstoque(produto.getEstoque() - quantidade);
			produtoServico.atualizar(produto);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void atualizar() {
		try {
			System.out.println("digite o id:");
			Long id = entrada.nextLong();
			ItemVenda itemVenda = itemVendaServico.getItemVendaPorId(id);
			
			System.out.println("digite o id do produto:");
			Long idProduto = entrada.nextLong();
			Produto produto = produtoServico.getProdutoPorId(idProduto);
			itemVenda.setProduto(produto);
			System.out.println("digite o id da venda:");
			Long idVenda = entrada.nextLong();
			Venda venda = vendaServico.getVendaPorId(idVenda);
			itemVenda.setVenda(venda);
			System.out.println("digite uma quantidade:");
			int quantidade = entrada.nextInt();
			int diffQuantidade = itemVenda.getQuantidade() - quantidade;
			itemVenda.setQuantidade(quantidade);
			
			if(produto.getEstoque() < diffQuantidade) {
				System.out.println("quantidade maior que o estoque atual:");
				return;
			}
			
			itemVendaServico.atualizar(itemVenda);			
			produto.setEstoque(produto.getEstoque() - diffQuantidade);
			produtoServico.atualizar(produto);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void deletarPorId() {
		try {
			System.out.println("digite o id:");
			Long id = entrada.nextLong();
			ItemVenda itemVenda = itemVendaServico.getItemVendaPorId(id);
			Produto produto = produtoServico.getProdutoPorId(itemVenda.getProduto().getId());
			produto.setEstoque(produto.getEstoque() + itemVenda.getQuantidade());
			
			itemVendaServico.excluir(itemVenda.getId());
			produtoServico.atualizar(produto);
			
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void listarTodos() {
		try {
			List<ItemVenda> itemVendas = itemVendaServico.getTodosItemVendas();
			System.out.printf("%10s %20s %20s %20s\n", "ID", "IDVENDA", "PRODUTO", "QUANTIDADE");
			for (ItemVenda itemVenda : itemVendas) {
				System.out.printf("%10s %20s %20s %20s\n", itemVenda.getId(), itemVenda.getVenda().getId(), itemVenda.getProduto().getNome(), itemVenda.getQuantidade());
			}		
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
		
	}
	
	private void listarPorId() {
		try {
			System.out.println("digite o id:");
			Long id = entrada.nextLong();
			ItemVenda itemVenda = itemVendaServico.getItemVendaPorId(id);

			System.out.printf("%10s %20s %20s %20s\n", "ID", "IDVENDA", "PRODUTO", "QUANTIDADE");
			System.out.printf("%10s %20s %20s %20s\n", itemVenda.getId(), itemVenda.getVenda().getId(), itemVenda.getProduto().getNome(), itemVenda.getQuantidade());
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
}
