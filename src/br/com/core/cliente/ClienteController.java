package br.com.core.cliente;

import java.util.List;
import java.util.Scanner;



public class ClienteController {
	
	private final ClienteServico clienteServico;
	private Scanner entrada;
	
	public ClienteController(Scanner entrada) {
		this.clienteServico = new ClienteServico();
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
			Cliente cliente = new Cliente(nome);
			clienteServico.inserir(cliente);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void atualizar() {
		try {
			System.out.println("digite o id:");
			Long id = entrada.nextLong();
			Cliente cliente = clienteServico.getClientePorId(id);
			//consumindo o \n
			entrada.nextLine();
			
			System.out.println("digite um nome:");
			String nome = entrada.nextLine();
			cliente.setNome(nome);
			clienteServico.atualizar(cliente);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void deletarPorId() {
		try {
			System.out.println("digite o id:");
			Long id = entrada.nextLong();
			clienteServico.excluir(id);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void listarTodos() {
		try {
			List<Cliente> clientes = clienteServico.getTodosClientes();
			System.out.printf("%10s %20s\n", "ID", "NOME");
			for (Cliente cliente : clientes) {
				System.out.printf("%10s %20s\n", cliente.getId(), cliente.getNome());
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
			Cliente cliente = clienteServico.getClientePorId(id);
			
			System.out.printf("%10s %20s\n", "ID", "NAME");
			System.out.printf("%10s %20s\n", cliente.getId(), cliente.getNome());		
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
}
