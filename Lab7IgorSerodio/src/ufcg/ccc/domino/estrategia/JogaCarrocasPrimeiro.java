package ufcg.ccc.domino.estrategia;

import java.util.ArrayList;
import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

/**
 * Joga sempre as carroças se for possível (a primeira que encaixar se houver mais de uma), caso não seja, joga a primeira que encaixa. Tenta primeiro no lado direito e depois no esquerdo.
 */
public class JogaCarrocasPrimeiro implements EstrategiaDeJogo {

	@Override
	public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
		if (mesa.getNumPecas() == 0) {
			return new Jogada(mao.get(0), TipoJogada.NA_DIREITA);
		}
		
		List<Peca> carrocas = new ArrayList<Peca>();
		
		for(Peca peca: mao) {
			if(peca.getNumDireito() == peca.getNumEsquerdo()) {
				carrocas.add(peca);
			}
		}
		
		for(Peca peca: carrocas) {
			if(peca.encaixa(mesa.getNumNaDireita()))
				return new Jogada(peca, TipoJogada.NA_DIREITA);
			if(peca.encaixa(mesa.getNumNaEsquerda()))
				return new Jogada(peca, TipoJogada.NA_ESQUERDA);
		}
		
		for(Peca peca: mao) {
			if(peca.encaixa(mesa.getNumNaDireita()))
				return new Jogada(peca, TipoJogada.NA_DIREITA);
			if(peca.encaixa(mesa.getNumNaEsquerda()))
				return new Jogada(peca, TipoJogada.NA_ESQUERDA);
		}
		
		return new Jogada();
	}
}
