package ufcg.ccc.domino;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

class HistoricoDeJogoTest {

	@Test
	void testGetPontuacaoComVitoriaNormal() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(0, 1));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(0, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertTrue(!historico.isEmpate() && historico.getVencedor()!=null);
		assertTrue(1 == historico.getPontuacao());
	}
	
	@Test
	void testGetPontuacaoComVitoriaLaElo() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(2, 1));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(0, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertTrue(!historico.isEmpate() && historico.getVencedor()!=null);
		assertTrue(3 == historico.getPontuacao());
	}
	
	@Test
	void testGetPontuacaoComVitoriaCarroca() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(2, 2));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(0, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertTrue(!historico.isEmpate() && historico.getVencedor()!=null);
		assertTrue(2 == historico.getPontuacao());
	}
	
	@Test
	void testGetPontuacaoComVitoriaCruzada() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 1), new Peca(2, 1), new Peca(6, 6));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(2, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		HistoricoDeJogo historico = j.jogaJogoCompleto();
		
		assertTrue(!historico.isEmpate() && historico.getVencedor()!=null);
		assertTrue(6 == historico.getPontuacao());
	}

}
