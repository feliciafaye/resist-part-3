package de.faye.snakealarm

data class SnakeAlarm(
	val snakes: List<SnakeItems>,
	val snakeVerification: String?,
	val estimatedPushNotificationTimestamp: Long?
)