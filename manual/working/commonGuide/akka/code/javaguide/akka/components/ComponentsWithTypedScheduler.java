/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */

package javaguide.akka.components;

// #scheduler-compile-time-injection
import play.ApplicationLoader;
import play.BuiltInComponentsFromContext;
import play.components.AkkaTypedComponents;
import play.controllers.AssetsComponents;
import play.filters.components.HttpFiltersComponents;
import play.routing.Router;

public class ComponentsWithTypedScheduler extends BuiltInComponentsFromContext
    implements AkkaTypedComponents, AssetsComponents, HttpFiltersComponents {

  public ComponentsWithTypedScheduler(ApplicationLoader.Context context) {
    super(context);
  }

  @Override
  public Router router() {
    return Router.empty();
  }
}
// #scheduler-compile-time-injection
