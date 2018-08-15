package base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CadastroContasTest {

	private CadastroContas cadastroContas;
	@BeforeEach
	void setup() {
		this.cadastroContas = new CadastroContas();
	}
	
	@Test
	void testInsereConta() {
		ContaMagica conta = new ContaMagica("Luke");
		cadastroContas.insereConta(conta);
		
		assertEquals(conta, cadastroContas.pesquisaConta("Luke"));
	}

	@Test
	void testRemoveConta() {
		ContaMagica conta = new ContaMagica("Luke");
		cadastroContas.insereConta(conta);
		cadastroContas.removeConta("Luke");
		
		assertEquals(null, cadastroContas.pesquisaConta("Luke"));
	}

	@Test
	void testPesquisaConta() {
		ContaMagica conta = new ContaMagica("Luke");
		cadastroContas.insereConta(conta);
		
		assertEquals(conta, cadastroContas.pesquisaConta("Luke"));
	}

}
