/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */

package javaguide.application.root;

// #root
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Singleton;
import play.http.HttpErrorHandler;
import play.mvc.*;
import play.mvc.Http.*;

@Singleton
public class ErrorHandler implements HttpErrorHandler {
  public CompletionStage<Result> onClientError(
      RequestHeader request, int statusCode, String message) {
    return CompletableFuture.completedFuture(
        Results.status(statusCode, "A client error occurred: " + message));
  }

  public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
    return CompletableFuture.completedFuture(
        Results.internalServerError("A server error occurred: " + exception.getMessage()));
  }
}
// #root
