/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */

package javaguide.akka.typed.oo;

// #compile-time-di
import akka.actor.typed.ActorRef;
import akka.actor.typed.javadsl.Adapter;
import java.util.Collections;
import java.util.List;
import play.ApplicationLoader;
import play.BuiltInComponentsFromContext;
import play.mvc.EssentialFilter;
import play.routing.Router;

public final class AppComponents extends BuiltInComponentsFromContext {

  public final ActorRef<HelloActor.SayHello> helloActor;
  public final ActorRef<ConfiguredActor.GetConfig> configuredActor;
  public final Main main;

  public AppComponents(ApplicationLoader.Context context) {
    super(context);
    helloActor = Adapter.spawn(actorSystem(), HelloActor.create(), "hello-actor");
    configuredActor =
        Adapter.spawn(actorSystem(), ConfiguredActor.create(config()), "configured-actor");
    main = new Main(helloActor, configuredActor);
  }

  @Override
  public Router router() {
    return Router.empty();
  }

  @Override
  public List<EssentialFilter> httpFilters() {
    return Collections.emptyList();
  }
}
// #compile-time-di
