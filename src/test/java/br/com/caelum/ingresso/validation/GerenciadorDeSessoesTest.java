package br.com.caelum.ingresso.validation;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.GerenciadorDeSessoes;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GerenciadorDeSessoesTest {

	@Test
	public void deveAdicionarSeListaDaSessaoEstiverVazia() {
		List<Sessao> sessoes = Collections.emptyList();
		GerenciadorDeSessoes gerenciadorDeSessoes = new GerenciadorDeSessoes(sessoes);
		Filme filme = new Filme();
		filme.setDuracao(120);
		LocalTime horario = LocalTime.now();
		Sala sala = new Sala("");
		Sessao sessao = new Sessao(filme, sala, horario);
		boolean cabe = gerenciadorDeSessoes.cabe(sessao);
		Assert.assertTrue(cabe);
	}

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
		Filme filme = new Filme();
		filme.setDuracao(120);
		LocalTime horario = LocalTime.now();
		Sala sala = new Sala("");
		Sessao sessao = new Sessao(filme, sala, horario);
		List<Sessao> sessoes = Arrays.asList(sessao);
		GerenciadorDeSessoes gerenciador = new GerenciadorDeSessoes(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente() {
		Filme filme = new Filme();
		filme.setDuracao(120);
		LocalTime horario = LocalTime.now();
		Sala sala = new Sala("");
		List<Sessao> sessoesDaSala =  Collections.singletonList(new Sessao(filme, sala, horario));
		GerenciadorDeSessoes gerenciadorDeSessoes = new GerenciadorDeSessoes(sessoesDaSala);
		Assert.assertFalse(gerenciadorDeSessoes.cabe(new Sessao(filme, sala,horario.plus(1, ChronoUnit.HOURS))));
	}

	@Test
	public void garanteQueDevePermitirUmaInsercaoentreDoisFilmes(){
		Sala sala = new Sala("");
		Filme filme = new Filme();
		filme.setDuracao(90);
		LocalTime dezHoras = LocalTime.parse("10:00:00");
		Sessao sessaoDasDez = new Sessao(filme, sala, dezHoras);
		Filme outroFilme = new Filme();
		outroFilme.setDuracao(120);
		LocalTime dezoitoHoras = LocalTime.parse("18:00:00");
		Sessao sessaoDasDezoito = new Sessao(outroFilme, sala, dezoitoHoras);
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez,sessaoDasDezoito);
		GerenciadorDeSessoes gerenciadorDeSessoes = new GerenciadorDeSessoes(sessoes);
		Assert.assertTrue(gerenciadorDeSessoes.cabe(new Sessao(outroFilme, sala, LocalTime.parse("13:00:00"))));
	}
}
