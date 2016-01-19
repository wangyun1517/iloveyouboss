package iloveyouboss;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfileTest {
    private static Question question;
    private static Criterion criterion;

    private Criteria criteria;
    private Profile profile;

    @BeforeClass
    public static void beforeClass() {
        question = new BooleanQuestion(1, "Are you a girl?");
        criterion = new Criterion(new Answer(question, 0), Weight.Important);
    }

    @Before
    public void before() {
        criteria = new Criteria();
        profile = new Profile("Q1");
    }

    @Test
    public void should_return_1000_score_and_true_matches_when_answer_one_important_question_correctly() {
        criteria.add(criterion);
        profile.add(new Answer(question, 0));

        assertThat(profile.matches(criteria), is(true));
        assertThat(profile.score(), is(1000));
    }

    @Test
    public void should_return_0_score_and_false_matches_when_answer_one_important_question_wrongly() {
        criteria.add(criterion);
        profile.add(new Answer(question, 1));

        assertThat(profile.matches(criteria), is(false));
        assertThat(profile.score(), is(0));
    }
}