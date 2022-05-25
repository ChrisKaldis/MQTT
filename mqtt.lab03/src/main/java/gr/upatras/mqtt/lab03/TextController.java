package gr.upatras.mqtt.lab03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class TextController {

	@Autowired
	private ITextService textService;
	
	private static final Logger log = LoggerFactory.getLogger( TextController.class);

	@ApiOperation(value = "Publish a Text.", notes = "This operation publish a text.", response = String.class)
			@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = String.class),
									@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
									@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
									@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
									@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
									@ApiResponse(code = 409, message = "Conflict", response = Error.class),
									@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
			@RequestMapping(value = "/text", produces = { "application/json;charset=utf-8" }, consumes = {
				"application/json;charset=utf-8" }, method = RequestMethod.POST)
	
			public ResponseEntity<String> publishText(@ApiParam(value = "The text to be published.", required = true)
				@RequestBody String text ) {
				log.info( "Will publish a text using mqtt.");
				String answer = textService.publishText(text);
				return new ResponseEntity<String>( answer, HttpStatus.OK);
			}
}
