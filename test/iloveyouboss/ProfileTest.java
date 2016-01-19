package iloveyouboss;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfileTest {
    private static Question importantQuestion;
    private static Criterion importantCriterion;
    private static Question dontCareQuestion;
    private static Criterion dontCareCriterion;
    private static Question mustMatchCareQuestion;
    private static Criterion mustMatchCriterion;

    private Criteria criteria;
    private Profile profile;

    @BeforeClass
    public static void beforeClass() {
        importantQuestion = new BooleanQuestion(1, "Are you important?");
        importantCriterion = new Criterion(new Answer(importantQuestion, 1), Weight.Important);

        dontCareQuestion = new BooleanQuestion(2, "Are you dontCare?");
        dontCareCriterion = new Criterion(new Answer(dontCareQuestion, 1), Weight.DontCare);

        mustMatchCareQuestion = new BooleanQuestion(3, "Are you mustMatch?");
        mustMatchCriterion = new Criterion(new Answer(mustMatchCareQuestion, 1), Weight.MustMatch);
    }

    @Before
    public void before() {
        criteria = new Criteria();
        profile = new Profile("Q1");
    }

    @Test
    public void should_return_1000_score_and_true_matches_when_answer_one_important_question_correctly() {
        criteria.add(importantCriterion);
        profile.add(new Answer(importantQuestion, 1));

        assertThat(profile.matches(criteria), is(true));
        assertThat(profile.score(), is(1000));
    }

    @Test
    public void should_return_0_score_and_false_matches_when_answer_one_important_question_wrongly() {
        criteria.add(importantCriterion);
        profile.add(new Answer(importantQuestion, 0));

        assertThat(profile.matches(criteria), is(false));
        assertThat(profile.score(), is(0));
    }

    @Test
    public void should_return_0_score_and_true_matches_when_answer_one_dont_care_question_correctly() {
        criteria.add(dontCareCriterion);
        profile.add(new Answer(dontCareQuestion, 1));

        assertThat(profile.matches(criteria), is(true));
        assertThat(profile.score(), is(0));
    }

    @Test
    public void should_return_0_score_and_true_matches_when_answer_dont_care_question_wrongly() {
        criteria.add(dontCareCriterion);
        profile.add(new Answer(dontCareQuestion, 0));

        assertThat(profile.matches(criteria), is(true));
        assertThat(profile.score(), is(0));
    }

    @Test
    public void should_return_max_integer_score_and_true_matches_when_answer_one_must_match_question_correctly() {
        criteria.add(mustMatchCriterion);
        profile.add(new Answer(mustMatchCareQuestion, 1));

        assertThat(profile.matches(criteria), is(true));
        assertThat(profile.score(), is(Integer.MAX_VALUE));
    }

    @Test
    public void should_return_0_score_and_false_matches_when_answer_one_must_match_question_wrongly() {
        criteria.add(mustMatchCriterion);
        profile.add(new Answer(mustMatchCareQuestion, 0));

        assertThat(profile.matches(criteria), is(false));
        assertThat(profile.score(), is(0));
    }

    @Test
    public void should_return_max_integer_plus_1000_score_and_true_matches_when_answer_one_must_match_question_correctly_and_one_important_question_correctly() {
        criteria.add(mustMatchCriterion);
        criteria.add(importantCriterion);
        profile.add(new Answer(mustMatchCareQuestion, 1));
        profile.add(new Answer(importantQuestion, 1));

        assertThat(profile.matches(criteria), is(true));
        assertThat(profile.score(), is(Integer.MAX_VALUE + 1000));
    }

    @Test
    public void should_return_1000_score_and_true_matches_when_answer_one_must_match_question_wrongly_and_one_important_question_correctly() {
        criteria.add(mustMatchCriterion);
        criteria.add(importantCriterion);
        profile.add(new Answer(mustMatchCareQuestion, 0));
        profile.add(new Answer(importantQuestion, 1));

        assertThat(profile.matches(criteria), is(false));
        assertThat(profile.score(), is(1000));
    }

    @Test
    public void should_return_0_score_and_true_matches_when_answer_one_must_match_question_wrongly_and_one_important_question_wrongly() {
        criteria.add(mustMatchCriterion);
        criteria.add(importantCriterion);
        profile.add(new Answer(mustMatchCareQuestion, 0));
        profile.add(new Answer(importantQuestion, 0));

        assertThat(profile.matches(criteria), is(false));
        assertThat(profile.score(), is(0));
    }

    @Test
    public void should_return_0_score_and_true_matches_when_answer_one_must_match_question_correctly_and_one_important_question_wrongly() {
        criteria.add(mustMatchCriterion);
        criteria.add(importantCriterion);
        profile.add(new Answer(mustMatchCareQuestion, 1));
        profile.add(new Answer(importantQuestion, 0));

        assertThat(profile.matches(criteria), is(true));
        assertThat(profile.score(), is(Integer.MAX_VALUE));
    }
}