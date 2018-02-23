package ir.moke.jsf.exception;

import org.apache.johnzon.mapper.MapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidDataExceptionMapper implements ExceptionMapper<MapperException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidDataExceptionMapper.class);

    @Override
    public Response toResponse(MapperException e) {
        String msg = e.getMessage();
        String response = String.format("Error message : %s\n", msg);
        LOGGER.warn("Exception Type : " + e.getClass() + " -> " + response);
        return Response.status(422).entity(response).build();
    }
}
