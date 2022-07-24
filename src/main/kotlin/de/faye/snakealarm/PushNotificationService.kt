package de.faye.snakealarm

import org.eclipse.microprofile.faulttolerance.Fallback
import org.eclipse.microprofile.faulttolerance.Retry
import java.util.*
import java.util.logging.Logger
import javax.enterprise.context.ApplicationScoped
import kotlin.random.Random

@ApplicationScoped
class PushNotificationService(
	private val logger: Logger
) {
	@Fallback(fallbackMethod = "announceSecondaryPushNotificationService")
	fun announcePrimaryPushNotificationService(): Date {
		val fail = Random.nextBoolean()
		if (fail) {
			error("Can't reach primary push notification service")
		}
		logger.info { "Push notification estimated in 1 sec" }
		return Calendar.getInstance().apply {
			add(Calendar.SECOND, 1)
		}.time
	}

	@Retry(maxRetries = 2)
	fun announceSecondaryPushNotificationService(): Date {
		logger.info { "Push notification estimated in 3 sec" }
		return Calendar.getInstance().apply {
			add(Calendar.SECOND, 3)
		}.time
	}
}