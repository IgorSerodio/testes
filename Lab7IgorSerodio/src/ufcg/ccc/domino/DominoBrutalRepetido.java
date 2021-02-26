package ufcg.ccc.domino;

import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaCarrocasPrimeiro;
import ufcg.ccc.domino.estrategia.JogaMaisJogadaNaMesa;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

/**
 * Exemplo de como fazer um um main com uma disputa de muuuitos jogos entre duas
 * estrat√©gias.
 * 
 */
public class DominoBrutalRepetido {

	public static void main(String[] args) throws EstrategiaInvalidaException, JogadaInvalidaException {
		int pontosJ1 = 0, pontosJ2 = 0, empates = 0;
		int carrocaJ1 = 0, laEloJ1 = 0, cruzadaJ1 = 0;
		int carrocaJ2 = 0, laEloJ2 = 0, cruzadaJ2 = 0;
		int vitoriasNormaisJ1 = 0, vitoriasNormaisJ2 = 0;
		int jogos = 0;
		

		EstrategiaDeJogo estrategia1 = new JogaMaisJogadaNaMesa(), estrategia2 = new JogaCarrocasPrimeiro(); 
		
		for (int i = 0; i < 10000; i++) {
			jogos++;
			Jogo j = new Jogo("J1", estrategia1, "J2", estrategia2, 12);
			HistoricoDeJogo historico = j.jogaJogoCompleto();
			if (historico.isEmpate()) {
				empates++;
			} else if (historico.getVencedor() == "J1") {
				pontosJ1 += historico.getPontuacao();
				if(historico.getPontuacao() == 6) {
					cruzadaJ1++;
				} else if(historico.getPontuacao() == 3) {
					laEloJ1++;
				} else if(historico.getPontuacao() == 2) {
					carrocaJ1++;
				} else {
					vitoriasNormaisJ1++;
				}
			} else if (historico.getVencedor() == "J2") {
				pontosJ2 += historico.getPontuacao();
				if(historico.getPontuacao() == 6) {
					cruzadaJ2++;
				} else if(historico.getPontuacao() == 3) {
					laEloJ2++;
				} else if(historico.getPontuacao() == 2) {
					carrocaJ2++;
				} else {
					vitoriasNormaisJ2++;
				}
			}
		}

		System.out.println("Jogos:\t" + jogos + "\nJ1:\t\t" + pontosJ1 + " pontos" + " (" +  carrocaJ1 + " carroÁas, " + laEloJ1 + " l· e lÙ, " + cruzadaJ1 + " cruzadas e " + vitoriasNormaisJ1 + " vitÛrias normais)"
				                              + "\nJ2:\t\t" + pontosJ2 + " pontos" + " (" +  carrocaJ2 + " carroÁas, " + laEloJ2 + " l· e lÙ, " + cruzadaJ2 + " cruzadas e " + vitoriasNormaisJ2 + " vitÛrias normais)"
				                              + "\nEmpates:\t" + empates + " empates");
	}
}
