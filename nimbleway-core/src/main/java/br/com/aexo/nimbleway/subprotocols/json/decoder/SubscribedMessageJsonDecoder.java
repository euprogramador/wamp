package br.com.aexo.nimbleway.subprotocols.json.decoder;


import org.springframework.stereotype.Component;

import br.com.aexo.nimbleway.messages.SubscribedMessage;
import br.com.aexo.nimbleway.subprotocols.json.JsonDecoderMessage;

import com.fasterxml.jackson.databind.node.ArrayNode;

@Component
public class SubscribedMessageJsonDecoder implements JsonDecoderMessage<SubscribedMessage> {

	@Override
	public SubscribedMessage decode(Object o) {

		ArrayNode raw = (ArrayNode) o;
		Long idRequest = raw.get(1).asLong();
		Long idRegistration = raw.get(2).asLong();

		return new SubscribedMessage(idRequest, idRegistration);
	}

	@Override
	public boolean isDecodeOf(Integer messageIdType) {
		return new Integer(33).equals(messageIdType);
	}

}
