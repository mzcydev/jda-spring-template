package dev.mzcy.bot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JdaConfig {

    private final BotProperties properties;

    @Bean
    public JDA jda(List<ListenerAdapter> listeners) throws InterruptedException {
        JDA jda = JDABuilder.createDefault(properties.getToken())
                .enableIntents(
                        //TODO: add all intents
                )
                .addEventListeners(listeners.toArray())
                .build();

        jda.awaitReady();
        log.info("JDA ready — logged in as {}", jda.getSelfUser().getAsTag());
        return jda;
    }
}