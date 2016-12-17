package life.memy.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RepositoryExceptionMapper implements ExceptionMapper<RepositoryException> {

	@Override
	public Response toResponse(RepositoryException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 400, "http://memy.life");
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
