/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */

package javaguide.akka;

// #injectedparent

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import javax.inject.Inject;
import play.libs.akka.InjectedActorSupport;

public class ParentActor extends AbstractActor implements InjectedActorSupport {

  private ConfiguredChildActorProtocol.Factory childFactory;

  @Inject
  public ParentActor(ConfiguredChildActorProtocol.Factory childFactory) {
    this.childFactory = childFactory;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().match(ParentActorProtocol.GetChild.class, this::getChild).build();
  }

  private void getChild(ParentActorProtocol.GetChild msg) {
    String key = msg.key;
    ActorRef child = injectedChild(() -> childFactory.create(key), key);
    sender().tell(child, self());
  }
}
// #injectedparent
