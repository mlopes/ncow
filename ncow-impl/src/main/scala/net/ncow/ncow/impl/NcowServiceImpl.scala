package net.ncow.ncow.impl

import java.util.UUID

import akka.{Done, NotUsed}
import net.ncow.ncow.api
import net.ncow.ncow.api.{NcowService, RegisterBotRequest, RegisterBotResponse}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

/**
  * Implementation of the NcowService.
  */
class NcowServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends NcowService {
  def registerBot: ServiceCall[RegisterBotRequest, RegisterBotResponse] = ServiceCall { request: RegisterBotRequest =>
    val ref = persistentEntityRegistry.refFor[NcowEntity] (UUID.randomUUID().toString)

    ref.ask(RegisterBotCommand(request.name))
  }
}
