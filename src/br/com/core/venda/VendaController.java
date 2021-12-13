package br.com.core.venda;

import java.util.List;
import java.util.Scanner;

import br.com.core.cliente.Cliente;
import br.com.core.cliente.ClienteServico;


public class VendaController {
	
	private final VendaServico vendaServico;
	private final ClienteServico clienteServico;
	private Scanner entrada;
	
	public VendaController(Scanner entrada) {
		this.clienteServico = new ClienteServico();
		this.vendaServico = new VendaServico();
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
			System.out.println("digite o id do cliente:");
			Long id = entrada.nextLong();
			Cliente cliente = clienteServico.getClientePorId(id);
			Venda venda = new Venda(cliente);
			vendaServico.inserir(venda);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void atualizar() {
		try {
			System.out.println("digite o id:");
			Long id = entrada.nextLong();
			Venda venda = vendaServico.getVendaPorId(id);
			System.out.println("digite o id do cliente:");
			Long idCliente = entrada.nextLong();
			Cliente cliente = clienteServico.getClientePorId(idCliente);
			venda.setCliente(cliente);
			vendaServico.atualizar(venda);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void deletarPorId() {
		try {
			System.out.println("digite o id:");
			Long id = entrada.nextLong();
			vendaServico.excluir(id);
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
	private void listarTodos() {
		try {
			List<Venda> vendas = vendaServico.getTodosVendas();
			System.out.printf("%10s %20s\n", "ID", "CLIENTE");
			for (Venda venda : vendas) {
				System.out.printf("%10s %20s\n", venda.getId(), venda.getCliente().getNome());
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
			Venda venda = vendaServico.getVendaPorId(id);

			System.out.printf("%10s %20s\n", "ID", "CLIENTE");
			System.out.printf("%10s %20s\n", venda.getId(), venda.getCliente().getNome());
		}catch(Exception e) {
			System.out.println("Erro: ");
			System.out.println(e);
		}
	}
	
}
