package br.com.aexo.nimbleway.subprotocols.json.decoder;


import org.springframework.stereotype.Component;

import br.com.aexo.nimbleway.messages.PublishedMessage;
import br.com.aexo.nimbleway.subprotocols.json.JsonDecoderMessage;

import com.fasterxml.jackson.databind.node.ArrayNode;

@Component
public class PublishedMessageJsonDecoder implements JsonDecoderMessage<PublishedMessage> {

	@Override
	public PublishedMessage decode(Object o) {

		ArrayNode raw = (ArrayNode) o;
		Long requestId = raw.get(1).asLong();
		Long publicationId = raw.get(2).asLong();

		return new PublishedMessage(requestId, publicationId);
	}

	@Override
	public boolean isDecodeOf(Integer messageIdType) {
		return new Integer(17).equals(messageIdType);
	}

}
