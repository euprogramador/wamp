package br.com.aexo.nimbleway.subprotocols.json.encoder;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.stereotype.Component;

import br.com.aexo.nimbleway.messages.GoodByeMessage;
import br.com.aexo.nimbleway.messages.WampMessage;
import br.com.aexo.nimbleway.subprotocols.json.JsonEncoderMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * encoder from goodbyeMessage
 * 
 * @author carlosr
 *
 */
@Component
public class GoodByeMessageJsonEncoder implements JsonEncoderMessage<GoodByeMessage> {

	@Override
	public Object encode(GoodByeMessage msg) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			ArrayNode node = mapper.createArrayNode();
			node.add(6);

			// TODO trazer isso na mensagem
			ObjectNode detail = mapper.createObjectNode();
//			detail.put("message", "");
			node.add(detail); // options
			node.add("wamp.close.normal");

			StringWriter writer = new StringWriter();

			mapper.writeValue(writer, node);
			return writer.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean isEncodeOf(WampMessage type) {
		return type instanceof GoodByeMessage;
	}

}
