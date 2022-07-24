package de.faye.snakealarm

import kotlinx.coroutines.delay
import org.eclipse.microprofile.faulttolerance.Retry
import org.eclipse.microprofile.faulttolerance.Timeout
import java.time.temporal.ChronoUnit
import javax.enterprise.context.ApplicationScoped
import kotlin.random.Random

@ApplicationScoped
class LizardCheck {
    @Timeout(1, unit = ChronoUnit.SECONDS)
    @Retry(maxRetries = 3)
    suspend fun checkSnakeAlarmValid(snakeAlarm: SnakeAlarm): Boolean {
        if (Random.nextBoolean()) {
            delay(Random.nextLong(200, 1060))
            return false
        }
        return true
    }
}