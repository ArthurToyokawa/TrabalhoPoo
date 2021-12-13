package br.com.core.produto;

import java.util.List;
import java.util.Scanner;



public class ProdutoController {
	
	private final ProdutoServico produtoServico;
	private Scanner entrada;
	
	public ProdutoController(Scanner entrada) {
		this.produtoServico = new ProdutoServico();
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
			System.out.println("digite um nome:");
			String nome = entrada.nextLine();
			System.out.println("digite um estoque:");
			int estoque = entrada.nextInt();
			Produto produto = new Produto(nome, estoque);
			produtoServico.inserir(produto);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void atualizar() {
		try {
			System.out.println("digite o id:");
			Long id = entrada.nextLong();
			Produto produto = produtoServico.getProdutoPorId(id);
			
			System.out.println("digite um nome:");
			String nome = entrada.nextLine();
			produto.setNome(nome);
			System.out.println("digite um estoque:");
			int estoque = entrada.nextInt();
			produto.setEstoque(estoque);
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
			produtoServico.excluir(id);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void listarTodos() {
		try {
			List<Produto> produtos = produtoServico.getTodosProdutos();
			System.out.printf("%10s %20s %20s\n", "ID", "NOME", "ESTOQUE");
			for (Produto produto : produtos) {
				System.out.printf("%10s %20s %20s\n", produto.getId(), produto.getNome(), produto.getEstoque());
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
			Produto produto = produtoServico.getProdutoPorId(id);

			System.out.printf("%10s %20s %20s\n", "ID", "NOME", "ESTOQUE");
			System.out.printf("%10s %20s %20s\n", produto.getId(), produto.getNome(), produto.getEstoque());
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
}
