package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sessao {
	
    @Id
    @GeneratedValue
    private Integer id;	
    @ManyToOne
	private Filme filme;
    @ManyToOne
	private Sala sala;
	private LocalTime horario;
	private BigDecimal preco;
		
	/**
	 * @deprecated hibernate only
	 */
	public Sessao(){
		
	}
	
    public Sessao(Filme filme, Sala sala, LocalTime horario) {
		this.filme = filme;
		this.sala = sala;
		this.horario = horario;
		this.preco = sala.getPreco().add(filme.getPreco());
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
    
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}    
	
	public LocalTime getHorarioTermino(){
		return this.horario.plus(filme.getDuracao().toMinutes(), ChronoUnit.MINUTES);
	}
	
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
