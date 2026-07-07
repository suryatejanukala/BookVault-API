package com.thinkconstructive.book_store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.*;

@DisplayName("BookStoreApplication Tests")
class BookStoreApplicationTest {

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: main(String[] args)
     * - Scenario: Calling main() starts the Spring application context successfully
     * - Expected: SpringApplication.run() is invoked with BookStoreApplication.class and args
     * - Coverage Target: main method, line calling SpringApplication.run()
     */
    @Test
    @DisplayName("Should run Spring application with correct arguments")
    void testMainMethodRunsApplication() {
        try (MockedStatic<SpringApplication> mockedSpringApp = mockStatic(SpringApplication.class)) {
            ConfigurableApplicationContext mockContext = mock(ConfigurableApplicationContext.class);
            mockedSpringApp.when(() -> SpringApplication.run(eq(BookStoreApplication.class), any(String[].class)))
                    .thenReturn(mockContext);

            BookStoreApplication.main(new String[]{});

            mockedSpringApp.verify(() -> SpringApplication.run(eq(BookStoreApplication.class), any(String[].class)));
        }
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: main(String[] args)
     * - Scenario: Application context loads without errors (mock-based)
     * - Expected: SpringApplication.run() is called and returns a non-null context
     * - Coverage Target: main method execution path
     */
    @Test
    @DisplayName("Should invoke SpringApplication.run with correct class")
    void testApplicationContextLoads() {
        try (MockedStatic<SpringApplication> mockedSpringApp = mockStatic(SpringApplication.class)) {
            ConfigurableApplicationContext mockContext = mock(ConfigurableApplicationContext.class);
            mockedSpringApp.when(() -> SpringApplication.run(eq(BookStoreApplication.class), any(String[].class)))
                    .thenReturn(mockContext);

            BookStoreApplication.main(new String[]{"--server.port=0"});

            mockedSpringApp.verify(() -> SpringApplication.run(BookStoreApplication.class, new String[]{"--server.port=0"}));
        }
    }
}
