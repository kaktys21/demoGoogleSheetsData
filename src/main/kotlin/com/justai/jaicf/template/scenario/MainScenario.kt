package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.reactions.jaicp
import com.justai.jaicf.hook.*

val mainScenario = Scenario {
    handle<BotRequestHook> {
        if (!context.session.containsKey("helloMessage")) {
            reactions.say("Конбанва")
            context.session["helloMessage"] = true
        }
    }

    handle<ActionErrorHook> {
        reactions.run {
            image ("https://i.pinimg.com/550x/4a/85/3c/4a853c708d224464ce5f9e80e11176cd.jpg")
            say("Не надо так, братик")
        }
    }

    state("start") {
        activators {
            regex("/start")
            intent("Hello")
        }
        action {
            reactions.sayRandom("Семпай?", "Ахегао?", "Ямате?")
        }
    }

    state("bye") {
        activators {
            intent("Bye")
        }
        action {
            reactions.say("Обращайтесь снова! До встречи!")
            reactions.jaicp?.endSession()
            context.session.clear()
            context.client.clear()
        }
    }

    state("yaMete") {
        activators {
            regex("семпай")
            regex("ямете")
            regex("ахегао")
        }
        action {
            reactions.run {
                image("https://i.ytimg.com/vi/gxX3gRmRc4M/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLDc1V9Cg6wOXyDc5NnOBa6KJ6Ci9Q")
                sayRandom(
                    "Я-мете кудасай",
                    "Семпай!"
                )
            }
        }
    }
    state("fallback") {
        activators {
            catchAll()
        }
        action {
            reactions.say("Семпай?=(")
        }
    }
}