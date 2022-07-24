package de.faye.snakealarm

import io.smallrye.reactive.messaging.MessageConverter
import io.smallrye.reactive.messaging.amqp.IncomingAmqpMetadata
import io.vertx.core.json.JsonObject
import org.eclipse.microprofile.reactive.messaging.Message
import java.lang.reflect.Type
import java.util.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.MediaType

@ApplicationScoped
class JsonMessageConverter(
	private val logger: Logger
) : MessageConverter {
	override fun canConvert(incoming: Message<*>?, target: Type?): Boolean =
		incoming?.getMetadata(IncomingAmqpMetadata::class.java)
			?.map {
				it.contentType == MediaType.APPLICATION_JSON && target is Class<*>
			}
			?.orElse(false) ?: false

	override fun convert(incoming: Message<*>, target: Type): Message<*> =
		incoming.withPayload((incoming.payload as JsonObject).mapTo(target as Class<*>?))
}