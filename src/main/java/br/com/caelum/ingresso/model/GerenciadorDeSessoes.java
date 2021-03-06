package br.com.caelum.ingresso.model;

import java.time.LocalTime;
import java.util.List;

public class GerenciadorDeSessoes {
	
	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessoes(List<Sessao> sessoesDaSala){
		this.sessoesDaSala = sessoesDaSala;
	}
	
	public boolean cabe(Sessao sessaoAtual){
		return sessoesDaSala.stream()
				.map(s-> horarioIsValido(s, sessaoAtual))
				.reduce(Boolean::logicalAnd)
				.orElse(true);
	}

	private boolean horarioIsValido(Sessao sessaoExistente, Sessao sessaoAtual) {
		LocalTime horarioSessao = sessaoExistente.getHorario();
		LocalTime horarioAtual = sessaoAtual.getHorario();
		
		boolean ehAntes = horarioAtual.isBefore(horarioSessao);
		
		if(ehAntes){
			return horarioAtual.plusMinutes(sessaoAtual.getFilme().getDuracao().toMinutes())
					.isBefore(sessaoExistente.getHorario());
		}
		else{
			return sessaoExistente.getHorarioTermino().isBefore(horarioAtual);
		}
	}

}
