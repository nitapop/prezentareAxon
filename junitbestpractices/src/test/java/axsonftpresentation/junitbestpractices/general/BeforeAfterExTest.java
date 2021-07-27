package axsonftpresentation.junitbestpractices.general;

import axsonftpresentation.junitbestpractices.config.EntitiesTestSpringBootConfiguration;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EntitiesTestSpringBootConfiguration.class,
        BeforeAfterExTest.Config.class})
@DataJpaTest(showSql = true)
public class BeforeAfterExTest{

    private List<String> expectedList;
    private String expectedName;

    @BeforeClass
    public static void onceExecutedBeforeAllTest() {
        System.out.println("@BeforeClass");
    }

    @Before
    public void executedBeforeEachTest() {
        expectedList = Lists.newArrayList();
        expectedName = "Pop";
    }

    @Test
    public void when_checkEmptyList_then_listIsEmpty() {
        assertThat(expectedList.isEmpty());
    }

    @Test
    public void when_checkName_then_nameIsPop() {
        assertThat("Pop").isEqualTo(expectedName);
    }

    @SpringBootConfiguration
    public static class Config {

    }

}
