package de.faye.util

import java.util.logging.Logger
import javax.enterprise.inject.Produces
import javax.enterprise.inject.spi.InjectionPoint

class LoggerProvider {
    @Produces
    fun createLogger(injectionPoint: InjectionPoint): Logger {
        return Logger.getLogger(injectionPoint.member.declaringClass.name)
    }
}