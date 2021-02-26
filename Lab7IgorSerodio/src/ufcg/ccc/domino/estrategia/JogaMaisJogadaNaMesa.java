package ufcg.ccc.domino.estrategia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

/**
 * Joga priorizando peças cujos números mais se repitiram na mesa. Tenta primeiro o lado direito, depois o esquerdo.
 */
public class JogaMaisJogadaNaMesa implements EstrategiaDeJogo {

	@Override
	public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
		if (mesa.getNumPecas() == 0) {
			return new Jogada(mao.get(0), TipoJogada.NA_DIREITA);
		}
		
		int[] contaPecas = new int[7];
		
		for(int i = 0; i<7; i++) {
			contaPecas[i] = 0;
		}
		
		for(Peca peca: mesa.getPecasNaMesa()) {
			contaPecas[peca.getNumDireito()]++;
			contaPecas[peca.getNumEsquerdo()]++;
		}
		
		List<Peca> copiaMao = new ArrayList<Peca>(mao);
		
		Collections.sort(copiaMao, Collections.reverseOrder(new ComparadorPeca(contaPecas)));
		
		for(Peca peca: copiaMao) {
			if(peca.encaixa(mesa.getNumNaDireita()))
				return new Jogada(peca, TipoJogada.NA_DIREITA);
			if(peca.encaixa(mesa.getNumNaEsquerda()))
				return new Jogada(peca, TipoJogada.NA_ESQUERDA);
		}
		
		return new Jogada();
	}
	
	private class ComparadorPeca implements Comparator<Peca>{
		
		int[] referencia;
		
		public ComparadorPeca(int[] referencia) {
			this.referencia = referencia;
		}

		@Override
		public int compare(Peca o1, Peca o2) {
			Integer i1 = Integer.valueOf(referencia[o1.getNumEsquerdo()] + referencia[o1.getNumDireito()]);
			Integer i2 = Integer.valueOf(referencia[o2.getNumEsquerdo()] + referencia[o2.getNumDireito()]);
			
			return i1.compareTo(i2);
		}
		
	}
}
