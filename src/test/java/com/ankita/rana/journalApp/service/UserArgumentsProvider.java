package com.ankita.rana.journalApp.service;

import com.ankita.rana.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("Radha").password("Radha").build()),
                Arguments.of(com.ankita.rana.journalApp.entity.User.builder().userName("Meera").password("").build())
        );
    }
}
