package br.com.caelum.ingresso.rest;

import java.net.URI;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;

@Component
public class ImdbClient {

	public Optional<DetalhesDoFilme> request(Filme filme) {
		RestTemplate client	= new RestTemplate();
		String url = "https://imdb-fj22.herokuapp.com/imdb?title=%s";
		URI uri = URI.create(String.format(url, filme.getNome().replace(" ", "+")));
		try	{
			DetalhesDoFilme	detalhesDoFilme	= client.getForObject(uri, DetalhesDoFilme.class);
			return Optional.of(detalhesDoFilme);
		} catch (RestClientException e)	{
			return	Optional.empty();
		}
	}
}
