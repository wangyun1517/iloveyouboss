package iloveyouboss;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfileTest {

    @Test
    public void should_return_1000_score_and_true_matches_when_answer_one_important_question_correctly() {
        Question question = new BooleanQuestion(1, "Are you a girl?");

        Criterion criterion = new Criterion(new Answer(question, 0), Weight.Important);
        Criteria criteria = new Criteria();
        criteria.add(criterion);
        Profile profile = new Profile("Q1");
        profile.add(new Answer(question, 0));

        assertThat(profile.matches(criteria), is(true));
        assertThat(profile.score(), is(1000));
    }

    @Test
    public void should_return_0_score_and_false_matches_when_answer_one_important_question_wrongly() {
        Question question = new BooleanQuestion(1, "Are you a girl?");

        Profile profile = new Profile("Q1");
        profile.add(new Answer(question, 0));

        Criterion criterion = new Criterion(new Answer(question, 1), Weight.Important);
        Criteria criteria = new Criteria();
        criteria.add(criterion);

        assertThat(profile.matches(criteria), is(false));
        assertThat(profile.score(), is(0));
    }
}