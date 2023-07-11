package ViniciusSoares96.com.github;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ViniciusSoares96.com.github.domain.Categoria;
import ViniciusSoares96.com.github.domain.Cidade;
import ViniciusSoares96.com.github.domain.Cliente;
import ViniciusSoares96.com.github.domain.Endereco;
import ViniciusSoares96.com.github.domain.Estado;
import ViniciusSoares96.com.github.domain.ItemPedido;
import ViniciusSoares96.com.github.domain.Pagamento;
import ViniciusSoares96.com.github.domain.PagamentoComBoleto;
import ViniciusSoares96.com.github.domain.PagamentoComCartao;
import ViniciusSoares96.com.github.domain.Pedido;
import ViniciusSoares96.com.github.domain.Produto;
import ViniciusSoares96.com.github.domain.enums.EstadoPagamento;
import ViniciusSoares96.com.github.domain.enums.TipoCliente;
import ViniciusSoares96.com.github.repositories.CategoriaRepository;
import ViniciusSoares96.com.github.repositories.CidadeRepository;
import ViniciusSoares96.com.github.repositories.ClienteRepository;
import ViniciusSoares96.com.github.repositories.EnderecoRepository;
import ViniciusSoares96.com.github.repositories.EstadoRepository;
import ViniciusSoares96.com.github.repositories.ItemPedidoRepository;
import ViniciusSoares96.com.github.repositories.PagamentoRepository;
import ViniciusSoares96.com.github.repositories.PedidoRepository;
import ViniciusSoares96.com.github.repositories.ProdutoRepository;

@SpringBootApplication
public class PraticaModelagemDeDados1Application implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(PraticaModelagemDeDados1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "363335453", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("32317477","31323544"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apt 203","Jardim","38956400",cli1,c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","1045","Sala 800","Centro","35795100",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm");
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/10/2022 22:14"), cli1, e1);
		
		Pedido ped2 = new Pedido(null,sdf.parse("29/06/2023 21:37"),cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, ped2,sdf2.parse("06/07/2023"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.0,2,80.0);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.0,1,800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

	
	
}
