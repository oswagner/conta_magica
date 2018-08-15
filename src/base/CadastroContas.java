package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class CadastroContas {

	private List<ContaMagica> contas;

	public CadastroContas() {
		this.contas = new ArrayList<>();
	}

	public void insereConta(ContaMagica conta) {
		this.contas.add(conta);
	}

	public void removeConta(String nomeTitular) {
		Predicate<ContaMagica> contaPredicate = conta -> conta.getNomeCliente().equalsIgnoreCase(nomeTitular);
		this.contas.removeIf(contaPredicate);
	}

	public ContaMagica pesquisaConta(String nomeTitular) {
		Iterator listItr = this.contas.iterator();
		while (listItr.hasNext()) {
			ContaMagica conta = (ContaMagica) listItr.next();
			if (conta.getNomeCliente().equalsIgnoreCase(nomeTitular))
				return conta;
		}
		return null;
	}

}
