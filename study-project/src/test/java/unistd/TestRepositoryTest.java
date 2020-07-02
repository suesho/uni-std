package unistd;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepositoryTest {

    @Autowired
    private TestRepository repo;

    @Test
    public void testName() throws Exception {
        TestEntity e = new TestEntity();
        repo.save(e);

        List<TestEntity> savedEList = repo.findAll();

        assertThat(savedEList).hasSize(1);
    }
}
