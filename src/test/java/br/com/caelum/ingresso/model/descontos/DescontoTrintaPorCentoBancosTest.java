package br.com.caelum.ingresso.model.descontos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTrintaPorCentoBancosTest {

	@Test
	public void deveConcederDescontoDe30PorCentoParaIngressoDeClientesDeBancos(){
		Sala sala = new Sala("Eldorado - Imax", new BigDecimal("20.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",new BigDecimal("12.0"));
		Sessao sessao = new Sessao(filme, sala,LocalTime.now());
		Ingresso ingresso = new Ingresso(sessao, new DescontoTrintaPorCentoBancos());
		BigDecimal precoEsperado = new BigDecimal("22.75");
		assertEquals(precoEsperado, ingresso.getPreco());
	}
}
