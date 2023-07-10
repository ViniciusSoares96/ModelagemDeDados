package ViniciusSoares96.com.github;

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
import ViniciusSoares96.com.github.domain.Produto;
import ViniciusSoares96.com.github.domain.enums.TipoCliente;
import ViniciusSoares96.com.github.repositories.CategoriaRepository;
import ViniciusSoares96.com.github.repositories.CidadeRepository;
import ViniciusSoares96.com.github.repositories.ClienteRepository;
import ViniciusSoares96.com.github.repositories.EnderecoRepository;
import ViniciusSoares96.com.github.repositories.EstadoRepository;
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
		
		
	}

	
	
}
