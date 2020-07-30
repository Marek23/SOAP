package pl.com.home.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetMovieRequest;
import io.spring.guides.gs_producing_web_service.GetMovieResponse;
import pl.com.home.soap.repository.MovieRepository;

@Endpoint
public class MovieEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private MovieRepository repository;

	@Autowired
	public MovieEndpoint(MovieRepository repository) {
		this.repository = repository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieRequest")
	@ResponsePayload
	public GetMovieResponse getCountry(@RequestPayload GetMovieRequest request) {
		GetMovieResponse response = new GetMovieResponse();

		response.setMovie(repository.findMovie(request.getName()));

		return response;
	}
}