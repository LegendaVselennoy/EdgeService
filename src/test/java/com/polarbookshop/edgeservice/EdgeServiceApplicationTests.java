package com.polarbookshop.edgeservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

// Загружает полный контекст Web-приложения Spring и Web-окружение, прослушивающее случайный порт
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers  // Активирует автоматический запуск и очистку тестовых контейнеров
class EdgeServiceApplicationTests {

	private static final int REDIS_PORT = 6379;

	@Container
	// Определяет контейнер Redis для тестирования
	static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:7.0"))
			.withExposedPorts(REDIS_PORT);


	@DynamicPropertySource
	// Перезаписывает конфигурацию Redis, чтобы она указывала на тестовый экземпляр Redis
	static void redisProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.redis.host",
				() -> redis.getHost());
		registry.add("spring.redis.port",
				() -> redis.getMappedPort(REDIS_PORT));
	}

	// Пустой тест, используемый для проверки правильности загрузки контекста приложения и успешного подключения к Redis
	@Test
	void verifyThatSpringContextLoads(){

	}
}
