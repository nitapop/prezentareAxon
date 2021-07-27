package axsonftpresentation.junitbestpractices.html;

import axsonftpresentation.junitbestpractices.business.StudentBusiness;
import axsonftpresentation.junitbestpractices.business.StudentBusinessImpl;
import axsonftpresentation.junitbestpractices.controller.TeacherController;
import axsonftpresentation.junitbestpractices.model.StudentDet;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.anyObject;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MockitoTeacherControllerTest {
    @InjectMocks
    public static TeacherController subject = new TeacherController();
    @InjectMocks
    private static StudentBusiness studentBusiness = new StudentBusinessImpl();

    @Mock
    private MessageSource messageSource;
    @Mock
    private Provider<MessageSource> messageSourceProvider;

    @Before
    public void setup() {
        studentBusiness = spy(studentBusiness);

        initMocks(this);
        when(messageSourceProvider.get()).thenReturn(messageSource);
    }

    @Test
    public void when_testWelcomeMsg_then_success() {

        ModelMap modelMap = new ModelMap();
        final String expectedView = subject.getWelcomeMsg(modelMap);

        assertThat("welcome").isEqualTo(expectedView);
    }

    @Test
    public void when_testGetStudent_then_success() {
        when(studentBusiness.getStudentById(anyObject(Long.class)))
                .thenReturn(new StudentDet(1L, "Pop"));

        ModelMap modelMap = new ModelMap();
        final String expectedView = subject.getStudent(modelMap);

        assertThat("student").isEqualTo(expectedView);
    }
}
