package br.com.caelum.ingresso.rest;

import java.net.URI;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import br.com.caelum.ingresso.model.Filme;

@Component
public class ImdbClient {

	private Logger logger = Logger.getLogger(ImdbClient.class);
	
	public <T> Optional<T> request(Filme filme, Class<T> classe) {
		RestTemplate client	= new RestTemplate();
		String url = "https://imdb-fj22.herokuapp.com/imdb?title=%s";
		URI uri = URI.create(String.format(url, filme.getNome().replace(" ", "+")));
		try	{
			return Optional.of(client.getForObject(uri, classe));
		} catch (RestClientException e)	{
			logger.error(e.getMessage(),e);
			return	Optional.empty();
		}
	}
}
