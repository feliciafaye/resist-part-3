package de.faye.snakealarm

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("snake-alarm")
class SnakeAlarmEndpoint(
	private val snakeAlarmRepository: SnakeAlarmRepository
) {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	suspend fun snakeAlarm(request: SnakeAlarmRequest): Response {
		return when (snakeAlarmRepository.alarm(request.snakes)) {
			true  -> Response.accepted().build()
			false -> Response.serverError().build()
		}
	}
}