/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */

// format: off

//#log4j2-class
import java.io.File
import java.net.URI
import java.net.URL

// ###skip: 1
/*
import play.api.{Mode, Configuration, Environment, LoggerConfigurator}

import org.slf4j.ILoggerFactory

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core._
import org.apache.logging.log4j.core.config.Configurator
// ###skip: 1
 */

import org.slf4j.ILoggerFactory
import org.slf4j.LoggerFactory
import play.api.Configuration
import play.api.Environment
import play.api.LoggerConfigurator
import play.api.Mode

// ###skip: 1
// format: on
class Log4J2LoggerConfigurator extends LoggerConfigurator {
  private var factory: ILoggerFactory = _

  override def init(rootPath: File, mode: Mode): Unit = {
    val properties   = Map("application.home" -> rootPath.getAbsolutePath)
    val resourceName = "log4j2.xml"
    val resourceUrl  = Option(this.getClass.getClassLoader.getResource(resourceName))
    configure(properties, resourceUrl)
  }

  override def shutdown(): Unit = {
    val context = LogManager.getContext().asInstanceOf[LoggerContext]
    Configurator.shutdown(context)
  }

  override def configure(env: Environment): Unit = {
    val properties  = LoggerConfigurator.generateProperties(env, Configuration.empty, Map.empty)
    val resourceUrl = env.resource("log4j2.xml")
    configure(properties, resourceUrl)
  }

  override def configure(
      env: Environment,
      configuration: Configuration,
      optionalProperties: Map[String, String]
  ): Unit = {
    // LoggerConfigurator.generateProperties enables play.logger.includeConfigProperties=true
    val properties  = LoggerConfigurator.generateProperties(env, configuration, optionalProperties)
    val resourceUrl = env.resource("log4j2.xml")
    configure(properties, resourceUrl)
  }

  override def configure(properties: Map[String, String], config: Option[URL]): Unit = {
    val context = LogManager.getContext(false).asInstanceOf[LoggerContext]
    context.setConfigLocation(config.get.toURI)

    factory = LoggerFactory.getILoggerFactory
  }

  override def loggerFactory: ILoggerFactory = factory
}
//#log4j2-class

object Configurator {
  def shutdown(context: Any): Unit = ???
}

object LogManager {
  def getContext(): LoggerContext = ???

  def getContext(b: Boolean): LoggerContext = ???
}

class LoggerContext {
  def setConfigLocation(toURI: URI): Unit = ???
}
