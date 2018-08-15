package base;

import java.math.BigDecimal;

public class ContaMagica {

	private String nome;
	private BigDecimal saldo;
	private Categorias categoria;

	public ContaMagica(String nome) {
		this.nome = nome;
		this.saldo = new BigDecimal(0);
		this.categoria = Categorias.SILVER;
	}

	public String getNomeCliente() {
		return nome;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public Categorias getStatus() {
		return this.categoria;
	}

	public void deposito(BigDecimal valor) {

		if (categoria.equals(Categorias.GOLD)) {
			this.saldo = this.saldo.add(valor.multiply(new BigDecimal("0.01")));
		}
		if (categoria.equals(Categorias.PLATINUM)) {
			this.saldo = this.saldo.add(valor.multiply(new BigDecimal("0.025")));
		}
		this.saldo = this.saldo.add(valor);

		if (this.saldo.compareTo(new BigDecimal(200000)) == 1) {
			this.categoria = Categorias.PLATINUM;
		} else if (this.saldo.compareTo(new BigDecimal(50000)) == 1
				|| this.saldo.compareTo(new BigDecimal(50000)) == 0) {
			this.categoria = Categorias.GOLD;
		} else {
			this.categoria = Categorias.SILVER;
		}
	}

	public void retirada(BigDecimal valor) {
		this.saldo = this.saldo.subtract(valor);
		
		if (this.categoria.equals(Categorias.PLATINUM)) {
			if (this.saldo.compareTo(new BigDecimal(100000)) == -1) {
				this.categoria = Categorias.GOLD;	
			}
		} else if (this.saldo.compareTo(new BigDecimal(25000)) == -1) {
			this.categoria = Categorias.SILVER;
		}
	}
}