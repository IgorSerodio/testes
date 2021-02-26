package ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.JogadaInvalidaException;

class JogaMaisJogadaNaMesaTest {

	private Mesa mesa;

	@BeforeEach
	void setUp() throws Exception {
		mesa = new Mesa();
		mesa.jogaNaDireita(new Peca(1, 2));
		mesa.jogaNaEsquerda(new Peca(1, 1));
	}

	@Test
	void testPassa() {
		JogaMaisJogadaNaMesa estrategia = new JogaMaisJogadaNaMesa();

		Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(3, 3), new Peca(0, 3)));

		assertEquals(TipoJogada.PASSA, j1.getTipo());
	}

	@Test
	void testJogaMaisJogada() {
		JogaMaisJogadaNaMesa estrategia = new JogaMaisJogadaNaMesa();

		Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(3, 3), new Peca(0, 2), new Peca(1, 6)));

		assertEquals(TipoJogada.NA_ESQUERDA, j1.getTipo());
		assertEquals(1, j1.getPeca().getNumEsquerdo());
		assertEquals(6, j1.getPeca().getNumDireito());
	}
	
	@Test
	void testJogaMaisJogadaDesempatandoPeloSegundoValorDaPeca() throws JogadaInvalidaException {
		mesa.jogaNaDireita(new Peca(2, 2));
		mesa.jogaNaDireita(new Peca(2, 3));
		mesa.jogaNaDireita(new Peca(3, 4));
		
		JogaMaisJogadaNaMesa estrategia = new JogaMaisJogadaNaMesa();

		//Nessa situação há a prioridade para jogar as peças com os números: 1 > 2 > 3 > 4 > outras.
		Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(4, 1), new Peca(6, 1), new Peca(5, 1), new Peca(3, 1), new Peca(4, 4), new Peca(4, 2)));      

		assertEquals(TipoJogada.NA_ESQUERDA, j1.getTipo());
		assertEquals(3, j1.getPeca().getNumEsquerdo());
		assertEquals(1, j1.getPeca().getNumDireito());
	}
	
	@Test
	void testJogaPrimeiraQueEncaixaSeAsPrioridadesForemIguais() {
		JogaMaisJogadaNaMesa estrategia = new JogaMaisJogadaNaMesa();

		//As prioridades das peças 0:2 e 2:6 são iguais, pois não há peças com 0 ou 6 na mesa e ambas têm 2.
		Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(3, 3), new Peca(0, 2), new Peca(2, 6)));

		assertEquals(TipoJogada.NA_DIREITA, j1.getTipo());
		assertEquals(0, j1.getPeca().getNumEsquerdo());
		assertEquals(2, j1.getPeca().getNumDireito());
	}

	@Test
	void testPrefereDireita() throws JogadaInvalidaException {
		mesa.jogaNaDireita(new Peca(2, 4));
		mesa.jogaNaEsquerda(new Peca(4, 1));
		
		JogaMaisJogadaNaMesa estrategia = new JogaMaisJogadaNaMesa();

		Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(4, 4)));

		assertEquals(TipoJogada.NA_DIREITA, j1.getTipo());
		assertEquals(4, j1.getPeca().getNumEsquerdo());
		assertEquals(4, j1.getPeca().getNumDireito());
	}
	
	@Test
	void testJogaNaEsquerda() {
		JogaMaisJogadaNaMesa estrategia = new JogaMaisJogadaNaMesa();

		Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(3, 3), new Peca(4, 4), new Peca(1, 6)));

		assertEquals(TipoJogada.NA_ESQUERDA, j1.getTipo());
		assertEquals(1, j1.getPeca().getNumEsquerdo());
		assertEquals(6, j1.getPeca().getNumDireito());
	}


}
