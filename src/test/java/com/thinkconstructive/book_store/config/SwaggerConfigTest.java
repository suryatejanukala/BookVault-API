package com.thinkconstructive.book_store.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SwaggerConfig Tests")
class SwaggerConfigTest {

    private final SwaggerConfig swaggerConfig = new SwaggerConfig();

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: customOpenAPI()
     * - Scenario: Bean created with correct API metadata
     * - Expected: OpenAPI bean with title "BookStore API", version "1.0", description, contact info, and license
     * - Coverage Target: All lines in customOpenAPI() method
     */
    @Test
    @DisplayName("Should create OpenAPI bean with correct metadata")
    void testCustomOpenAPIBean() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        assertNotNull(openAPI);
        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("BookStore API", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertEquals("Bookstore REST API Documentation", info.getDescription());
        assertEquals("Bookstore Service Terms", info.getTermsOfService());

        Contact contact = info.getContact();
        assertNotNull(contact);
        assertEquals("Surya", contact.getName());
        assertEquals("suryatejanukala2@gamil.com", contact.getEmail());
        assertEquals("http://thinkconstructive.com", contact.getUrl());

        License license = info.getLicense();
        assertNotNull(license);
        assertEquals("Think Constructive License", license.getName());
        assertEquals("http://thinkconstructive.com", license.getUrl());
    }
}
