package de.faye

import de.faye.snakealarm.SnakeAlarm
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class SnakeAlarmEndpointTest {

    @Test
    fun testSnakeAlarmBadRequest() {
        given()
          .`when`().post("/snake-alarm")
          .then()
             .statusCode(415)
    }
}