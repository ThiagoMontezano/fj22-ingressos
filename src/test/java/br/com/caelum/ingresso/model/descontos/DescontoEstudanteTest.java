package br.com.caelum.ingresso.model.descontos;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoEstudanteTest {

	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante(){
		Lugar lugar = new Lugar("A",1);
		Sala sala = new Sala("Eldorado - Imax", new BigDecimal("20.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",new BigDecimal("12.0"));
		Sessao sessao = new Sessao(filme, sala,LocalTime.now());
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);
		BigDecimal precoEsperado = new BigDecimal("16.25");
		assertEquals(precoEsperado, ingresso.getPreco());
	}
}
