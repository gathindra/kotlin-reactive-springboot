package com.gathi.kotlin.config

import com.gathi.kotlin.data.converters.CustomerReaderConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.PostgresDialect


@Configuration
class R2dbcConfig {

    @Bean
    fun r2dbcCustomConverters(): R2dbcCustomConversions {
        val converters = listOf(CustomerReaderConverter())
        return R2dbcCustomConversions.of(
            PostgresDialect.INSTANCE,
            converters
        )
    }
}