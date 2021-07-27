package axsonftpresentation.junitbestpractices.html;

import axsonftpresentation.junitbestpractices.business.StudentBusiness;
import axsonftpresentation.junitbestpractices.controller.StudentController;
import axsonftpresentation.junitbestpractices.model.StudentDet;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.ModelMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

@RunWith(EasyMockRunner.class)
public class MockStudentControllerTest {
    @TestSubject
    public static StudentController subject = new StudentController();
    @Mock
    private static StudentBusiness studentBusiness;

    @BeforeClass
    public static void setup() {
        subject.setStudentBusiness(studentBusiness);
    }

    @Test
    public void when_testWelcomeMsg_then_success() {
        replay(studentBusiness);

        ModelMap modelMap = new ModelMap();
        final String expectedView = subject.getWelcomeMsg(modelMap);

        assertThat("welcome").isEqualTo(expectedView);
    }

    @Test
    public void when_testGetStudent_then_success() {
        expect(studentBusiness.getStudentById(anyObject(Long.class)))
                .andStubReturn(new StudentDet(1L, "Pop"));

        ModelMap modelMap = new ModelMap();
        final String expectedView = subject.getStudent(modelMap);

        assertThat("student").isEqualTo(expectedView);
    }
}
