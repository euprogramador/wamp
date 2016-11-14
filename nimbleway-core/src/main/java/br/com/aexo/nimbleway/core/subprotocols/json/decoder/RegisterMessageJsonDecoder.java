package br.com.aexo.nimbleway.core.subprotocols.json.decoder;

import java.util.Iterator;

import br.com.aexo.nimbleway.core.Registration;
import br.com.aexo.nimbleway.core.messages.AbortMessage;
import br.com.aexo.nimbleway.core.messages.RegisterMessage;
import br.com.aexo.nimbleway.core.subprotocols.json.JsonDecoderMessage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * Decoder of abort message
 * 
 * @author carlosr
 *
 */
public class RegisterMessageJsonDecoder implements JsonDecoderMessage<RegisterMessage> {

	@Override
	public RegisterMessage decode(Object o) {
		ArrayNode raw = (ArrayNode) o;

		Long requestId = raw.get(1).asLong();
		JsonNode details = raw.get(2);
		String procedureName = raw.get(3).asText();

		Registration registration = Registration.toName(procedureName);
		Iterator<String> detailsName = details.fieldNames();

		while (detailsName.hasNext()) {
			String detail = detailsName.next();
			registration = registration.option(detail, details.get(detail).asText());
		}

		return new RegisterMessage(requestId, registration);
	}

	@Override
	public boolean isDecodeOf(Integer messageIdType) {
		return new Integer(64).equals(messageIdType);
	}

}
