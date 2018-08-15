package base;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ContaMagicaTest {
	
	private ContaMagica conta;
	
	@BeforeEach
	void setup() {
		conta = new ContaMagica("Luke");
	}

	@Test 
	void testContaMagica() {
		assertNotNull(conta);
	}

	@Test 
	void testGetNomeCliente() {
		assertEquals("Luke", conta.getNomeCliente());
	}

	@Test 
	void testGetSaldoZerado() {
		BigDecimal saldoZerado = new BigDecimal(0);
		assertEquals(saldoZerado, conta.getSaldo());
	}
	

	@Test 
	void testGetStatusSilver() {
		assertNotNull(conta.getStatus());
		assertEquals(Categorias.SILVER, conta.getStatus());
	}
	
	@Test 
	void testGetStatusGoldValorSuperior() {
		conta.deposito(new BigDecimal(51000));
		assertEquals(Categorias.GOLD, conta.getStatus());
	}
	
	@Test 
	void testGetStatusGoldValorIgual() {
		conta.deposito(new BigDecimal(50000));
		assertEquals(Categorias.GOLD, conta.getStatus());
	}
	
	@Test 
	void testGetStatusPlatinum() {
		conta.deposito(new BigDecimal(201000));
		assertEquals(Categorias.PLATINUM, conta.getStatus());
	}
		

	@Test 
	void testDepositoClienteSilver() {
		BigDecimal saldo = new BigDecimal(100);
		conta.deposito(saldo);
		assertEquals(saldo, conta.getSaldo());
	}
	
	@Test 
	void testDepositoClienteGold() {
		BigDecimal depositado = new BigDecimal(60000);
		conta.deposito(depositado);
		conta.deposito(new BigDecimal(100));
		BigDecimal saldo = new BigDecimal("60101.00");
		assertEquals(saldo, conta.getSaldo());
	}
	
	@Test 
	void testDepositoClientePlatinum() {
		BigDecimal depositado = new BigDecimal(201000);
		conta.deposito(depositado);
		conta.deposito(new BigDecimal(100));
		BigDecimal saldo = new BigDecimal("201102.500");
		assertEquals(saldo, conta.getSaldo());
	}

	@Test 
	void testRetirada() {
		BigDecimal valorSaque = new BigDecimal(100);
		BigDecimal valorSaldo = new BigDecimal(0);
		conta.deposito(valorSaque);
		conta.retirada(valorSaque);
		assertEquals(valorSaldo, conta.getSaldo());
	}
	
	@Test
	void testRetiradaRebaixaCategoriaPlatinumParaGold() {
		BigDecimal depositado = new BigDecimal(201000);
		conta.deposito(depositado);
		BigDecimal valorSaque = new BigDecimal(102000);
		conta.retirada(valorSaque);
		BigDecimal valorSaldo = new BigDecimal(99000);
		assertEquals(valorSaldo, conta.getSaldo());
		assertEquals(Categorias.GOLD, conta.getStatus());
	}
	
	
	@Test
	void testRetiradaRebaixaCategoriaGoldParaSilver() {
		BigDecimal depositado = new BigDecimal(60000);
		conta.deposito(depositado);
		BigDecimal valorSaque = new BigDecimal(40000);
		conta.retirada(valorSaque);
		BigDecimal valorSaldo = new BigDecimal(20000);
		assertEquals(valorSaldo, conta.getSaldo());
		assertEquals(Categorias.SILVER, conta.getStatus());
	}
	
	@Test
	void testRetiradaNaoRebaixaPlatinum() {
		BigDecimal depositado = new BigDecimal(201000);
		conta.deposito(depositado);
		BigDecimal valorSaque = new BigDecimal(50000);
		conta.retirada(valorSaque);
		BigDecimal valorSaldo = new BigDecimal(151000);
		assertEquals(valorSaldo, conta.getSaldo());
		assertEquals(Categorias.PLATINUM, conta.getStatus());
	}
	
	@Test
	
	void testRetiradaZeraSaldoRebaixaCategoriaGold() {
		BigDecimal depositado = new BigDecimal(201000);
		conta.deposito(depositado);
		BigDecimal valorSaque = new BigDecimal(201000);
		conta.retirada(valorSaque);
		BigDecimal valorSaldo = new BigDecimal(0);
		assertEquals(valorSaldo, conta.getSaldo());
		assertEquals(Categorias.GOLD, conta.getStatus());
	}
	
}
