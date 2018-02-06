package com.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;

public class SimpleREST extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.get("/").handler(event -> {
            MultiMap entries = event.queryParams();
            String name = entries.get("name");
            HttpServerRequest request = event.request();
            String host = request.connection().remoteAddress().host();
            System.out.println("IP: " + host);
            event.response().putHeader("Content-Type", "text/plain;charset=utf-8")
                    .end("Hello " + name);
        });

        vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", 8080));
    }

}
