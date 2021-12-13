package br.com.main;

import java.io.PrintStream;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.EntityManager.EntityManagerFactoryProducer;
import br.com.core.cliente.ClienteController;
import br.com.core.itemVenda.ItemVendaController;
import br.com.core.produto.ProdutoController;
import br.com.core.venda.VendaController;


public class ProjetoPoo {

	private final Scanner entrada;
	private final PrintStream saida;
	private ClienteController clienteController;
	private ProdutoController produtoController;
	private VendaController vendaController;
	private ItemVendaController itemVendaController;
	EntityManager entityManager;

	public ProjetoPoo() {
		entrada = new Scanner(System.in);
		saida = System.out;
		this.entityManager = EntityManagerFactoryProducer.createEntityManager();
		clienteController = new ClienteController(entrada);
		produtoController = new ProdutoController(entrada);
		vendaController = new VendaController(entrada);
		itemVendaController = new ItemVendaController(entrada);
	}
	public static void main(String[] args) {
		InserirEntidadesIniciais.inserir();
		new ProjetoPoo().navegar();
	}
	
	public void navegar(){
		String operacaoSelecionada = "";
		do {
			operacaoSelecionada = solicitarOpcao();
			chamarController(operacaoSelecionada);
		} while (!operacaoSelecionada.equals("0"));
		EntityManagerFactoryProducer.closeDBConnection();

	}
	
	private String solicitarOpcao() {
		saida.println("----- Sistema de Vendas -----");
		saida.println("1 - Gerenciar clientes");
		saida.println("2 - Gerenciar produtos");
		saida.println("3 - Gerenciar vendas");
		saida.println("4 - Gerenciar Itens venda");
		saida.println("0 - Sair do Sistema");

		saida.println("Selecione uma opcao:");

		return entrada.nextLine();
	}
	
	private void chamarController(final String opcao) {
		if (opcao.equals("0")) {
			saida.println("Sistema encerrado.");
			return;
		}
		
		switch (opcao) {
		case "1":
			clienteController.solicitarOpcao();
			break;
		case "2":
			produtoController.solicitarOpcao();
			break;
		case "3":
			vendaController.solicitarOpcao();
			break;
		case "4":
			itemVendaController.solicitarOpcao();
			break;
		default:
			saida.println("Opcao Invalida!");
			break;
		}

		saida.println("Pressione [Enter] para continuar...");
		entrada.nextLine();
	}
}
