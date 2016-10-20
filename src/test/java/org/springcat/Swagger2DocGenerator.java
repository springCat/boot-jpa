package org.springcat;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springcat.conf.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class, SwaggerConfig.class})
public class Swagger2DocGenerator {
	@Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    
    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * 生成AsciiDoc文件
     * @throws Exception
     */
    @Test
    public void convertSwaggerToAsciiDoc() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs?group=Admin API")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(Swagger2MarkupResultHandler.outputDirectory("src/test/resources/docs/asciidoc").build())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * 生成MarkDown文件
     * @throws Exception
     */
    @Test
    public void convertSwaggerToMarkdown() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs?group=Admin API")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(Swagger2MarkupResultHandler.outputDirectory("src/test/resources/docs/markdown")
                    .withMarkupLanguage(MarkupLanguage.MARKDOWN).withPathsGroupedBy(GroupBy.TAGS).build())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    /**
     * 生成Swagger.json
     * @throws Exception
     */
    @Test
    public void createSpringfoxSwaggerJson() throws Exception {
        String outputDir = "/Users/springcat/Documents/workspace/boot-jpa/src/test/resources/docs/swagger";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs?group=Admin API")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String swaggerJson = response.getContentAsString();
        Files.createDirectories(Paths.get(outputDir));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)){
            writer.write(swaggerJson);
        }
    }
}
