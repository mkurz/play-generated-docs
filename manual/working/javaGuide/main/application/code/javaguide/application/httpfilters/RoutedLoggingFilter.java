/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */

package javaguide.application.httpfilters;

// #routing-info-access
import akka.stream.Materializer;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.routing.HandlerDef;
import play.mvc.*;
import play.routing.Router;

public class RoutedLoggingFilter extends Filter {

  private static final Logger log = LoggerFactory.getLogger(RoutedLoggingFilter.class);

  @Inject
  public RoutedLoggingFilter(Materializer mat) {
    super(mat);
  }

  @Override
  public CompletionStage<Result> apply(
      Function<Http.RequestHeader, CompletionStage<Result>> nextFilter,
      Http.RequestHeader requestHeader) {
    long startTime = System.currentTimeMillis();
    return nextFilter
        .apply(requestHeader)
        .thenApply(
            result -> {
              HandlerDef handlerDef = requestHeader.attrs().get(Router.Attrs.HANDLER_DEF);
              String actionMethod = handlerDef.controller() + "." + handlerDef.method();
              long endTime = System.currentTimeMillis();
              long requestTime = endTime - startTime;

              log.info("{} took {}ms and returned {}", actionMethod, requestTime, result.status());

              return result.withHeader("Request-Time", "" + requestTime);
            });
  }
}
// #routing-info-access
